package com.tim9.PlanJourney.controller;

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
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.User;
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

			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			user.setPassword(bc.encode(updatedUser.getPassword()));
			userService.save(user);
			return user;
		}
		return null;
	}

}
