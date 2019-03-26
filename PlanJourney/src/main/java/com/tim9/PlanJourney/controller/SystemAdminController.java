package com.tim9.PlanJourney.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.Hotel;
import com.tim9.PlanJourney.service.SystemAdminService;

@RestController
public class SystemAdminController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SystemAdminService systemAdminService;
	
	
	@RequestMapping(
			value = "/api/getAllHotels",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hotel>> getAllHotels() {
		logger.info("> getAllHotels");
		
		Collection<Hotel> hotels = systemAdminService.getAllHotels();
		
		logger.info("< getAllHotels");
		
		return new ResponseEntity<Collection<Hotel>>(hotels,HttpStatus.OK);
	}
	
}
