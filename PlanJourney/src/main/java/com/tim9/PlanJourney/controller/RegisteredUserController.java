package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.DestinationBean;
import com.tim9.PlanJourney.beans.FlightCompanyBean;
import com.tim9.PlanJourney.beans.FriendBean;
import com.tim9.PlanJourney.beans.FriendRequestBean;
import com.tim9.PlanJourney.beans.UserBean;
import com.tim9.PlanJourney.models.FriendRequest;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.RegisteredUserService;

@RestController
public class RegisteredUserController {

	@Autowired
	private RegisteredUserService service;
	@Autowired
	private FlightCompanyService companyService;
	

	// Method for searching and filtering registered users
	@RequestMapping(value = "/api/getRegUsers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<FriendBean> getRegistredUsers(@RequestBody UserBean searchRequest) {
		
		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		ArrayList<FriendBean> returnValue = new ArrayList<FriendBean>();
		ArrayList<RegisteredUser> users = (ArrayList<RegisteredUser>) service.findAll();
		for (RegisteredUser user : users) {

			if ((user.getFirstName().equals(searchRequest.getFirstName()) || searchRequest.getFirstName().equals(""))
					&& (user.getLastName().equals(searchRequest.getLastName())
							|| searchRequest.getLastName().equals(""))) {
				UserBean ub = new UserBean();
				ub.setFirstName(user.getFirstName());
				ub.setLastName(user.getLastName());
				ub.setUsername(user.getUsername());
				ub.setId(user.getId());
				if (isInList(user, getUserFriends(loggedUser))) {
					returnValue.add(new FriendBean(ub, "Remove"));
				} else if (isInRequests(user, loggedUser.getSendRequests(), true)) {
					returnValue.add(new FriendBean(ub, "Request sent"));
				} else if (isInRequests(user, loggedUser.getReceivedRequests(), false)) {
					returnValue.add(new FriendBean(ub, "Accept"));
				} else if (user.getId() != loggedUser.getId()) {
					returnValue.add(new FriendBean(ub, "Add"));
				}
			}
		}
		return returnValue;
	}

	// Method for searching and filtering user's friends
	@RequestMapping(value = "/api/getMyFriends", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<FriendBean> getMyFriends(@RequestBody UserBean searchRequest) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		ArrayList<FriendBean> returnValue = new ArrayList<FriendBean>();
		for (RegisteredUser user : getUserFriends(loggedUser)) {

			if ((user.getFirstName().equals(searchRequest.getFirstName()) || searchRequest.getFirstName().equals(""))
					&& (user.getLastName().equals(searchRequest.getLastName())
							|| searchRequest.getLastName().equals(""))) {
				UserBean ub = new UserBean();
				ub.setFirstName(user.getFirstName());
				ub.setLastName(user.getLastName());
				ub.setUsername(user.getUsername());
				ub.setId(user.getId());
				returnValue.add(new FriendBean(ub, "Remove"));
			}
		}
		return returnValue;
	}

	// Method returns all friendship requests of logged user
	@RequestMapping(value = "/api/getMyRequests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<FriendRequestBean> getMyRequests() {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		ArrayList<FriendRequestBean> retVal = new ArrayList<FriendRequestBean>();
		for (FriendRequest r : loggedUser.getReceivedRequests()) {
			if (r.isAccepted() == false) {
				retVal.add(new FriendRequestBean(r.getId(), r.getSender().getFirstName(), r.getSender().getLastName(),
						r.getSender().getUsername(), r.isAccepted()));
			}
		}
		return retVal;
	}

	// Method is supoused to send friendship request
	@RequestMapping(value = "/api/addFriend/{recieverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody boolean addFriend(@PathVariable("recieverId") Long recieverId) {

		RegisteredUser sender = getLoggedRegisteredUser();
		if (sender == null) {
			return false;
		}
		return service.addFriend(sender, recieverId);
	}

	// Method for accepting friend request
	@RequestMapping(value = "/api/acceptRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public boolean acceptRequest(@PathVariable("requestId") Long requestId) {

		RegisteredUser loggedReciever = getLoggedRegisteredUser();
		if (loggedReciever == null) {
			return false;
		}
		return service.acceptRequest(requestId);
	}

	// Method for accepting friend request, entry parameter is sender's id
	@RequestMapping(value = "/api/acceptRequestFromSearch/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public boolean acceptRequestFromSearch(@PathVariable("userId") Long userId) {

		RegisteredUser loggedReciever = getLoggedRegisteredUser();
		if (loggedReciever == null) {
			return false;
		}
		return service.acceptRequestFromSearch(loggedReciever, userId);
	}

	// Method which removes friendShip between logged user and user with given id
	@RequestMapping(value = "/api/removeFriend/{friendId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public boolean removeFriend(@PathVariable("friendId") Long friendId) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return false;
		}
		return service.removeFriend(loggedUser, friendId);
	}

	// Returns currently logged user
	private RegisteredUser getLoggedRegisteredUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			RegisteredUser user = (RegisteredUser) service.findByUsername(username);
			return user;
		}
		return null;
	}

	// friends
	private Set<RegisteredUser> getUserFriends(RegisteredUser loggedUser) {

		Set<RegisteredUser> friends = new HashSet<RegisteredUser>();
		for (FriendRequest req : loggedUser.getSendRequests()) {
			if (req.isAccepted()) {
				friends.add(req.getReciever());
			}
		}
		for (FriendRequest req : loggedUser.getReceivedRequests()) {
			if (req.isAccepted()) {
				friends.add(req.getSender());
			}
		}
		return friends;
	}
	
	@RequestMapping(value = "/api/getFlightCompanyProfileRegitered/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	// Method returns flightCompany information
	public @ResponseBody FlightCompanyBean getFlightCompanyProfileRegitered(@PathVariable("id") Long id) throws Exception {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		FlightCompany fc = companyService.findOne(id);
		FlightCompanyBean bean = new FlightCompanyBean(fc.getId(), fc.getName(), fc.getAddress(), fc.getDescription(), fc.getRating(), fc.getLaguageInfo(), fc.getSeatsConfiguration());
		ArrayList<DestinationBean> destinations = new ArrayList<>();
		for (Destination d : fc.getDestinations()) {
			destinations.add(new DestinationBean(d.getId(), d.getName(), d.getDescription(),
					d.getAddress(), d.getLongitude(), d.getLatitude(), d.getCity().getName()));
		}
		bean.setAirports(destinations);
		return bean;
	}


	// Method for checking if the user is in given list
	private boolean isInList(RegisteredUser subject, Set<RegisteredUser> friends) {

		for (RegisteredUser user : friends) {
			if (user.getId() == subject.getId()) {
				return true;
			}
		}
		return false;
	}

	// Method for checking if the user is in given list
	private boolean isInRequests(RegisteredUser subject, Set<FriendRequest> requests, boolean reciever) {

		if (reciever) {
			for (FriendRequest request : requests) {
				if (request.getReciever().getId() == subject.getId() && request.isAccepted() == false) {
					return true;
				}
			}
			return false;
		}
		for (FriendRequest request : requests) {
			if (request.getSender().getId() == subject.getId() && request.isAccepted() == false) {
				return true;
			}
		}
		return false;
	}

}
