package com.tim9.PlanJourney.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import com.tim9.PlanJourney.beans.FrontFlightReservationBean;
import com.tim9.PlanJourney.beans.HotelReservationBean;
import com.tim9.PlanJourney.beans.PassangerBean;
import com.tim9.PlanJourney.beans.QuickFlightReservationBean;
import com.tim9.PlanJourney.beans.VehicleReservationBean;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.flight.Passanger;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.service.FlightReservationService;
import com.tim9.PlanJourney.service.FlightService;
import com.tim9.PlanJourney.service.RegisteredUserService;

@RestController
public class flightReservationController {

	@Autowired
	private FlightReservationService reservationService;
	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private FlightService flightService;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

	@RequestMapping(value = "/api/getMyReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<FrontFlightReservationBean> getMyReservations() {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		ArrayList<FrontFlightReservationBean> toReturn = new ArrayList<>();
		ArrayList<FlightReservation> allReservations = (ArrayList<FlightReservation>) reservationService.findAll();
		for (FlightReservation flightReservation : allReservations) {
			if (flightReservation.getUser().getId() == loggedUser.getId()) {
				toReturn.add(makeReservationBean(flightReservation, loggedUser, null));
			} else {
				for (Passanger p : flightReservation.getPassangers()) {
					if (p.getFriend() != null && p.getFriend().getId() == loggedUser.getId() && p.isConfirmed()) {
						toReturn.add(makeReservationBean(flightReservation, loggedUser, p.getSeat()));
						break;
					}
				}
			}
		}
		return toReturn;
	}

	@RequestMapping(value = "/api/getFlightReservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody FrontFlightReservationBean getReservation(@PathVariable("id") Long id) {
		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		FlightReservation reservation = reservationService.findOne(id);
		if (reservation == null) {
			return null;
		}
		FrontFlightReservationBean returnValue = new FrontFlightReservationBean();
		returnValue.setStartDestination(reservation.getFlight().getStartDestination().getName());
		returnValue.setEndDestination(reservation.getFlight().getEndDestination().getName());
		returnValue.setStartDate(sdf.format(reservation.getFlight().getStartDate()));
		returnValue.setEndDate(sdf.format(reservation.getFlight().getEndDate()));
		returnValue.setFlightDuration(reservation.getFlight().getFlightDuration());
		returnValue.setFlightLenght(reservation.getFlight().getFlightLength());
		returnValue.setPrice(reservation.getPrice());
		returnValue.setCreated(sdf.format(reservation.getDate()));
		returnValue.setLatitude(reservation.getFlight().getEndDestination().getLatitude());
		returnValue.setLongitude(reservation.getFlight().getEndDestination().getLongitude());
		ArrayList<PassangerBean> passangers = makePassangersBeans(reservation.getPassangers());
		returnValue.setPassangersInfo(passangers);
		ArrayList<HotelReservationBean> hotelReservations = makeHotelReservationBeans(
				reservation.getHoteReservations());
		returnValue.setHotelsReservations(hotelReservations);

		ArrayList<VehicleReservationBean> rentReservations = makeRentReservationBeans(
				reservation.getVehicleReservations());
		returnValue.setRentReservations(rentReservations);
		returnValue.setDiscount(reservation.getDiscount());
		return returnValue;
	}

	@RequestMapping(value = "/api/cancelFlightReservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String removeFlightReservation(@PathVariable("id") Long id) {
		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		return flightService.cancelReservation(id, loggedUser);
	}

	@RequestMapping(value = "/api/getReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody FrontFlightReservationBean getReservationRequest(@PathVariable("requestId") Long requestId) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		FlightReservation request = reservationService.findOne(requestId);
		FrontFlightReservationBean bean = new FrontFlightReservationBean(request.getId(),
				request.getFlight().getStartDestination().getName(), request.getFlight().getEndDestination().getName(),
				sdf.format(request.getFlight().getStartDate()), sdf.format(request.getFlight().getEndDate()),
				request.getPrice(), request.getPassangers().size());
		boolean found = false;
		for (Passanger p : request.getPassangers()) {
			if (p.getFriend() != null && p.getFriend().getId().equals(loggedUser.getId())) {
				bean.setSeatRow(p.getSeat().getSeatRow());
				bean.setSeatColumn(p.getSeat().getSeatColumn());
				bean.setTravelClassa(p.getSeat().getTravelClassa());
				bean.setPriceForSeat(p.getPrice());
				found = true;
			}
		}
		if (!found) {
			return null;
		}
		ArrayList<PassangerBean> passangers = makePassangersBeans(request.getPassangers());
		bean.setPassangersInfo(passangers);
		ArrayList<HotelReservationBean> hotelReservations = makeHotelReservationBeans(request.getHoteReservations());
		bean.setHotelsReservations(hotelReservations);
		ArrayList<VehicleReservationBean> rentReservations = makeRentReservationBeans(request.getVehicleReservations());
		bean.setRentReservations(rentReservations);
		bean.setCreator(request.getUser().getFirstName() + " " + request.getUser().getLastName() + " ( "
				+ request.getUser().getUsername() + " )");
		return bean;
	}

	@RequestMapping(value = "/api/getMyReservationRequests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<FrontFlightReservationBean> getMyReservationRequests() {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		ArrayList<FrontFlightReservationBean> toReturn = new ArrayList<>();
		ArrayList<FlightReservation> allReservations = (ArrayList<FlightReservation>) reservationService.findAll();
		Date today = new Date();
		for (FlightReservation flightReservation : allReservations) {

			long diffInMillies = flightReservation.getFlight().getStartDate().getTime() - today.getTime();
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff < 3) {
				continue;
			}
			if (flightReservation.getUser().getId() == loggedUser.getId()) {
				continue;
			} else {
				for (Passanger p : flightReservation.getPassangers()) {
					if (p.getFriend() != null && p.getFriend().getId() == loggedUser.getId() && p.isConfirmed() == false
							&& p.getSeat().isTaken()) {
						toReturn.add(makeReservationBean(flightReservation, loggedUser, p.getSeat()));
						break;
					}
				}
			}
		}
		return toReturn;
	}

	@RequestMapping(value = "/api/makeFlightReservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String makeReservation(@RequestBody FlightReservationBean reservationBean) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return null;
		}
		return flightService.makeReservation(reservationBean, loggedUser);

	}

	@RequestMapping(value = "/api/makeQuickReservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String makeQuickReservation(@RequestBody QuickFlightReservationBean reservationBean) {

		RegisteredUser loggedUser = getLoggedRegisteredUser();
		if (loggedUser == null) {
			return "You are not logged in!";
		}
		return flightService.makeQuickReservation(reservationBean, loggedUser);
	}

	@RequestMapping(value = "/api/confirmReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String confirmReservationRequest(@PathVariable("requestId") Long requestId) {

		RegisteredUser logged = getLoggedRegisteredUser();
		if (logged == null) {
			return "You are not sign in or it's not your reservation request";
		}
		return flightService.confirmReservationRequest(requestId, logged);
	}

	@RequestMapping(value = "/api/refuseReservationRequest/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody String refuseReservationRequest(@PathVariable("requestId") Long requestId) {

		RegisteredUser logged = getLoggedRegisteredUser();
		if (logged == null) {
			return "You are not sign in or it's not your reservation request";
		}
		return flightService.refuseReservationRequest(requestId, logged);
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

	private ArrayList<HotelReservationBean> makeHotelReservationBeans(Set<HotelReservation> reservations) {

		ArrayList<HotelReservationBean> hotelReservations = new ArrayList<HotelReservationBean>();
		for (HotelReservation h : reservations) {
			HotelReservationBean bean = new HotelReservationBean();
			bean.setFirstDay(sdf.format(h.getFirstDay()));
			bean.setLastDay(sdf.format(h.getLastDay()));
			bean.setPaidPrice(h.getPaidPrice());
			bean.setHotelName(h.getHotel().getName());
			hotelReservations.add(bean);
		}
		return hotelReservations;
	}

	private ArrayList<VehicleReservationBean> makeRentReservationBeans(Set<VehicleReservation> reservations) {

		ArrayList<VehicleReservationBean> rentReservations = new ArrayList<>();
		for (VehicleReservation v : reservations) {
			VehicleReservationBean bean = new VehicleReservationBean(v.getVehicle().getName(),
					v.getOfficePick().getName(), v.getOfficeReturn().getName(), sdf.format(v.getDateFrom()),
					sdf.format(v.getDateTo()), v.getCena());
			rentReservations.add(bean);
		}
		return rentReservations;
	}

	private ArrayList<PassangerBean> makePassangersBeans(Set<Passanger> passangerObjects) {

		ArrayList<PassangerBean> passangers = new ArrayList<PassangerBean>();
		for (Passanger p : passangerObjects) {
			PassangerBean passanger = new PassangerBean(p.getFirstName(), p.getLastName(), p.getPassport(),
					p.getPrice(), p.getSeat().getSeatRow(), p.getSeat().getSeatColumn(), p.getSeat().getTravelClassa());
			String status = "Confirmed";
			if (p.getFriend() != null && !p.isConfirmed()) {
				status = "Waiting";
			}
			if (p.getFriend() != null && !p.getSeat().isTaken()) {
				status = "Refused";
			}
			passanger.setStatus(status);
			passangers.add(passanger);
		}
		return passangers;
	}

	private FrontFlightReservationBean makeReservationBean(FlightReservation flightReservation,
			RegisteredUser loggedUser, Seat seat) {
		FrontFlightReservationBean ffrb = new FrontFlightReservationBean(flightReservation.getId(),
				flightReservation.getFlight().getStartDestination().getName(),
				flightReservation.getFlight().getEndDestination().getName(),
				sdf.format(flightReservation.getFlight().getStartDate()),
				sdf.format(flightReservation.getFlight().getEndDate()), flightReservation.getPrice(),
				flightReservation.getPassangers().size(), 0);
		if (seat == null) {
			ffrb.setSeatRow(flightReservation.getSeat().getSeatRow());
			ffrb.setSeatColumn(flightReservation.getSeat().getSeatColumn());
			ffrb.setTravelClassa(flightReservation.getSeat().getTravelClassa());
		} else {
			ffrb.setSeatRow(seat.getSeatRow());
			ffrb.setSeatColumn(seat.getSeatColumn());
			ffrb.setTravelClassa(seat.getTravelClassa());
		}
		Set<Review> reviews = flightReservation.getReservationReviews();
		for (Review r : reviews) {
			if (r.getUser().getUsername().equals(loggedUser.getUsername())) {
				ffrb.setRating(r.getRating());
			}
		}
		int count = 0;
		for (Passanger p : flightReservation.getPassangers()) {
			if (p.getSeat().isTaken() == true) {
				count++;
			}
		}
		ffrb.setPassangers(count);

		ffrb.setCreator(flightReservation.getUser().getFirstName() + " " + flightReservation.getUser().getLastName());
		int status = 0; // moze cancel, ne moze ocenjivanje
		Date today = new Date();
		long diffInMillies = flightReservation.getFlight().getStartDate().getTime() - today.getTime();
		long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (flightReservation.getFlight().getStartDate().before(today)) {
			status = 1; // moze ocenjivanje, ne moze cancel
		} else if (diff < 3) {
			status = 2; // ne moze cancel, ne moze ocenjivanje
		}
		ffrb.setStatus(status);
		return ffrb;
	}

}
