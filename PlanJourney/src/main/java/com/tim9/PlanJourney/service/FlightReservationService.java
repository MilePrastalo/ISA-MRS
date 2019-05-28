package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.repository.FlightReservationRepository;

@Service
@Transactional(readOnly = true)
public class FlightReservationService {
	
	
	@Autowired
	FlightReservationRepository  repository;

	public FlightReservation findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<FlightReservation> findAll() {
		return repository.findAll();
	}
	
	public Page<FlightReservation> findAll(Pageable page) {
		return repository.findAll(page);
	}
	@Transactional(readOnly = false)
	public FlightReservation save(FlightReservation reservation) {
		return repository.save(reservation);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
