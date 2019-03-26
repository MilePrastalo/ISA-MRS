package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.UserBean;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.User;

@RestController
public class UserController {
	
	
	@RequestMapping(
			value = "/api/getUser",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser() throws Exception {
		User user = new RegisteredUser(1,"username","pass","Pera","Peric","pera@gmail.com");
		return user;
	}
	
	
	@RequestMapping(
			value = "/api/updateUserProfile",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUserProfile(@RequestBody UserBean updatedUser) throws Exception {
		User user = new RegisteredUser(1,"username","pass","Pera","Peric","pera@gmail.com");
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setEmail(updatedUser.getEmail());
		user.setPassword(updatedUser.getPassword());
		return user;
	}

}
