package com.tim9.PlanJourney.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import com.tim9.PlanJourney.beans.FlightReservationBean;
import com.tim9.PlanJourney.beans.QuickFlightReservationBean;
import com.tim9.PlanJourney.beans.ReservationRequestBean;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.flight.Passanger;
import com.tim9.PlanJourney.models.flight.QuickFlightReservation;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.FlightReservationService;
import com.tim9.PlanJourney.service.FlightService;
import com.tim9.PlanJourney.service.PassangerService;
import com.tim9.PlanJourney.service.QuickFlightReservationService;
import com.tim9.PlanJourney.service.RegisteredUserService;
import com.tim9.PlanJourney.service.SeatService;

@RestController
public class flightReservationController {

	@Autowired
	private FlightReservationService reservationService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private FlightService flightService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private PassangerService passangerService;
	@Autowired
	private QuickFlightReservationService quickReservationService;
	
	
	
	@RequestMapping(value = "/api/getMyReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody Set<FlightReservation> getMyReservations() {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		return loggedUser.getFlightReservations();
	}
	

	@RequestMapping(value = "/api/sendReservationRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody String sendReservationRequest(@RequestBody ReservationRequestBean requestInfo) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		RegisteredUser reciever = registeredUserService.findOne(requestInfo.getCalledUserId());
		Flight flight = flightService.findOne(requestInfo.getFlightId());
		Seat seat = seatService.findOne(requestInfo.getSeatId());
		seat.setTaken(true);
		seatService.save(seat);
		String callerInfo = loggedUser.getUsername() + " ( " + loggedUser.getFirstName() + " " +  loggedUser.getLastName() + " )";
		
		FlightReservation reservation = new FlightReservation(reciever, seat, new HashSet<Passanger>(), flight, findPrice(flight, seat), new Date(), false, callerInfo );
		reservationService.save(reservation);
		reciever.getFlightReservations().add(reservation);
		registeredUserService.save(reciever);
		
		try {
			emailService.sendReservationRequest(reservation);

		} catch (Exception e) {

			System.out.println("Error while sending email: " + e.getMessage());
			return "Error while sending email!";
		}
		return "success";
	}

	@RequestMapping(value = "/api/getReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightReservation getReservationRequest(@PathVariable("requestId") Long requestId) {

		FlightReservation request = reservationService.findOne(requestId);
		return request;
	}
	
	
	
	@RequestMapping(value = "/api/makeFlightReservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody FlightReservation makeReservation(@RequestBody FlightReservationBean reservationBean)  {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		Flight flight = flightService.findOne(reservationBean.getFlightId());
		double total = reservationBean.getPassangers().get(0).getPrice();
		Seat main_seat = seatService.findOne(reservationBean.getPassangers().get(0).getSeatId());
		main_seat.setTaken(true);
		seatService.save(main_seat);
		Set<Passanger> passangers = new HashSet<>();
		
		for (int i = 1; i < reservationBean.getPassangers().size(); i++) {
			
			total += reservationBean.getPassangers().get(i).getPrice();
			Seat seat = seatService.findOne(reservationBean.getPassangers().get(i).getSeatId());
			seat.setTaken(true);
			seatService.save(seat);
			Passanger passanger = new Passanger( reservationBean.getPassangers().get(i).getFirstName(), reservationBean.getPassangers().get(i).getLastName(), "", seat);
			passangerService.save(passanger);
			passangers.add(passanger);
		}
		FlightReservation reservation = new FlightReservation(loggedUser, main_seat, passangers, flight, total, new Date(), true, "" );
		try {
			emailService.sendReservationMade(reservation);

		} catch (Exception e) {

			System.out.println("Error while sending email: " + e.getMessage());
			return null;
		}
		reservationService.save(reservation);
		return reservation;
	}
	
	@RequestMapping(value = "/api/makeQuickReservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String makeQuickReservation(@RequestBody QuickFlightReservationBean reservationBean)  {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return "You are not logged in!";
		}
		Flight flight = flightService.findOne(reservationBean.getFlightId());
		double originPrice = reservationBean.getOriginPrice();
		double discount = reservationBean.getDiscount();
		Seat main_seat = seatService.findOne(reservationBean.getSeatId());
		main_seat.setTaken(true);
		main_seat.setQuick(false);
		seatService.save(main_seat);
		double total = originPrice * discount/100;
		FlightReservation reservation = new FlightReservation(loggedUser, main_seat, new HashSet<Passanger>(), flight, total, new Date(), true, "" );
		QuickFlightReservation quick = quickReservationService.findOne(reservationBean.getId());
		quick.setTaken(true);
		quickReservationService.save(quick);
		reservationService.save(reservation);
		try {
			emailService.sendReservationMade(reservation);

		} catch (Exception e) {

			System.out.println("Error while sending email: " + e.getMessage());
			return "Reservation is made, but we didn'y succeed to send e-mail about details!";
		}
		return "success";
	}

	
	@RequestMapping(value = "/api/confirmReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightReservation confirmReservationRequest(@PathVariable("requestId") Long requestId) {

		FlightReservation request = reservationService.findOne(requestId);
		request.setConfirmed(true);
		reservationService.save(request);
		return request;
	}
	
	@RequestMapping(value = "/api/refuseReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody FlightReservation refuseReservationRequest(@PathVariable("requestId") Long requestId) {

		//ne znam da li je dovoljno samo da obrisem ili morem i kod user-a
		FlightReservation request = reservationService.findOne(requestId);
		request.getSeat().setTaken(false);
		seatService.save(request.getSeat());
		reservationService.remove(request.getId());
		return null;
	}
	
	private RegisteredUser getLoggedRegisteredUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			RegisteredUser user = (RegisteredUser) registeredUserService.findByUsername(username);
			return user;
		}
		return null;
	}
	
	private double findPrice(Flight f, Seat s) {
		
		if (s.getTravelClassa() == "economic") {
			return f.getEconomicPrice();
		}
		else if (s.getTravelClassa() == "business") {
			return f.getBusinessPrice();
		}
		else {
			return f.getFirstClassPrice();
		}
	}

}
