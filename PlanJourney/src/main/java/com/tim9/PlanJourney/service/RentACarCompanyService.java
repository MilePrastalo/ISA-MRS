package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.repository.RentACarCompanyRepository;

@Service
@Transactional(readOnly = true)
public class RentACarCompanyService {
	@Autowired
	private RentACarCompanyRepository repository;

	public RentACarCompany findOne(Long id) {
		return repository.getOne(id);
	}

	public List<RentACarCompany> findAll() {
		return repository.findAll();
	}
	@Transactional(readOnly = false)
	public RentACarCompany save(RentACarCompany company) {
		return repository.save(company);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

	public RentACarCompany findByAddress(String address) {
		return repository.findByAddress(address);
	}

	public RentACarCompany findByName(String name) {
		return repository.findByName(name);
	}
}
