package com.tim9.PlanJourney.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.FriendRequest;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.repository.RegisteredUserRepository;

@Service
@Transactional(readOnly = true)
public class RegisteredUserService {

	
	@Autowired
	private RegisteredUserRepository repository;
	@Autowired
	private FriendRequestService friendReqestsService;
	
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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean addFriend(RegisteredUser sender, Long recieverId) {

		RegisteredUser reciever = findOne(recieverId);
		// creating request
		FriendRequest request = new FriendRequest(sender, reciever, false);
		sender.getSendRequests().add(request);
		reciever.getReceivedRequests().add(request);
		friendReqestsService.save(request);
		save(sender);
		save(reciever);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean acceptRequest(Long requestId) {

		FriendRequest request = friendReqestsService.findOne(requestId);
		request.setAccepted(true);
		friendReqestsService.save(request);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean acceptRequestFromSearch(RegisteredUser loggedReciever, Long userId) {

		RegisteredUser sender = findOne(userId);
		FriendRequest request = null;
		for (FriendRequest req : sender.getSendRequests()) {
			if (req.getReciever().getId() == loggedReciever.getId()) {
				request = req;
				break;
			}
		}
		request.setAccepted(true);
		friendReqestsService.save(request);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean removeFriend(RegisteredUser loggedUser, Long friendId) {

		ArrayList<FriendRequest> requests = (ArrayList<FriendRequest>) friendReqestsService.findAll();
		for (FriendRequest req : requests) {
			if (req.getSender().getId() == loggedUser.getId() && req.getReciever().getId() == friendId) {
				loggedUser.getSendRequests().remove(req);
				friendReqestsService.remove(req.getId());
				return true;
			}
			if (req.getReciever().getId() == loggedUser.getId() && req.getSender().getId() == friendId) {
				loggedUser.getReceivedRequests().remove(req);
				friendReqestsService.remove(req.getId());
				return true;
			}
		}
		return false;
	}
}
