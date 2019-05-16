package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.Passanger;
import com.tim9.PlanJourney.repository.PassangerRepository;

@Service
public class PassangerService {
	
	@Autowired
	PassangerRepository repository;
	
	public Passanger findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Passanger> findAll() {
		return repository.findAll();
	}
	
	public Page<Passanger> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Passanger save(Passanger passanger) {
		return repository.save(passanger);
	}
	
	public List<Passanger> saveAll(List<Passanger> passangers) {
		return repository.saveAll(passangers);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
