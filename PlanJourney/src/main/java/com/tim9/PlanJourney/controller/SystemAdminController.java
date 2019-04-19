package com.tim9.PlanJourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.service.SystemAdminService;

@RestController
public class SystemAdminController {

	@Autowired
	private SystemAdminService service;

	@RequestMapping(value = "/api/getSystemAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<SystemAdmin> getSystemAdmin(@PathVariable("username") String username) {

		SystemAdmin admin = service.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<SystemAdmin>(admin, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<SystemAdmin>(admin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addSystemAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<SystemAdmin> addSystemAdmin(@RequestBody SystemAdmin admin) {

		if (service.findByUsername(admin.getUsername()) == null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			admin.setPassword(encoder.encode(admin.getPassword()));
			SystemAdmin a = (SystemAdmin) service.save(admin);
			return new ResponseEntity<SystemAdmin>(a, HttpStatus.OK);
		} else {
			return new ResponseEntity<SystemAdmin>(admin, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeSystemAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<SystemAdmin> removeSystemAdmin(@PathVariable("username") String username) {

		SystemAdmin admin = service.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<SystemAdmin>(admin, HttpStatus.CONFLICT);
		}

		service.remove(admin.getId());
		return new ResponseEntity<SystemAdmin>(admin, HttpStatus.OK);
	}

}