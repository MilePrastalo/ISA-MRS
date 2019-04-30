package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.FriendRequest;
import com.tim9.PlanJourney.repository.FriendRequestRepository;

@Service
public class FriendRequestService {
	
	@Autowired
	private FriendRequestRepository repository;
	
	public FriendRequest findOne(Long id) {
		return repository.getOne(id);
	}

	public List<FriendRequest> findAll() {
		return repository.findAll();
	}

	public FriendRequest save(FriendRequest request) {
		return repository.save(request);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	


}
