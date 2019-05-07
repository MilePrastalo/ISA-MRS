package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.QuickFlightReservation;
import com.tim9.PlanJourney.repository.QuickFlightReservationRepository;

@Service
public class QuickFlightReservationService {
	
	@Autowired
	QuickFlightReservationRepository repository;
	
	public QuickFlightReservation findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<QuickFlightReservation> findAll() {
		return repository.findAll();
	}
	
	public Page<QuickFlightReservation> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public QuickFlightReservation save(QuickFlightReservation quickFlightReservation) {
		return repository.save(quickFlightReservation);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
