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
import com.tim9.PlanJourney.beans.ReservationRequestBean;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.flight.Passanger;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.FlightReservationService;
import com.tim9.PlanJourney.service.FlightService;
import com.tim9.PlanJourney.service.PassangerService;
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
		
		FlightReservation reservation = new FlightReservation(reciever, seat, new HashSet<Passanger>(), flight, 0, new Date(), false, callerInfo );
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
		Seat main_seat = seatService.findOne(reservationBean.getPassangers().get(0).getSeatId());
		seatService.save(main_seat);
		Set<Passanger> passangers = new HashSet<>();
		
		for (int i = 1; i < reservationBean.getPassangers().size(); i++) {
			Seat seat = seatService.findOne(reservationBean.getPassangers().get(i).getSeatId());
			seat.setTaken(true);
			seatService.save(seat);
			Passanger passanger = new Passanger( reservationBean.getPassangers().get(i).getFirstName(), reservationBean.getPassangers().get(i).getLastName(), "", seat);
			passangerService.save(passanger);
			passangers.add(passanger);
		}
		FlightReservation reservation = new FlightReservation(loggedUser, main_seat, passangers, flight, 0, new Date(), true, "" );
		reservationService.save(reservation);
		return reservation;
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

}
