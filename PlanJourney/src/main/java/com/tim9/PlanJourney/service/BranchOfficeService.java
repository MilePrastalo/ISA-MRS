package com.tim9.PlanJourney.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.rentacar.BranchOffice;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.repository.BranchOfficeRepository;

@Service
@Transactional(readOnly = true)
public class BranchOfficeService {
	@Autowired
	private BranchOfficeRepository repository;
	
	@Autowired
	VehicleReservationService reservationService;
	
	public BranchOffice findOne(Long id) {
		return repository.getOne(id);
	}

	public List<BranchOffice> findAll() {
		return repository.findAll();
	}
	@Transactional(readOnly = false)
	public BranchOffice save(BranchOffice office) {
		return repository.save(office);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public boolean removeOffice(Long id) {
		BranchOffice office = findOne(id);
		ArrayList<VehicleReservation> reservations =(ArrayList<VehicleReservation>) reservationService.findAll();
		for (VehicleReservation vehicleReservation : reservations) {
			if(vehicleReservation.getOfficePick()==office || vehicleReservation.getOfficeReturn() == office) {
				return false;
			}
		}

		remove(id);
		return true;
	}

}
