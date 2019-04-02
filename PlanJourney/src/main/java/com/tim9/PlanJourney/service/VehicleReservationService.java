package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Company;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.repository.VehicleReservationRepository;
@Service
public class VehicleReservationService {
	
	@Autowired
	private VehicleReservationRepository repository;
	
	public VehicleReservation findOne(Long id) {
		return repository.getOne(id);
	}

	public List<VehicleReservation> findAll() {
		return repository.findAll();
	}
	
	public VehicleReservation save(VehicleReservation reservation) {
		return repository.save(reservation);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
