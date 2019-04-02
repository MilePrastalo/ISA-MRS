package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.repository.FllightRepository;

@Service
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

	public Flight save(Flight flight) {
		return repository.save(flight);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
