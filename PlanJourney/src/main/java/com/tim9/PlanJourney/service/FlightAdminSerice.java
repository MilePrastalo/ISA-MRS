package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.repository.FlightAdminRepository;

@Service
public class FlightAdminSerice {
	
	@Autowired
	FlightAdminRepository repository;
	
	public  FlightAdmin findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List< FlightAdmin> findAll() {
		return repository.findAll();
	}
	
	public Page< FlightAdmin> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public  FlightAdmin save( FlightAdmin  flightAdmin) {
		return repository.save( flightAdmin);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
