package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.flight.QuickFlightReservation;
import com.tim9.PlanJourney.repository.QuickFlightReservationRepository;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false)
	public QuickFlightReservation save(QuickFlightReservation quickFlightReservation) {
		return repository.save(quickFlightReservation);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
