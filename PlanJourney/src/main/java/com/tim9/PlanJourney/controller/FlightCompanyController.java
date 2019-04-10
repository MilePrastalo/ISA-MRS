package com.tim9.PlanJourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.FlightService;

@RestController
public class FlightCompanyController {
	private static FlightCompany fc;

	@Autowired
	private FlightCompanyService FCservice;
	@Autowired
	private DestinationService DestinationService;
	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "/api/getFlightCompanyProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany getFlightCompanyProfile() throws Exception {
		// prvo bi trebalo dobaviti sa sesije ulogovanog korisnika i uzeti njegovu
		// aviokompaniju

		FlightCompany flightC = FCservice.findByName("Avio");
		if (flightC == null) {
			Destination startDestination = DestinationService.findByName("Belgrade");
			DestinationService.save(startDestination);
			Destination endDestination = DestinationService.findByName("Moscow");
			DestinationService.save(endDestination);
			Flight flight = new Flight(new Date(), new Date(), 3, 6000, startDestination, endDestination,
					new HashSet<Ticket>(), new HashSet<Seat>(), 1000, 1000, 1000);
			flightService.save(flight);
			Set<Flight> flights = new HashSet<>();
			flights.add(flight);
			flightC = new FlightCompany("Avio", "address", "description", 0, new HashSet<FlightAdmin>(),
					new HashSet<Destination>(), flights, new HashSet<Ticket>());
			FCservice.save(flightC);
		}
		if (flightC.getFlights().size() == 0) {
			Destination startDestination = DestinationService.findByName("Belgrade");
			Destination endDestination = DestinationService.findByName("Moscow");
			Flight flight = new Flight(new Date(), new Date(), 3, 6000, startDestination, endDestination,
					new HashSet<Ticket>(), new HashSet<Seat>(), 1000, 1000, 1000);
			flightC.getFlights().add(flight);
			FCservice.save(flightC);
		}
		return flightC;
	}

	@RequestMapping(value = "/api/updateFlightCompanyProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightCompany updateFlightCompanyProfile(@RequestBody FlightCompany updatedFC)
			throws Exception {
		// FlightCompany fc = new FlightCompany(1l,
		// "FlightCompany","address","shdhasdhbsajknclklasnckldasnckakcnknckdnckdk");
		FlightCompany fc = FCservice.findByName("Avio");
		fc.setName(updatedFC.getName());
		fc.setAddress(updatedFC.getAddress());
		fc.setDescription(updatedFC.getDescription());
		FCservice.save(fc);
		return fc;
	}

	@RequestMapping(value = "/api/addDestination", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody Destination addDestination(Destination destInfo) throws Exception {

		// prvo bi trebalo dobaviti sa sesije ulogovanog korisnika i uzeti njegovu
		// aviokompaniju
		FlightCompany fc = FCservice.findByName("Avio");
		System.out.println("\tpozvan sam");
		System.out.println("\t" + destInfo.getCoordinates());
		System.out.println("\t" + destInfo.getDescription());
		System.out.println("\t" + destInfo.getName());
		// provera da li vec postoji destinacija

		destInfo = new Destination("a", "a", "a");
		Destination newDestination = new Destination(destInfo.getName(), destInfo.getDescription(),
				destInfo.getCoordinates());
		DestinationService.save(newDestination);
		fc.getDestinations().add(newDestination);
		FCservice.save(fc);
		return newDestination;

	}

	@RequestMapping(value = "/api/getFlights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Flight> getFlights() throws Exception {
		// treba mi prvo ulogovan korisnik da je flight admin i njegova avio kompanija i
		// onda da uzmem njene letove
		System.out.println("POZVAN SAM");
		FlightCompany flightC = FCservice.findByName("Avio");
		if (flightC == null) {
			flightC = new FlightCompany("Avio", "address", "description", 0, new HashSet<FlightAdmin>(),
					new HashSet<Destination>(), new HashSet<Flight>(), new HashSet<Ticket>());
			FCservice.save(flightC);
		}
		ArrayList<Flight> flights = new ArrayList<>();
		for (Flight d : flightC.getFlights()) {
			flights.add(d);
		}
		return flights;
	}

	@RequestMapping(value = "/api/getDestinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Destination> getDestinations() throws Exception {

		// treba mi prvo ulogovan korisnik da je flight admin i njegova avio kompanija i
		// onda da uzmem njene destinacije
		FlightCompany flightC = FCservice.findByName("Avio");
		if (flightC == null) {
			flightC = new FlightCompany("Avio", "address", "description", 0, new HashSet<FlightAdmin>(),
					new HashSet<Destination>(), new HashSet<Flight>(), new HashSet<Ticket>());
			FCservice.save(flightC);
		}
		ArrayList<Destination> destinations = new ArrayList<>();
		for (Destination d : flightC.getDestinations()) {
			destinations.add(d);
		}
		return destinations;
	}

	@RequestMapping(value = "/api/addFlightCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<FlightCompany> addFlightCompany(@RequestBody FlightCompany flightCompany) {

		if (FCservice.findByAddress(flightCompany.getAddress()) == null
				&& FCservice.findByName(flightCompany.getName()) == null) {
			FlightCompany f = (FlightCompany) FCservice.save(flightCompany);
			return new ResponseEntity<FlightCompany>(f, HttpStatus.OK);
		} else {
			return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeFlightCompany/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<FlightCompany> removeFlightCompany(@PathVariable("name") String name) {

		FlightCompany flightCompany = FCservice.findByName(name);

		if (flightCompany == null) {
			return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.CONFLICT);
		}

		FCservice.remove(flightCompany.getId());
		return new ResponseEntity<FlightCompany>(flightCompany, HttpStatus.OK);
	}

}
