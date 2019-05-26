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
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.RentACarAdminService;
import com.tim9.PlanJourney.service.RentACarCompanyService;

@RestController
public class RentACarAdminController {

	@Autowired
	private RentACarAdminService adminService;

	@Autowired
	private RentACarCompanyService companyService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/api/getRentACarAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<RentACarAdmin> getRentACarAdmin(@PathVariable("username") String username) {

		RentACarAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addRentACarAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean addRentACarAdmin(@RequestBody AdminBean admin) {
		// Finds if new admin's given hotel exists.
		RentACarCompany r = companyService.findByName(admin.getCompanyName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (adminService.findByUsername(admin.getUsername()) == null && r != null) {
				Authority authority = authorityService.findByName("RENT_ADMIN");
				ArrayList<Authority> auth = new ArrayList<Authority>();
				auth.add(authority);
				RentACarAdmin rentAdmin = new RentACarAdmin();
				rentAdmin.setUsername(admin.getUsername());
				rentAdmin.setFirstName(admin.getFirstName());
				rentAdmin.setLastName(admin.getLastName());
				rentAdmin.setEmail(admin.getEmail());
				rentAdmin.setAuthorities(auth);
				rentAdmin.setService(r);
				rentAdmin.setConfirmed(false);

				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				admin.setPassword(encoder.encode(admin.getPassword()));
				rentAdmin.setPassword(encoder.encode(admin.getPassword()));

				adminService.save(rentAdmin);

				try {
					emailService.sendRegistrationEmail(rentAdmin); // sends email
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

	@RequestMapping(value = "/api/removeRentACarAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<RentACarAdmin> removeRentACarAdmin(@PathVariable("username") String username) {

		RentACarAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (admin == null) {
				return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
			}

			adminService.remove(admin.getId());
			return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.OK);
		}
		return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
	}

}
