package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.repository.RegisteredUserRepository;

@Service
@Transactional(readOnly = true)
public class RegisteredUserService {

	
	@Autowired
	private RegisteredUserRepository repository;
	
	public RegisteredUser findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}
	
	public RegisteredUser findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public List<RegisteredUser> findAll() {
		return repository.findAll();
	}
	
	public Page<RegisteredUser> findAll(Pageable page) {
		return repository.findAll(page);
	}

	@Transactional(readOnly = false)
	public RegisteredUser save(RegisteredUser user) {
		return repository.save(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
}
