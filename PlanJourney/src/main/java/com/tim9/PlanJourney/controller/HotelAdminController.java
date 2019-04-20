package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.HotelService;

@RestController
public class HotelAdminController {

	@Autowired
	private HotelAdminService adminService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/api/getHotelAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<HotelAdmin> getHotelAdmin(@PathVariable("username") String username) {

		HotelAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addHotelAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ResponseEntity<HotelAdmin> addHotelAdmin(@RequestBody HotelAdmin admin) {
		// Finds if new admin's given hotel exists.
		Hotel h = hotelService.findByName(admin.getHotel().getName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (adminService.findByUsername(admin.getUsername()) == null && h != null) {
				Authority authority = authorityService.findByName("HOTEL_ADMIN");
				ArrayList<Authority> auth = new ArrayList<Authority>();
				auth.add(authority);
				admin.setAuthorities(auth);

				admin.setHotel(h);
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				admin.setPassword(encoder.encode(admin.getPassword()));
				HotelAdmin a = (HotelAdmin) adminService.save(admin);
				return new ResponseEntity<HotelAdmin>(a, HttpStatus.OK);
			} else {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/removeHotelAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<HotelAdmin> removeHotelAdmin(@PathVariable("username") String username) {

		HotelAdmin admin = adminService.findByUsername(username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Checks if admin exists.
			if (admin == null) {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}

			adminService.remove(admin.getId());
			return new ResponseEntity<HotelAdmin>(admin, HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

}
