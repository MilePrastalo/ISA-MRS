package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Company;
import com.tim9.PlanJourney.repository.CompanyRepository;


@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company findOne(Long id) {
		return companyRepository.getOne(id);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public void remove(Long id) {
		companyRepository.deleteById(id);
	}
}
