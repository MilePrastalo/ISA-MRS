package com.tim9.PlanJourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.DestinationBean;
import com.tim9.PlanJourney.beans.FlightBean;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.UserService;

@RestController
public class FlightCompanyController {

	@Autowired
	private FlightCompanyService flightCompanyService;
	@Autowired
	private DestinationService destinationService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/api/getFlightCompanyProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	// Method returns flightCompany information
	public @ResponseBody FlightCompany getFlightCompanyProfile() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			return flightCompany;
		}
		return null;
	}

	@RequestMapping(value = "/api/updateFlightCompanyProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	@CrossOrigin()
	// Method for updating flight company profile, returns flight company object
	public @ResponseBody FlightCompany updateFlightCompanyProfile(@RequestBody FlightCompany updatedFC)
			throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			// set new info
			flightCompany.setName(updatedFC.getName());
			flightCompany.setAddress(updatedFC.getAddress());
			flightCompany.setDescription(updatedFC.getDescription());
			// save to database
			flightCompanyService.save(flightCompany);
			return flightCompany;
		}
		return null;
	}

	@RequestMapping(value = "/api/addDestination", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	// Method for adding new destination on which flight company operates
	public @ResponseBody Destination addDestination(@RequestBody DestinationBean destInfo) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			Destination newDestination = new Destination(destInfo.getName(), destInfo.getDescription(),
					destInfo.getCoordinates());
			destinationService.save(newDestination);
			flightCompany.getDestinations().add(newDestination);
			// update flight company
			flightCompanyService.save(flightCompany);
			return newDestination;
		}
		return null;
	}

	@RequestMapping(value = "/api/getFlights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	// Method returns flight information
	public @ResponseBody ArrayList<Flight> getFlights() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			ArrayList<Flight> flights = new ArrayList<>();
			for (Flight d : flightCompany.getFlights()) {
				flights.add(d);
			}
			return flights;
		}
		return null;
	}

	@RequestMapping(value = "/api/getDestinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	// Method returns list of destinations on which flight company operates
	public @ResponseBody ArrayList<Destination> getDestinations() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			ArrayList<Destination> destinations = new ArrayList<>();
			for (Destination d : flightCompany.getDestinations()) {
				destinations.add(d);
			}
			return destinations;
		}
		return null;
	}

	@RequestMapping(value = "/api/flightsInCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	// Method for searching flights in flight company
	public @ResponseBody ArrayList<Flight> flightsInCompany(@RequestBody FlightBean search) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			ArrayList<Flight> foundFlights = new ArrayList<>();
			for (Flight f : flightCompany.getFlights()) {
				if ((f.getStartDestination().getName().equals(search.getStartDestination())
						|| search.getStartDestination().equals(""))
						&& (f.getEndDestination().getName().equals(search.getEndDestination())
								|| search.getEndDestination().equals(""))
						&& (f.getEconomicPrice() >= search.getMinEconomic() || (search.getMinEconomic() == 0))
						&& (f.getBusinessPrice() >= search.getMinBusiness() || (search.getMinBusiness() == 0))
						&& (f.getFirstClassPrice() >= search.getMinFirstClass() || (search.getMinFirstClass() == 0))
						&& (f.getEconomicPrice() <= search.getMaxEconomic() || (search.getMaxEconomic() == 0))
						&& (f.getBusinessPrice() <= search.getMaxBusiness() || (search.getMaxBusiness() == 0))
						&& (f.getFirstClassPrice() <= search.getMaxFirstClass() || (search.getMaxFirstClass() == 0))
						&& (f.getFlightDuration() == search.getFlightDuration() || search.getFlightDuration() == 0)
						&& (f.getFlightLength() == search.getFlightLength() || search.getFlightLength() == 0)) {
					if (search.getStartDate() != null) {
						System.out.println("bio");
						if (f.getStartDate().equals(search.getStartDate())) {
							foundFlights.add(f);
							continue;
						}
					}
					if (search.getEndDate() != null) {
						if (f.getEndDate().equals(search.getEndDate())) {
							foundFlights.add(f);
							continue;
						}
					}
					foundFlights.add(f);
				}
			}
			return foundFlights;
		}
		return null;
	}

	// Method puts some test data into database
	@RequestMapping(value = "/api/testFlightData", method = RequestMethod.GET)
	@CrossOrigin()
	public void testData() throws Exception {

		Destination destinatinon1 = new Destination("Moscow", "description", "coords");
		Destination destinatinon2 = new Destination("Belgrade", "description", "coords");
		Destination destinatinon3 = new Destination("Paris", "description", "coords");
		Destination destinatinon4 = new Destination("New York", "description", "coords");
		Set<Destination> destinations = new HashSet<Destination>();
		destinations.add(destinatinon1);
		destinations.add(destinatinon2);
		destinations.add(destinatinon3);
		destinations.add(destinatinon4);

		Flight flight1 = new Flight(new Date(), new Date(), 3, 6000, destinatinon1, destinatinon2,
				new HashSet<Ticket>(), new HashSet<Seat>(), 100, 120, 140);
		Flight flight2 = new Flight(new Date(), new Date(), 5, 3000, destinatinon3, destinatinon4,
				new HashSet<Ticket>(), new HashSet<Seat>(), 546, 151, 84);
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		FlightCompany fc = new FlightCompany("Avio", "address", "description", 0, new HashSet<FlightAdmin>(),
				destinations, flights, new HashSet<Ticket>());
		flightCompanyService.save(fc);
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		FlightAdmin flightAdmin = new FlightAdmin("mira", bc.encode("miric"), "Mira", "Miric", "mira@gmail.com");
		Authority authority = (Authority) authorityService.findOne(2l);
		ArrayList<Authority> authorities = new ArrayList<>();
		authorities.add(authority);
		flightAdmin.setAuthorities(authorities);
		flightAdmin.setFlightCompany(fc);
		userService.save(flightAdmin);
	}

	@RequestMapping(value = "/api/addFlightCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ResponseEntity<FlightCompany> addFlightCompany(@RequestBody FlightCompany flightCompany) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (flightCompanyService.findByAddress(flightCompany.getAddress()) == null
					&& flightCompanyService.findByName(flightCompany.getName()) == null) {
				FlightCompany f = (FlightCompany) flightCompanyService.save(flightCompany);
				return new ResponseEntity<FlightCompany>(f, HttpStatus.OK);
			} else {
				return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/removeFlightCompany/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<FlightCompany> removeFlightCompany(@PathVariable("name") String name) {

		FlightCompany flightCompany = flightCompanyService.findByName(name);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (flightCompany == null) {
				return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
			}

			flightCompanyService.remove(flightCompany.getId());
			return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.OK);
		}
		return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/getAllFlightCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ArrayList<FlightCompany> getAllFlightCompanies() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			ArrayList<FlightCompany> fcs = (ArrayList<FlightCompany>) flightCompanyService.findAll();

			return fcs;
		}
		return null;
	}

}
