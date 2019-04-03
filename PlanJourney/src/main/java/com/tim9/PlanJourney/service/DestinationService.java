package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.repository.DestinationRepository;

@Service
public class DestinationService {
	
	@Autowired
	DestinationRepository repository;
	
	public Destination findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}
	
	public Destination findByName(String name) {
		return repository.findByName(name);
	}

	public List<Destination> findAll() {
		return repository.findAll();
	}
	
	public Page<Destination> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Destination save(Destination destination) {
		return repository.save(destination);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
