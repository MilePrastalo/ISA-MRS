package com.tim9.PlanJourney.controller;

import java.beans.DesignMode;
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

import com.tim9.PlanJourney.beans.DestinationBean;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.SystemAdminService;
import com.tim9.PlanJourney.service.UserService;

@RestController
public class SystemAdminController {

	@Autowired
	private SystemAdminService service;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private DestinationService destinationService;

	@RequestMapping(value = "/api/getSystemAdminProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	// Method returns system admin information
	public @ResponseBody SystemAdmin getSystemAdminProfile() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			SystemAdmin user = (SystemAdmin) userService.findOneByUsername(username);

			return user;
		}
		return null;
	}

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
			Authority authority = authorityService.findByName("SYS_ADMIN");
			ArrayList<Authority> auth = new ArrayList<Authority>();
			auth.add(authority);
			admin.setAuthorities(auth);
			admin.setConfirmed(true);
			admin.setLoggedBefore(true);
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

	@RequestMapping(value = "/api/getAllDestinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ArrayList<DestinationBean> getAllDestinations() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ArrayList<DestinationBean> destinationBeans = new ArrayList<DestinationBean>();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			ArrayList<Destination> destinations = (ArrayList<Destination>) destinationService.findAll();

			for (Destination d : destinations) {
				DestinationBean db = new DestinationBean();
				db.setId(d.getId());
				db.setName(d.getName());
				db.setCoordinates(d.getCoordinates());
				db.setDescription(d.getDescription());
				destinationBeans.add(db);
			}

			return destinationBeans;
		}
		return destinationBeans;
	}

	@RequestMapping(value = "/api/sysAdminAddDestination", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody Long sysAdminAddDestination(@RequestBody DestinationBean destinationBean) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Returns -4 if given destination name is null or empty.
			if (destinationBean.getName() == null || destinationBean.getName().equals("".trim())) {
				return (long) -4;
			}
			// Returns -5 if given destination coordinates are null or empty.
			if (destinationBean.getCoordinates() == null || destinationBean.getCoordinates().equals("".trim())) {
				return (long) -5;
			}

			ArrayList<Destination> destinations = (ArrayList<Destination>) destinationService.findAll();
			for (Destination d : destinations) {
				// Returns -1 if destinations with given name already exists.
				if (d.getName().equals(destinationBean.getName())) {
					return (long) -1;
				}
				// Return -2 if destination with given coordinates already exists.
				if (d.getCoordinates().equals(destinationBean.getCoordinates())) {
					return (long) -2;
				}
			}

			Destination newDestination = new Destination();
			newDestination.setName(destinationBean.getName());
			newDestination.setCoordinates(destinationBean.getCoordinates());
			newDestination.setDescription(destinationBean.getDescription());

			newDestination = destinationService.save(newDestination);

			return newDestination.getId();
		}
		// Returns -3 if there was an error with authority.
		return (long) -3;
	}

	@RequestMapping(value = "/api/sysAdminEditDestination", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody Long sysAdminEditDestination(@RequestBody DestinationBean destinationBean) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Returns -4 if given destination name is null or empty.
			if (destinationBean.getName() == null || destinationBean.getName().equals("".trim())) {
				return (long) -4;
			}
			// Returns -5 if given destination coordinates are null or empty.
			if (destinationBean.getCoordinates() == null || destinationBean.getCoordinates().equals("".trim())) {
				return (long) -5;
			}

			Destination dest = null;
			ArrayList<Destination> destinations = (ArrayList<Destination>) destinationService.findAll();
			for (Destination d : destinations) {
				// Returns -1 if destinations with given name already exists.
				if (d.getName().equals(destinationBean.getName()) && d.getId() != destinationBean.getId()) {
					return (long) -1;
				}
				// Return -2 if destination with given coordinates already exists.
				if (d.getCoordinates().equals(destinationBean.getCoordinates())
						&& d.getId() != destinationBean.getId()) {
					return (long) -2;
				}
				if (d.getId() == destinationBean.getId()) {
					dest = d;
				}
			}
			// Returns -6 if destination with given ID does not exist.
			if (dest == null) {
				return (long) -6;
			}

			dest.setName(destinationBean.getName());
			dest.setCoordinates(destinationBean.getCoordinates());
			dest.setDescription(destinationBean.getDescription());

			dest = destinationService.save(dest);

			return dest.getId();
		}
		// Returns -3 if there was an error with authority.
		return (long) -3;
	}

}