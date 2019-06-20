package com.tim9.PlanJourney.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.flight.QuickFlightReservation;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.FlightAdminSerice;
import com.tim9.PlanJourney.service.FlightCompanyService;
import com.tim9.PlanJourney.service.FlightService;
import com.tim9.PlanJourney.service.QuickFlightReservationService;
import com.tim9.PlanJourney.service.SeatService;

@RestController
public class FlifgtController {

	@Autowired
	FlightAdminSerice flightAdminService;
	@Autowired
	FlightService flightService;
	@Autowired
	DestinationService destinationService;
	@Autowired
	FlightCompanyService flightCompanyService;
	@Autowired
	EmailService emailService;
	@Autowired
	SeatService seatService;
	@Autowired
	QuickFlightReservationService quickService;

	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

	// Method returns flight that match given id
	@RequestMapping(value = "/api/getFlight/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightBean getFlight(@PathVariable("id") Long id) throws Exception {

		Flight flight = flightService.findOne(id);
		FlightBean fb = new FlightBean(flight, flight.getFlightCompany().getName(), sdf2.format(flight.getStartDate()),
				sdf2.format(flight.getEndDate()));
		fb.setCityName(flight.getEndDestination().getCity().getName());
		return  fb;
	}

	@RequestMapping(value = "/api/getSeatsOnFlight/{id}/{travelClass}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody SeatsBean getSeatsOnFlight(@PathVariable("id") Long id,
			@PathVariable("travelClass") String travelClass) throws Exception {

		Flight flight = flightService.findOne(id);
		List<Seat> seats = new ArrayList<Seat>();
		int rows = 0;
		int columns = 0;
		for (Seat seat : flight.getSeats()) {

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
		Collections.sort(seats, Comparator.comparing(Seat::getSeatRow).thenComparing(Seat::getSeatColumn));
		SeatsBean sb = new SeatsBean(seats, rows, columns);
		return sb;
	}

	@RequestMapping(value = "/api/editSeat/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody Seat editSeat(@PathVariable("id") Long id) throws Exception {

		Seat seat = seatService.findOne(id);
		if (seat == null) {
			return null;
		}
		if (seat.isUnavailable()) {
			seat.setUnavailable(false);
		} else {
			seat.setUnavailable(true);
		}
		seatService.save(seat);
		return seat;
	}

	// Method for adding new flight
	@RequestMapping(value = "/api/addFlight", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody String addFlight(@RequestBody FlightBean newFlightInfo) throws Exception {

		FlightAdmin loggedAdmin = getLoggedFlightAdmin();
		if (loggedAdmin == null) {
			return null;
		}
		FlightCompany flightCompany = loggedAdmin.getFlightCompany();
		if (flightCompany == null) {
			return "No company has been assigned to you.";
		}

		Date endDate = sdf1.parse(newFlightInfo.getEndDate_str());
		Date startDate = sdf1.parse(newFlightInfo.getStartDate_str());

		if (endDate.before(startDate)) {
			return "End date can not be before start date!";
		}
		Date today = new Date();
		if (startDate.before(today) || endDate.before(today)) {
			return "Dates can not be in the past!";
		}
		if (newFlightInfo.getStartDestination().equals(newFlightInfo.getEndDestination())) {
			return "Start and end destinations must be different!";
		}
		Destination startDestination = destinationService.findOne(Long.parseLong(newFlightInfo.getStartDestination()));
		Destination endDestination = destinationService.findOne(Long.parseLong(newFlightInfo.getEndDestination()));
		if (startDestination == null || endDestination == null) {
			return null;
		}
		Set<Seat> seats = new HashSet<Seat>();
		makeSeats(seats, newFlightInfo.getEconomicRows(), newFlightInfo.getEconomicColumns(), "economic");
		makeSeats(seats, newFlightInfo.getBusinessRows(), newFlightInfo.getBusinessColumns(), "business");
		makeSeats(seats, newFlightInfo.getFirstClassRows(), newFlightInfo.getFirstClassColumns(), "first class");

		Flight newFlight = new Flight(flightCompany, startDate, endDate, newFlightInfo.getFlightDuration(),
				newFlightInfo.getFlightLength(), startDestination, endDestination, new HashSet<FlightReservation>(),
				seats, newFlightInfo.getBusinessPrice(), newFlightInfo.getEconomicPrice(),
				newFlightInfo.getFirstClassPrice());
		newFlight.setAdditionalServices(newFlightInfo.getAdditionalServices());
		newFlight.setTransitions(newFlightInfo.getTransitions());
		flightService.save(newFlight);
		flightCompany.getFlights().add(newFlight);
		flightCompanyService.save(flightCompany);
		flightAdminService.save(loggedAdmin);

		return "success";
	}

	// Method for editing flight
	@RequestMapping(value = "/api/editFlight", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody String editFlight(@RequestBody FlightBean flightInfo) throws Exception {
		

		FlightAdmin loggedAdmin = getLoggedFlightAdmin();
		if (loggedAdmin == null) {
			return null;
		}
		Long flightId = flightInfo.getId();
		Flight flight = flightService.findOne(flightId);
		
		Date endDate = flightInfo.getEndDate();;
		Date startDate = flightInfo.getStartDate();
		if (flightInfo.getEndDate_str().equals("")) {
			endDate = sdf1.parse(flightInfo.getEndDate_str());
		}
		if (flightInfo.getStartDate_str().equals("")) {
			startDate = sdf1.parse(flightInfo.getStartDate_str());
		}
		if (endDate.before(startDate)) {
			return "End date can not be before start date!";
		}
		Date today = new Date();
		if (startDate.before(today) || endDate.before(today)) {
			return "Dates can not be in the past!";
		}
		
		flight.setStartDate(startDate);
		flight.setEndDate(endDate);
		flight.setFlightDuration(flightInfo.getFlightDuration());
		flight.setFlightLength(flightInfo.getFlightLength());
		flight.setEconomicPrice(flightInfo.getEconomicPrice());
		flight.setBusinessPrice(flightInfo.getBusinessPrice());
		flight.setFirstClassPrice(flightInfo.getFirstClassPrice());
		flight.setAdditionalServices(flightInfo.getAdditionalServices());
		flight.setTransitions(flightInfo.getTransitions());
		flightService.save(flight);
		return "success";
	}

	// Method for removing flight
	@RequestMapping(value = "/api/removeFlight/{flightId}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('FLIGHT_ADMIN')")
	public @ResponseBody String removeFlight(@PathVariable("flightId") Long flightId) throws Exception {
		
		FlightAdmin loggedAdmin = getLoggedFlightAdmin();
		if (loggedAdmin == null) {
			return null;
		}
		Flight flight = flightService.findOne(flightId);
		if (flight.getFlightReservations().size() > 0) {
			return "You can not remove this flight, there are some reservations";
		}
		for (QuickFlightReservation q : flight.getQuickReservations()) {
			quickService.remove(q.getId());
		}
		flightService.remove(flightId);
		return "success";
	}

	@RequestMapping(value = "/api/flightSearch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<FlightBean> searchFlights(@RequestBody FlightBean search) throws Exception {
		System.out.println("Company = " + search.getEndDestination());
		ArrayList<Flight> flights = new ArrayList<>();
		flights = (ArrayList<Flight>) flightService.findAll();
		ArrayList<FlightBean> foundFlights = new ArrayList<>();
		DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
		for (Flight f : flights) {
			int transitionsCnt = 0;
			if ( f.getTransitions() == null || f.getTransitions().equals("0") || f.getTransitions().equals("")) {
				transitionsCnt = 0;
			}
			else {
				transitionsCnt = f.getTransitions().split(",").length;
			}
			if ((f.getStartDestination().getName().contains(search.getStartDestination())
					|| search.getStartDestination().equals(""))
					&& (f.getEndDestination().getName().contains(search.getEndDestination())
							|| search.getEndDestination().equals(""))
					&& (f.getFlightCompany().getName().contains(search.getFlightCompany())
							|| search.getFlightCompany().equals(""))
					&& (f.getEconomicPrice() >= search.getMinEconomic() || (search.getMinEconomic() == 0))
					&& (f.getBusinessPrice() >= search.getMinBusiness() || (search.getMinBusiness() == 0))
					&& (f.getFirstClassPrice() >= search.getMinFirstClass() || (search.getMinFirstClass() == 0))
					&& (f.getEconomicPrice() <= search.getMaxEconomic() || (search.getMaxEconomic() == 0))
					&& (f.getBusinessPrice() <= search.getMaxBusiness() || (search.getMaxBusiness() == 0))
					&& (f.getFirstClassPrice() <= search.getMaxFirstClass() || (search.getMaxFirstClass() == 0))
					&& (f.getFlightDuration() == search.getFlightDuration() || search.getFlightDuration() == 0)
					&& (f.getFlightLength() == search.getFlightLength() || search.getFlightLength() == 0)
					&& (search.getTransitionsNum() <= transitionsCnt || search.getTransitionsNum() == 0)
					&& (search.getStartDate() == null
							|| dateTimeComparator.compare(f.getStartDate(), search.getStartDate()) == 0)
					&& (search.getEndDate() == null
							|| dateTimeComparator.compare(f.getEndDate(), search.getEndDate()) == 0)) {

				foundFlights.add(new FlightBean(f, f.getFlightCompany().getName(), sdf2.format(f.getStartDate()),
						sdf2.format(f.getEndDate())));
			}
		}
		System.out.println("\tREZ = " + foundFlights.size());
		return foundFlights;

	}

	// Returns currently logged user
	private FlightAdmin getLoggedFlightAdmin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			FlightAdmin user = (FlightAdmin) flightAdminService.findByUsername(username);
			return user;
		}
		return null;
	}

	private Set<Seat> makeSeats(Set<Seat> seats, int rows, int columns, String flightClass) {
		for (int row = 1; row <= rows; row++) {
			for (int col = 1; col <= columns; col++) {
				seats.add(new Seat(false, row, col, flightClass));
			}
		}
		return seats;
	}

}
