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

import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.service.FlightAdminSerice;
import com.tim9.PlanJourney.service.FlightCompanyService;

@RestController
public class FlightAdminController {

	@Autowired
	private FlightAdminSerice adminService;

	@Autowired
	private FlightCompanyService flightService;

	@RequestMapping(value = "/api/getFlightAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<FlightAdmin> getFlightAdmin(@PathVariable("username") String username) {

		FlightAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<FlightAdmin>(admin, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<FlightAdmin>(admin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addFlightAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<FlightAdmin> addFlightAdmin(@RequestBody FlightAdmin admin) {
		// Finds if new admin's given hotel exists.
		FlightCompany f = flightService.findByName(admin.getFlightCompany().getName());

		if (adminService.findByUsername(admin.getUsername()) == null && f != null) {
			FlightAdmin a = (FlightAdmin) adminService.save(admin);
			return new ResponseEntity<FlightAdmin>(a, HttpStatus.OK);
		} else {
			return new ResponseEntity<FlightAdmin>(admin, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeFlightAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<FlightAdmin> removeFlightAdmin(@PathVariable("username") String username) {

		FlightAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<FlightAdmin>(admin, HttpStatus.CONFLICT);
		}
		// If admin exists remove him from his hotel's admins list and remove him from
		// database.
		FlightCompany fc = flightService.findOne(admin.getFlightCompany().getId());
		for (FlightAdmin a : fc.getFlightAdmins()) {
			if (a.getId() == admin.getId()) {
				fc.getFlightAdmins().remove(a);
			}
		}
		adminService.remove(admin.getId());
		return new ResponseEntity<FlightAdmin>(admin, HttpStatus.OK);
	}

}
