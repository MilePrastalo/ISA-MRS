package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.repository.RentACarAdminRepository;

@Service
public class RentACarAdminService {
	@Autowired
	private RentACarAdminRepository repository;

	public RentACarAdmin findOne(Long id) {
		return repository.getOne(id);
	}

	public List<RentACarAdmin> findAll() {
		return repository.findAll();
	}

	public RentACarAdmin save(RentACarAdmin admin) {
		return repository.save(admin);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public RentACarAdmin findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
