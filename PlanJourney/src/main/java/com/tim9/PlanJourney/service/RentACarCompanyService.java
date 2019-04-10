package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.repository.RentACarCompanyRepository;

@Service
public class RentACarCompanyService {
	@Autowired
	private RentACarCompanyRepository repository;

	public RentACarCompany findOne(Long id) {
		return repository.getOne(id);
	}

	public List<RentACarCompany> findAll() {
		return repository.findAll();
	}

	public RentACarCompany save(RentACarCompany company) {
		return repository.save(company);
	}

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
