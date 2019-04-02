package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.rentacar.BranchOffice;
import com.tim9.PlanJourney.repository.BranchOfficeRepository;

@Service
public class BranchOfficeService {
	@Autowired
	private BranchOfficeRepository repository;
	
	public BranchOffice findOne(Long id) {
		return repository.getOne(id);
	}

	public List<BranchOffice> findAll() {
		return repository.findAll();
	}
	
	public BranchOffice save(BranchOffice office) {
		return repository.save(office);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
