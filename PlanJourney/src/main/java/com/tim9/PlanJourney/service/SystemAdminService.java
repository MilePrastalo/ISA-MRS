package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.repository.SystemAdminRepository;

@Service
public class SystemAdminService {
	@Autowired
	private SystemAdminRepository repository;

	public SystemAdmin findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<SystemAdmin> findAll() {
		return repository.findAll();
	}

	public Page<SystemAdmin> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public SystemAdmin save(SystemAdmin systemAdmin) {
		return repository.save(systemAdmin);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public SystemAdmin findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
