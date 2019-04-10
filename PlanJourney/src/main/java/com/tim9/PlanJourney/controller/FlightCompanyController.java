package com.tim9.PlanJourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.service.FlightCompanyService;

@RestController
public class FlightCompanyController {
	private static FlightCompany fc;
	@Autowired
	private FlightCompanyService service;

	@RequestMapping(value = "/api/getFlightCompanyProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany getFlightCompanyProfile() throws Exception {
		// prvo bi trebalo dobaviti sa sesije ulogovanog korisnika i uzeti njegovu
		// aviokompaniju
		if (fc == null) {
			fc = new FlightCompany(1l, "FlightCompany", "address", "shdhasdhbsajknclklasnckldasnckakcnknckdnckdk", 1);
		}

		return fc;

	}

	@RequestMapping(value = "/api/updateFlightCompanyProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany updateUserProfile(@RequestBody FlightCompany updatedFC) throws Exception {
		// FlightCompany fc = new FlightCompany(1l,
		// "FlightCompany","address","shdhasdhbsajknclklasnckldasnckakcnknckdnckdk");
		fc.setName(updatedFC.getName());
		fc.setAddress(updatedFC.getAddress());
		fc.setDescription(updatedFC.getDescription());
		return fc;
	}

	@RequestMapping(value = "/api/addFlightCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<FlightCompany> addFlightCompany(@RequestBody FlightCompany flightCompany) {
		if (service.findByAddress(flightCompany.getAddress()) == null
				&& service.findByName(flightCompany.getName()) == null) {
			FlightCompany f = (FlightCompany) service.save(flightCompany);
			return new ResponseEntity<FlightCompany>(f, HttpStatus.OK);
		} else {
			return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeFlightCompany/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<FlightCompany> removeFlightCompany(@PathVariable("name") String name) {

		FlightCompany flightCompany = service.findByName(name);

		if (flightCompany == null) {
			return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
		}

		service.remove(flightCompany.getId());
		return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.OK);
	}

}
