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

import com.tim9.PlanJourney.beans.DestinationBean;
import com.tim9.PlanJourney.beans.EditCityBean;
import com.tim9.PlanJourney.beans.RemoveAdminBean;
import com.tim9.PlanJourney.beans.SystemAdminBean;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.City;
import com.tim9.PlanJourney.models.Discounts;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.CityService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.DiscountsService;
import com.tim9.PlanJourney.service.FlightAdminSerice;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.RentACarAdminService;
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

	@Autowired
	private CityService cityService;

	@Autowired
	private HotelAdminService hotelAdminService;

	@Autowired
	private FlightAdminSerice flightAdminService;

	@Autowired
	private RentACarAdminService racAdminService;

	@Autowired
	private DiscountsService discountsService;

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
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean addSystemAdmin(@RequestBody SystemAdminBean admin) {

		if (admin.getUsername() == null || admin.getUsername().equals("".trim())) {
			return false;
		}
		if (admin.getFirstName() == null || admin.getFirstName().equals("".trim())) {
			return false;
		}
		if (admin.getLastName() == null || admin.getLastName().equals("".trim())) {
			return false;
		}
		if (admin.getEmail() == null || admin.getEmail().equals("".trim())) {
			return false;
		}
		if (admin.getPassword() == null || admin.getPassword().equals("".trim())) {
			return false;
		}

		if (service.findByUsername(admin.getUsername()) == null) {
			SystemAdmin newSystemAdmin = new SystemAdmin();

			newSystemAdmin.setUsername(admin.getUsername());
			newSystemAdmin.setFirstName(admin.getFirstName());
			newSystemAdmin.setLastName(admin.getLastName());
			newSystemAdmin.setEmail(admin.getEmail());
			Authority authority = authorityService.findByName("SYS_ADMIN");
			ArrayList<Authority> auth = new ArrayList<Authority>();
			auth.add(authority);
			newSystemAdmin.setAuthorities(auth);
			newSystemAdmin.setConfirmed(true);
			newSystemAdmin.setLoggedBefore(true);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			newSystemAdmin.setPassword(encoder.encode(admin.getPassword()));
			service.save(newSystemAdmin);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/api/removeSystemAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
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

	@RequestMapping(value = "/api/getAllCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAnyAuthority('SYS_ADMIN','FLIGHT_ADMIN','RENT_ADMIN')")
	public @ResponseBody ArrayList<City> getAllCities() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ArrayList<City> cities = new ArrayList<City>();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			cities = (ArrayList<City>) cityService.findAll();
		}
		return cities;
	}

	@RequestMapping(value = "/api/addCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean getCity(@RequestBody City city) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (city.getName() == null || city.getName().equals("".trim())) {
			return false;
		}

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			ArrayList<City> cities = (ArrayList<City>) cityService.findAll();

			for (City c : cities) {
				if (c.getName().equals(city.getName())) {
					return false;
				}
			}

			cityService.save(city);

			return true;
		}
		return false;
	}

	@RequestMapping(value = "/api/editCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean editCity(@RequestBody EditCityBean city) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (city.getOldName() == null || city.getOldName().equals("".trim())) {
			return false;
		}

		if (city.getNewName() == null || city.getNewName().equals("".trim())) {
			return false;
		}

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			ArrayList<City> cities = (ArrayList<City>) cityService.findAll();
			// Checks if city with old name exists.
			City foundCity = null;
			for (City c : cities) {
				if (c.getName().equals(city.getOldName())) {
					foundCity = c;
					break;
				}
			}
			if (foundCity == null) {
				return false;
			}

			// Checks if there is already a city with new name
			for (City c : cities) {
				if (c.getName().equals(city.getNewName())) {
					return false;
				}
			}
			foundCity.setName(city.getNewName());

			cityService.save(foundCity);

			return true;
		}
		return false;
	}

	@RequestMapping(value = "/api/getAllHotelAdmins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ArrayList<RemoveAdminBean> getAllHotelAdmins() {
		ArrayList<HotelAdmin> hotelAdmins = (ArrayList<HotelAdmin>) hotelAdminService.findAll();
		ArrayList<RemoveAdminBean> rabs = new ArrayList<RemoveAdminBean>();

		for (HotelAdmin a : hotelAdmins) {
			RemoveAdminBean rab = new RemoveAdminBean();
			rab.setUsername(a.getUsername());
			rab.setFirstName(a.getFirstName());
			rab.setLastName(a.getLastName());
			rab.setHotelName(a.getHotel().getName());

			rabs.add(rab);
		}

		return rabs;
	}

	@RequestMapping(value = "/api/getAllFlightAdmins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ArrayList<RemoveAdminBean> getAllFlightAdmins() {
		ArrayList<FlightAdmin> flightAdmins = (ArrayList<FlightAdmin>) flightAdminService.findAll();
		ArrayList<RemoveAdminBean> rabs = new ArrayList<RemoveAdminBean>();

		for (FlightAdmin a : flightAdmins) {
			RemoveAdminBean rab = new RemoveAdminBean();
			rab.setUsername(a.getUsername());
			rab.setFirstName(a.getFirstName());
			rab.setLastName(a.getLastName());
			rab.setHotelName(a.getFlightCompany().getName());

			rabs.add(rab);
		}

		return rabs;
	}

	@RequestMapping(value = "/api/getAllRACAdmins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ArrayList<RemoveAdminBean> getAllRACAdmins() {
		ArrayList<RentACarAdmin> racAdmins = (ArrayList<RentACarAdmin>) racAdminService.findAll();
		ArrayList<RemoveAdminBean> rabs = new ArrayList<RemoveAdminBean>();

		for (RentACarAdmin a : racAdmins) {
			RemoveAdminBean rab = new RemoveAdminBean();
			rab.setUsername(a.getUsername());
			rab.setFirstName(a.getFirstName());
			rab.setLastName(a.getLastName());
			rab.setHotelName(a.getService().getName());

			rabs.add(rab);
		}

		return rabs;
	}

	@RequestMapping(value = "/api/getDiscounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody Discounts getDiscounts() {
		Discounts discounts = discountsService.findOne(1L);

		// if discounts does not exist initiate them.
		if (discounts == null) {
			Discounts newDiscounts = new Discounts(0, 0, 0, 0, 0, 0);
			newDiscounts.setId(1L);
			discountsService.save(newDiscounts);

			return newDiscounts;
		}

		return discounts;
	}

	@RequestMapping(value = "/api/editDiscounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean editDiscounts(@RequestBody Discounts discounts) throws Exception {
		if (discounts.getNumberOfFlightReservations() < 0) {
			return false;
		}
		if (discounts.getNumberOfHotelReservations() < 0) {
			return false;
		}
		if (discounts.getNumberOfRACReservations() < 0) {
			return false;
		}
		if (discounts.getFlightDiscount() < 0) {
			return false;
		}
		if (discounts.getHotelDiscount() < 0) {
			return false;
		}
		if (discounts.getRentACarDiscount() < 0) {
			return false;
		}

		discounts.setId(1L);
		discountsService.save(discounts);

		return true;
	}
}