package com.tim9.PlanJourney.controller;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.lenient;

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
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.FlightService;

@RestController
public class FlifgtController {
	
	@Autowired
	FlightService flightService;
	@Autowired
	DestinationService destinationService;
	@Autowired
	FlightCompanyService flightCompanyService;
	
	
	
	
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
		
		if ( newFlightInfo.getStartDestination().equals(newFlightInfo.getEndDestination())) {
			System.out.println("\tiste destinacije");
			return null;
		}
		
		Destination startDestination = destinationService.findByName(newFlightInfo.getStartDestination());
		Destination endDestination = destinationService.findByName(newFlightInfo.getEndDestination());
		if ( startDestination == null || endDestination == null) {
			return null;
		}
		
		Set<Seat> seats = new HashSet<Seat>();
		makeSeats(seats, newFlightInfo.getEconomicCapacity(), "economic");
		makeSeats(seats, newFlightInfo.getBuisinesssCapacity(), "business");
		makeSeats(seats, newFlightInfo.getFirstClassCapacity(), "first class");
		
		Flight newFlight = new Flight(startDate, endDate,newFlightInfo.getFlightDuration(), newFlightInfo.getFlightLength(),
				startDestination, endDestination, new HashSet<Ticket>(), seats, 
				newFlightInfo.getBusinessPrice(), newFlightInfo.getEconomicPrice(), newFlightInfo.getFirstClassPrice());;
		
		flightService.save(newFlight);
		return newFlight;
	}
	
	
	
	@RequestMapping(
			value = "/api/flightSearch",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Flight>  searchFlights(@RequestBody FlightBean search) throws Exception {
		System.out.println("\tpozvan sam " + search.getMinEconomic() + " i "+ search.getMaxEconomic() + " i " + search.getMinFirstClass());
		ArrayList<Flight> flights = new ArrayList<>();
		flights = (ArrayList<Flight>)flightService.findAll();
		ArrayList<Flight> foundFlights = new ArrayList<>();
		for (Flight f : flights) {
			if( (f.getStartDestination().getName().equals(search.getStartDestination()) ||search.getStartDestination().equals("")) &&
				(f.getEndDestination().getName().equals(search.getEndDestination()) ||search.getEndDestination().equals("")) &&
				(f.getEconomicPrice() >= search.getMinEconomic() || (search.getMinEconomic() == 0)) &&
				(f.getBusinessPrice() >= search.getMinBusiness() || (search.getMinBusiness() == 0)) &&
				(f.getFirstClassPrice() >= search.getMinFirstClass() || (search.getMinFirstClass() == 0)) &&
				(f.getEconomicPrice() <= search.getMaxEconomic()|| (search.getMaxEconomic() == 0)) &&
				(f.getBusinessPrice() <= search.getMaxBusiness()|| (search.getMaxBusiness() == 0)) &&
				(f.getFirstClassPrice() <= search.getMaxFirstClass()|| (search.getMaxFirstClass() == 0)) &&
				(f.getFlightDuration() == search.getFlightDuration() || search.getFlightDuration() == 0) &&
				(f.getFlightLength() == search.getFlightLength() || search.getFlightLength() == 0))
			{
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
		System.out.println( "\tFOUND "+ foundFlights.size());
		for (Flight f : foundFlights) {
			System.out.println("\t" + f.getStartDestination().getName());
		}
		return foundFlights;
	}
	
	private Set<Seat> makeSeats(Set<Seat> seats, String capacity, String flightClass){
		int rows = Integer.parseInt(capacity.split("|")[0]);
		int columns = Integer.parseInt(capacity.split("|")[2]);
		for (int row = 1; row <= rows; row++ ) {
			for (int col = 1; col <= columns; col++ ) {
				seats.add(new Seat(false, row,col, flightClass));
			}
		}
		return seats;
	}

}
