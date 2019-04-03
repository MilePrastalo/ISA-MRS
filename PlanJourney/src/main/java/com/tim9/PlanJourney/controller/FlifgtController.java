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
	public @ResponseBody ArrayList<String> getDestinations() throws Exception {
		
		//adding to datebase because there is no destinations yet
		
		
		ArrayList<String> destinationNames = new ArrayList<>();
		ArrayList<Destination> destinations_ = new ArrayList<>();
		destinations_ = (ArrayList<Destination>) destinationService.findAll();
		if (destinations_.size() == 0) {
			System.out.println("\tUSAO");
			Destination dest1 = new Destination("Belgrade", "opis","coords");
			destinationService.save(dest1);
			Destination dest2 = new Destination("Moscow", "opis","coords");
			destinationService.save(dest2);
			destinations_ = (ArrayList<Destination>) destinationService.findAll();
		}
		
		for (Destination dest : destinations_) {
			destinationNames.add(dest.getName());
		}
		return destinationNames;
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
			System.out.println("\tend pre start");
			return null;
		}
		Date today = new Date();
		if (startDate.before(today) || endDate.before(today)) {
			System.out.println("\tpre danasnjeg");
			return null;
		}
		
		if ( newFlightInfo.getStartDestination().equals(newFlightInfo.getEndDestination())) {
			System.out.println("\tiste destinacije");
			return null;
		}
		
		Destination startDestination = destinationService.findByName(newFlightInfo.getStartDestination());
		Destination endDestination = destinationService.findByName(newFlightInfo.getEndDestination());
		if ( startDestination == null || endDestination == null) {
			return null;
		}
		
		Flight newFlight = new Flight(startDate, endDate,newFlightInfo.getFlightDuration(), newFlightInfo.getFlightLength(),
				startDestination, endDestination, new HashSet<Ticket>(), new HashSet<Seat>(), 
				newFlightInfo.getBusinessPrice(), newFlightInfo.getEconomicPrice(), newFlightInfo.getFirstClassPrice());;
		
		flightService.save(newFlight);
		return newFlight;
	}

}
