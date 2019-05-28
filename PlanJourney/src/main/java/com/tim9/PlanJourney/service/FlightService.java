package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.repository.FllightRepository;

@Service
@Transactional(readOnly = true)
public class FlightService {
	
	@Autowired
	FllightRepository repository;
	
	public Flight findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Flight> findAll() {
		return repository.findAll();
	}
	
	public Page<Flight> findAll(Pageable page) {
		return repository.findAll(page);
	}

	@Transactional(readOnly = false)
	public Flight save(Flight flight) {
		return repository.save(flight);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
