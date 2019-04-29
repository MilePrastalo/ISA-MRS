package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.UserBean;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.User;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/api/getLogUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAnyAuthority('FLIGHT_ADMIN','SYS_ADMIN','HOTEL_ADMIN','REGISTERED','RENT_ADMIN')")
	// Method returns logged user info
	public @ResponseBody UserBean getUser() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			User user = (User) userService.findOneByUsername(username);
			UserBean ub = new UserBean(user.getUsername(), "", user.getFirstName(), user.getLastName(), user.getEmail());

			return ub;
		}
		return null;
	}

	@RequestMapping(value = "/api/updateUserProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAnyAuthority('FLIGHT_ADMIN','SYS_ADMIN','FLIGHT_ADMIN','REGISTERED','RENT_ADMIN')")
	// Method for changing user profile information
	public @ResponseBody User updateUserProfile(@RequestBody UserBean updatedUser) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			User user = (User) userService.findOneByUsername(username);
			user.setFirstName(updatedUser.getFirstName());
			user.setLastName(updatedUser.getLastName());
			user.setEmail(updatedUser.getEmail());
			
			if (updatedUser.getPassword().equals(updatedUser.getRepeatedPass()) == false) {
				System.out.println("aaaaaaaaaa");
				return null;
			}
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			if (! updatedUser.getPassword().equals("")) {
				user.setPassword(bc.encode(updatedUser.getPassword()));
			}
			
			userService.save(user);
			return user;
		}
		return null;
	}
	@RequestMapping(value = "/api/getUserRole", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@CrossOrigin()
	public @ResponseBody String updateUserRole() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			User user = (User) userService.findOneByUsername(username);
			if (user instanceof RegisteredUser) {
				return "REGISTERED";
			}
			else if (user instanceof FlightAdmin) {
				return "FLIGHT_ADMIN";
			}
			else if (user instanceof SystemAdmin) {
				return "SYS_ADMIN";
			}
			else if (user instanceof HotelAdmin) {
				return "HOTEL_ADMIN";
			}
			else if (user instanceof RentACarAdmin) {
				return "RENT_ADMIN";
			}
			/*List<GrantedAuthority> aut = (List<GrantedAuthority>) user.getAuthorities();
			return ((Authority)aut.get(0)).getName();*/
		}
		return null;
	}

}
