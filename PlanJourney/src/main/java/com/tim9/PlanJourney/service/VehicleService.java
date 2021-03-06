package com.tim9.PlanJourney.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.PlanJourney.beans.QuickVehicleReserveBean;
import com.tim9.PlanJourney.beans.VehicleReservationSearchBean;
import com.tim9.PlanJourney.models.Discounts;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.rentacar.BranchOffice;
import com.tim9.PlanJourney.models.rentacar.QuickVehicleReservation;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.repository.VehicleRepository;

@Service
@Transactional(readOnly = true)
public class VehicleService {
	@Autowired
	VehicleRepository repository;
	
	@Autowired
	VehicleReservationService reservationService;
	
	@Autowired
	RentACarCompanyService companyService;
	
	@Autowired
	RegisteredUserService userService;
	
	@Autowired
	BranchOfficeService bs;
	
	@Autowired
	QuickVehicleReservationService quickService;
	
	@Autowired
	DiscountsService discountService;
	
	public Vehicle findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Vehicle> findAll() {
		return repository.findAll();
	}
	
	public Page<Vehicle> findAll(Pageable page) {
		return repository.findAll(page);
	}
	@Transactional(readOnly = false)
	public Vehicle save(Vehicle vehicle) {
		return repository.save(vehicle);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public VehicleReservation reserve(VehicleReservationSearchBean search, RegisteredUser user) throws Exception {
		
		Vehicle vehicle = findOne(Long.parseLong(search.getId()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFrom = sdf.parse(search.getDateFrom());
		Date dateTo = sdf.parse(search.getDateTo());
		BranchOffice pick = bs.findOne(Long.parseLong(search.getOfficePick()));
		BranchOffice ret = bs.findOne(Long.parseLong(search.getOfficeReturn()));	
		VehicleReservation reservation = new VehicleReservation(vehicle, user,new Date(), dateFrom, dateTo, (double)( (dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60 * 24)) * vehicle.getPrice() );
		reservation.setOfficePick(pick);
		reservation.setOfficeReturn(ret);
		RentACarCompany company = vehicle.getCompany();
		reservation.setCompany(company);
		Discounts discounts = discountService.findOne(1L);
		if (user.getVehicleReservations().size() >= discounts.getNumberOfRACReservations()) {
			reservation.setDiscount(discounts.getRentACarDiscount());
		}
		if(!checkIfAvaiable(vehicle,dateFrom,dateTo)) {
			throw new Exception();
		}
		vehicle.getReservations().add(reservation);
		user.getVehicleReservations().add(reservation);
		company.getReservations().add(reservation);
		save(vehicle);
		VehicleReservation reser=  reservationService.save(reservation);
		return reser;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public VehicleReservation reserveQuick(@RequestBody QuickVehicleReserveBean bean, RegisteredUser user) throws Exception {
		
		QuickVehicleReservation quick = quickService.findOne(bean.getId());
		Vehicle vehicle = quick.getVehicle();
		Date dateFrom = quick.getDateFrom();
		Date dateTo = quick.getDateTo();
		BranchOffice pick =quick.getOfficePick();
		BranchOffice ret = quick.getOfficeReturn();
		VehicleReservation reservation = new VehicleReservation(vehicle, user,new Date(), dateFrom, dateTo, quick.getOriginalPrice()*(1.0-quick.getDiscount()/100.0) );
		reservation.setOfficePick(pick);
		reservation.setOfficeReturn(ret);
		if(quick.isTaken()) {
			throw new Exception();
		}
		quick.setTaken(true);
		quickService.save(quick);
		reservation.setQuick(quick.getId());
		user.getVehicleReservations().add(reservation);
		vehicle.getReservations().add(reservation);
		RentACarCompany company = vehicle.getCompany();
		company.getReservations().add(reservation);
		save(vehicle);
		reservation.setCompany(company);
		reservation.setDiscount(quick.getDiscount());
		VehicleReservation vr = reservationService.save(reservation);
		return vr;
	}
	
	private boolean checkIfAvaiable(Vehicle v, Date dateFrom, Date dateTo) {
		ArrayList<VehicleReservation> reservations = new ArrayList<>();
		Vehicle vehicle = findOne(v.getId());
		reservations.addAll(vehicle.getReservations());
		ArrayList<QuickVehicleReservation> quickReservations = new ArrayList<>();
		quickReservations.addAll(vehicle.getQuickReservations());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		for (VehicleReservation vehicleReservation : reservations) {
			if (!(vehicleReservation.getDateFrom().after(dateTo) || vehicleReservation.getDateTo().after(dateFrom))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateFrom()).equals(sdf.format(dateTo)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateFrom()).equals(sdf.format(dateFrom)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateTo()).equals(sdf.format(dateFrom)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateTo()).equals(sdf.format(dateTo)))) {
				return false;
			}
			
		}
		for (QuickVehicleReservation vehicleReservation : quickReservations) {
			if (!(vehicleReservation.getDateFrom().after(dateTo) || vehicleReservation.getDateTo().after(dateFrom))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateFrom()).equals(sdf.format(dateTo)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateFrom()).equals(sdf.format(dateFrom)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateTo()).equals(sdf.format(dateFrom)))) {
				return false;
			}
			if((sdf.format(vehicleReservation.getDateTo()).equals(sdf.format(dateTo)))) {
				return false;
			}
		}
		return true;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean removeVehicle(Long id) {
		Vehicle v = findOne(id);
		if(v.getReservations().size()>0) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
	
	public String removeQuickReservation(QuickVehicleReserveBean bean) {
		QuickVehicleReservation quick = quickService.findOne(bean.getId());
		if(!quick.isTaken()) {
			quickService.remove(bean.getId());
			return "OK";
		}else {
			return "TAKEN";
		}
	}
	
	
}
