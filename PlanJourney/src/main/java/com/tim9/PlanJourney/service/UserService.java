package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tim9.PlanJourney.models.User;
import com.tim9.PlanJourney.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	
	public User findOne(Long id) {
		return repository.getOne(id);
	}
	public User findOneByUsername(String username) {
		return repository.findOneByUsername(username);
	}

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Page<User> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public User save(User user) {
		return repository.save(user);
	}

	public void remove(Long id) {
		//repository.delete(id);
	}

}
