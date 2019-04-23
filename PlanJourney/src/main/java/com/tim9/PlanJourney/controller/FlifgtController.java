package com.tim9.PlanJourney.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.FlightBean;
import com.tim9.PlanJourney.beans.SeatsBean;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.flight.FlightCompany;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.FlightService;
import com.tim9.PlanJourney.service.UserService;

@RestController
public class FlifgtController {

	@Autowired
	FlightService flightService;
	@Autowired
	DestinationService destinationService;
	@Autowired
	FlightCompanyService flightCompanyService;
	@Autowired
	UserService userService;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd.MMyyyy. HH:mm");
	
	
	@RequestMapping(value = "/api/getFlight/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	//Method returns flight which match given id
	public @ResponseBody FlightBean getFlight(@PathVariable("id") Long id) throws Exception{
		
		Flight flight = flightService.findOne(id);
		String companyName = "";
		for ( FlightCompany fc : flightCompanyService.findAll()) {
			if (fc.getFlights().contains(flight)) {
				companyName = fc.getName();
				break;
			}
		}
		
		return new FlightBean(flight, companyName, sdf.format(flight.getStartDate()), sdf.format(flight.getEndDate()));
	}
	
	
	@RequestMapping(value = "/api/getSeatsOnFlight/{id}/{travelClass}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody SeatsBean getSeatsOnFlight(@PathVariable("id") Long id, @PathVariable("travelClass") String travelClass) throws Exception{

		Flight flight = flightService.findOne(id);
		List<Seat> seats = new ArrayList<Seat>();
		int rows = 0;
		int columns = 0;
		for ( Seat seat : flight.getSeats()) {
			
			if (seat.getTravelClassa().equals(travelClass)) {
				seats.add(seat);
				if (seat.getSeatRow() > rows) {
					rows = seat.getSeatRow();
				}
				if (seat.getSeatColumn() > columns) {
					columns = seat.getSeatColumn();
				}
			}	
		}
		Collections.sort(seats, Comparator.comparing(Seat::getSeatRow));
		SeatsBean sb = new SeatsBean(seats, rows, columns);
		return sb;
	}
	

	@RequestMapping(value = "/api/addFlight", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody Flight addFlight(@RequestBody FlightBean newFlightInfo) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) userService.findOneByUsername(username);
			FlightCompany flightCompany = user.getFlightCompany();
			if (flightCompany == null) {
				System.out.println("Flight admin doesnt't have flight company.");
				return null;
			}
			// security checks on backend side
			Date endDate = newFlightInfo.getEndDate();
			Date startDate = newFlightInfo.getStartDate();
			if (endDate.before(startDate)) {
				return null;
			}
			Date today = new Date();
			if (startDate.before(today) || endDate.before(today)) {
				return null;
			}
			if (newFlightInfo.getStartDestination().equals(newFlightInfo.getEndDestination())) {
				System.out.println("\tiste destinacije");
				return null;
			}
			Destination startDestination = destinationService.findByName(newFlightInfo.getStartDestination());
			Destination endDestination = destinationService.findByName(newFlightInfo.getEndDestination());
			if (startDestination == null || endDestination == null) {
				return null;
			}
			Set<Seat> seats = new HashSet<Seat>();
			makeSeats(seats, newFlightInfo.getEconomicCapacity(), "economic");
			makeSeats(seats, newFlightInfo.getBuisinesssCapacity(), "business");
			makeSeats(seats, newFlightInfo.getFirstClassCapacity(), "first class");

			Flight newFlight = new Flight(startDate, endDate, newFlightInfo.getFlightDuration(),
					newFlightInfo.getFlightLength(), startDestination, endDestination, new HashSet<Ticket>(), seats,
					newFlightInfo.getBusinessPrice(), newFlightInfo.getEconomicPrice(),
					newFlightInfo.getFirstClassPrice());
			flightCompany.getFlights().add(newFlight);
			flightCompanyService.save(flightCompany);
			return newFlight;
		}
		return null;
	}

	@RequestMapping(value = "/api/flightSearch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<FlightBean> searchFlights(@RequestBody FlightBean search) throws Exception {
		System.out.println("Company = " + search.getEndDestination());
		ArrayList<Flight> flights = new ArrayList<>();
		flights = (ArrayList<Flight>) flightService.findAll();
		ArrayList<FlightBean> foundFlights = new ArrayList<>();
		DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
		String companyName = "";
		for (Flight f : flights) {
			companyName = getCompanyNameForFlight(f);
			System.out.println("\t>>>" + f.getEndDestination().getName());
			if ( (f.getStartDestination().getName().equals(search.getStartDestination()) || search.getStartDestination().equals(""))
				&& (f.getEndDestination().getName().equals(search.getEndDestination()) || search.getEndDestination().equals(""))
				&&  (companyName.equals(search.getFlightCompany()) || search.getFlightCompany().equals(""))
				&& (f.getEconomicPrice() >= search.getMinEconomic() || (search.getMinEconomic() == 0))
				&& (f.getBusinessPrice() >= search.getMinBusiness() || (search.getMinBusiness() == 0))
				&& (f.getFirstClassPrice() >= search.getMinFirstClass() || (search.getMinFirstClass() == 0))
				&& (f.getEconomicPrice() <= search.getMaxEconomic() || (search.getMaxEconomic() == 0))
				&& (f.getBusinessPrice() <= search.getMaxBusiness() || (search.getMaxBusiness() == 0))
				&& (f.getFirstClassPrice() <= search.getMaxFirstClass() || (search.getMaxFirstClass() == 0))
				&& (f.getFlightDuration() == search.getFlightDuration() || search.getFlightDuration() == 0)
				&& (f.getFlightLength() == search.getFlightLength() || search.getFlightLength() == 0)
				&& ( search.getStartDate()==null || dateTimeComparator.compare(f.getStartDate(),search.getStartDate()) == 0) 
				&& (search.getEndDate() == null  || dateTimeComparator.compare(f.getEndDate(),search.getEndDate()) == 0)
				)
					 {
				
				foundFlights.add(new FlightBean(f,companyName, sdf.format(f.getStartDate()), sdf.format(f.getEndDate())));
			}
		}
		System.out.println("\tREZ = " + foundFlights.size());
		return foundFlights;

	}
	private String getCompanyNameForFlight(Flight flight) {
		String companyName = "";
		for ( FlightCompany fc : flightCompanyService.findAll()) {
			if (fc.getFlights().contains(flight)) {
				companyName = fc.getName();
				break;
			}
		}
		return companyName;
	}

	private Set<Seat> makeSeats(Set<Seat> seats, String capacity, String flightClass) {
		int rows = Integer.parseInt(capacity.split("|")[0]);
		int columns = Integer.parseInt(capacity.split("|")[2]);
		for (int row = 1; row <= rows; row++) {
			for (int col = 1; col <= columns; col++) {
				seats.add(new Seat(false, row, col, flightClass));
			}
		}
		return seats;
	}

}
