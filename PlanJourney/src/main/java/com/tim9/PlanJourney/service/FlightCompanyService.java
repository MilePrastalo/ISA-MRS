package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.repository.FlightCompanyRepository;

@Service
@Transactional(readOnly = true)
public class FlightCompanyService {

	@Autowired
	FlightCompanyRepository repository;

	public FlightCompany findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<FlightCompany> findAll() {
		return repository.findAll();
	}

	public Page<FlightCompany> findAll(Pageable page) {
		return repository.findAll(page);
	}
	
	@Transactional(readOnly = false)
	public FlightCompany save(FlightCompany fc) {
		return repository.save(fc);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

	public FlightCompany findByAddress(String address) {
		return repository.findByAddress(address);
	}

	public FlightCompany findByName(String name) {
		return repository.findByName(name);
	}

}
