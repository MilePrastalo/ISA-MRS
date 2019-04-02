package com.tim9.PlanJourney.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tim9.PlanJourney.beans.UserBean;
import com.tim9.PlanJourney.models.Company;
import com.tim9.PlanJourney.models.FlightAdmin;
import com.tim9.PlanJourney.models.FlightCompany;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.User;


@RestController
public class FlightCompanyController {
	private static FlightCompany fc;
	
	
	@RequestMapping(
			value = "/api/getFlightCompanyProfile",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany getFlightCompanyProfile() throws Exception {		
		//prvo bi trebalo dobaviti sa sesije ulogovanog korisnika i uzeti njegovu aviokompaniju
		if(fc == null) {
			fc = new FlightCompany(1l, "FlightCompany","address","shdhasdhbsajknclklasnckldasnckakcnknckdnckdk");
		}
		return fc;
		
	}
	
	
	@RequestMapping(
			value = "/api/updateFlightCompanyProfile",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany updateUserProfile(@RequestBody FlightCompany updatedFC) throws Exception {
		//FlightCompany fc = new FlightCompany(1l, "FlightCompany","address","shdhasdhbsajknclklasnckldasnckakcnknckdnckdk");
		fc.setName(updatedFC.getName());
		fc.setAddress(updatedFC.getAddress());
		fc.setDescription(updatedFC.getDescription());	
		return fc;
	}

}
