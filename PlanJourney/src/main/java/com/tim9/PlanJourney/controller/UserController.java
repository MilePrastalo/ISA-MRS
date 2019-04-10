package com.tim9.PlanJourney.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	UserService service;
	
	private static UserBean user;
	
	@RequestMapping(
			value = "/api/getLogUser",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody UserBean getUser() throws Exception {
		//FlightAdmin user = (FlightAdmin) service.findOneByUsername("pera");
		if(user==null) {
			user = new UserBean("username","pass","Pera","Peric","pera@gmail.com");
			
			//service.save(user);	
		}
		return user;
	}
	
	
	@RequestMapping(
			value = "/api/updateUserProfile",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody UserBean  updateUserProfile(@RequestBody UserBean updatedUser) throws Exception {
		//FlightAdmin user = (FlightAdmin) service.findOneByUsername("pera");
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setEmail(updatedUser.getEmail());
		user.setPassword(updatedUser.getPassword());
		//service.save(user);
		return user;
	}

}
