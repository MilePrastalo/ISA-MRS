package com.tim9.PlanJourney.controller;
import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.FlightBean;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.FlightService;

@RestController
public class FlifgtController {
	
	@Autowired
	FlightService flightService;
	@Autowired
	DestinationService destinationService;
	
	
	@RequestMapping(
			value = "/api/getDestinations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<String> getProducers() throws Exception {
		//destinationService.findAll()
		ArrayList<String> destinations = new ArrayList<>();
		destinations.add("New York");
		destinations.add("Belgrade");
		destinations.add("Paris");
		destinations.add("London");
		destinations.add("Lisabon");
		destinations.add("Moscow");
		return destinations;
	}
	
	@RequestMapping(
			value = "/api/addFlight",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody Flight addFlight(@RequestBody FlightBean newFlightInfo) throws Exception {
		
		//security checks on backend side
		Date endDate = newFlightInfo.getEndDate();
		Date startDate = newFlightInfo.getStartDate();
		
		if (endDate.before(startDate)) {
			return null;
		}
		Date today = new Date();
		if (startDate.before(today) || endDate.before(today)) {
			return null;
		}
		Destination startDestination = new Destination(newFlightInfo.getStartDestination(), "opis","coords");
		Destination endDestination = new Destination(newFlightInfo.getEndDestination(), "opis","coords");
		//Destination startDestination = destinationService.findByName(newFlight.getStartDestination)
		//Destination endDestination = destinationService.findByName(newFlight.getEndDestination)
		if (! startDestination.getNaziv().equals(endDestination.getNaziv())) {
			return null;
		}
		
		Flight newFlight = new Flight(startDate, endDate,newFlightInfo.getFlightDuration(), newFlightInfo.getFlightLength(),
				startDestination, endDestination, new HashSet<Ticket>(), new HashSet<Seat>(), 
				newFlightInfo.getBusinessPrice(), newFlightInfo.getEconomicPrice(), newFlightInfo.getFirstClassPrice());;
		
		//flightService.save(newFlight)
		return newFlight;
	}

}
