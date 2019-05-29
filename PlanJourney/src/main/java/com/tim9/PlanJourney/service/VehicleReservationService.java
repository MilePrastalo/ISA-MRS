package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.Company;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.repository.VehicleReservationRepository;
@Service
@Transactional(readOnly = true)
public class VehicleReservationService {
	
	@Autowired
	private VehicleReservationRepository repository;
	
	public VehicleReservation findOne(Long id) {
		return repository.getOne(id);
	}

	public List<VehicleReservation> findAll() {
		return repository.findAll();
	}
	@Transactional(readOnly = false)
	public VehicleReservation save(VehicleReservation reservation) {
		return repository.save(reservation);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
