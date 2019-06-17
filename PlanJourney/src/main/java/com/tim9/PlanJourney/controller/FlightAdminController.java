package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.AdminBean;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.FlightAdminSerice;
import com.tim9.PlanJourney.service.FlightCompanyService;

@RestController
public class FlightAdminController {

	@Autowired
	private FlightAdminSerice adminService;

	@Autowired
	private FlightCompanyService flightService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private EmailService emailService;

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
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean addFlightAdmin(@RequestBody AdminBean admin) {
		// Finds if new admin's given hotel exists.
		FlightCompany f = flightService.findByName(admin.getCompanyName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (adminService.findByUsername(admin.getUsername()) == null && f != null) {
				Authority authority = authorityService.findByName("FLIGHT_ADMIN");
				ArrayList<Authority> auth = new ArrayList<Authority>();
				auth.add(authority);
				FlightAdmin flightAdmin = new FlightAdmin();

				flightAdmin.setUsername(admin.getUsername());
				flightAdmin.setFirstName(admin.getFirstName());
				flightAdmin.setLastName(admin.getLastName());
				flightAdmin.setEmail(admin.getEmail());
				flightAdmin.setFlightCompany(f);

				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				flightAdmin.setPassword(encoder.encode(admin.getPassword()));
				flightAdmin.setAuthorities(auth);
				flightAdmin.setConfirmed(false);
				flightAdmin.setLoggedBefore(false);
				adminService.save(flightAdmin);

				try {
					emailService.sendRegistrationEmail(flightAdmin); // sends email
				} catch (MailException | InterruptedException e) {
					e.printStackTrace();
				}

				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@RequestMapping(value = "/api/removeFlightAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<FlightAdmin> removeFlightAdmin(@PathVariable("username") String username) {

		FlightAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<FlightAdmin>(admin, HttpStatus.CONFLICT);
		}

		adminService.remove(admin.getId());
		return new ResponseEntity<FlightAdmin>(admin, HttpStatus.OK);
	}

}
