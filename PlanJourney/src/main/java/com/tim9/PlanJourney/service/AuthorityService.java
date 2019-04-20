package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	public Authority findOne(Long id) {
		return repository.getOne(id);
	}

	public List<Authority> findAll() {
		return repository.findAll();
	}

	public Authority save(Authority office) {
		return repository.save(office);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public Authority findByName(String name) {
		return repository.findByName(name);
	}
}
