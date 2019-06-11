package com.tim9.PlanJourney.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.beans.VehicleReservationSearchBean;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.rentacar.BranchOffice;
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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public VehicleReservation reserve(VehicleReservationSearchBean search, RegisteredUser user) throws ParseException {
		
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
		reservationService.save(reservation);
		user.getVehicleReservations().add(reservation);
		vehicle.getReservations().add(reservation);
		company.getReservations().add(reservation);
		companyService.save(company);
		userService.save(user);
		save(vehicle);
		VehicleReservation reser=  reservationService.save(reservation);
		return reser;
	}
}
