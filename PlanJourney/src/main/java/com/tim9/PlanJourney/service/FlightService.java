package com.tim9.PlanJourney.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.beans.FlightReservationBean;
import com.tim9.PlanJourney.beans.PassangerBean;
import com.tim9.PlanJourney.beans.QuickFlightReservationBean;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.Discounts;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.flight.Passanger;
import com.tim9.PlanJourney.models.flight.QuickFlightReservation;
import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.repository.FllightRepository;

@Service
@Transactional(readOnly = true)
public class FlightService {

	@Autowired
	private FlightReservationService reservationService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private PassangerService passangerService;
	@Autowired
	private QuickFlightReservationService quickReservationService;
	@Autowired
	private VehicleReservationService vehicleReservationService;
	@Autowired
	private HotelReservationService hotelReservationService;
	@Autowired
	private FlightCompanyService flightCompanyService;
	@Autowired
	DiscountsService discountService;

	@Autowired
	FllightRepository repository;

	public Flight findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<Flight> findAll() {
		return repository.findAll();
	}

	public Page<Flight> findAll(Pageable page) {
		return repository.findAll(page);
	}

	@Transactional(readOnly = false)
	public Flight save(Flight flight) {
		return repository.save(flight);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public String makeReservation(FlightReservationBean reservationBean, RegisteredUser loggedUser) {
		
		Flight flight = findOne(reservationBean.getFlightId());
		double total = 0;
		Seat main_seat = seatService.findOne(reservationBean.getPassangers().get(0).getSeatId());
		if (main_seat.isTaken()) {
			return "Seat = " + main_seat.getTravelClassa() + " (" + main_seat.getSeatRow() + " , " + main_seat.getSeatColumn() + ") is already taken!";
		}
		main_seat.setTaken(true);
		seatService.save(main_seat);

		ArrayList<Seat> seats = new ArrayList<>();
		ArrayList<RegisteredUser> friends = new ArrayList<>();
		ArrayList<Passanger> passangersList = new ArrayList<>();
		reservationBean.getPassangers().get(0).setFirstName(loggedUser.getFirstName());
		reservationBean.getPassangers().get(0).setLastName(loggedUser.getLastName());

		Set<Passanger> passangers = new HashSet<>();
		Set<HotelReservation> hotelReservations = getHotelReservations(reservationBean.getHotelReservations());
		Set<VehicleReservation> vehicleReservations = getRentReservations(reservationBean.getRentReservations());

		for (PassangerBean passanger : reservationBean.getPassangers()) {

			total += passanger.getPrice();
			Seat seat = seatService.findOne(passanger.getSeatId());
			if (main_seat.getId().equals(seat.getId()) == false && seat.isTaken()) {
				return "Seat = " + seat.getTravelClassa() + " (" + seat.getSeatRow() + " , " + seat.getSeatColumn() + ") is already taken!";
			}
			seat.setTaken(true);
			seats.add(seat);
			Passanger pass = null;
			if (passanger.getFriendId() != -1) {
				RegisteredUser friend = registeredUserService.findOne(passanger.getFriendId());
				friends.add(friend);
				pass = new Passanger(passanger.getFirstName(), passanger.getLastName(), passanger.getPassport(), seat,
						friend, passanger.getPrice());
			} else {
				pass = new Passanger(passanger.getFirstName(), passanger.getLastName(), passanger.getPassport(), seat,
						null, passanger.getPrice());
			}
			passangersList.add(pass);
			passangers.add(pass);
		}
		seatService.saveAll(seats);
		passangerService.saveAll(passangersList);

		FlightReservation reservation = new FlightReservation(loggedUser, main_seat, passangers, flight, total,
				new Date(), -1l, hotelReservations, vehicleReservations);
		flight.getFlightCompany().getFlightReservation().add(reservation);
		reservation.setCompany(flight.getFlightCompany());
		reservation.setDiscount(0);
		Discounts discounts = discountService.findOne(1L);
		if (loggedUser.getFlightReservations().size() >= discounts.getNumberOfFlightReservations()) {
			reservation.setDiscount(discounts.getRentACarDiscount());
		}
		for (RegisteredUser friend : friends) {
			try {
				emailService.sendReservationRequest(friend, reservation.getId());

			} catch (Exception e) {
				System.out.println("Error while sending email: " + e.getMessage());
			}
		}
		try {
			emailService.sendReservationMade(reservation);

		} catch (Exception e) {

			System.out.println("Error while sending email: " + e.getMessage());
			return "Reservation is made but we didn't succeed to send the email!";
		}
		reservationService.save(reservation);
		flightCompanyService.save(flight.getFlightCompany());
		return "success";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String makeQuickReservation(QuickFlightReservationBean reservationBean, RegisteredUser loggedUser) {

		Flight flight = findOne(reservationBean.getFlightId());
		double originPrice = reservationBean.getOriginPrice();
		double discount = reservationBean.getDiscount();
		Seat main_seat = seatService.findOne(reservationBean.getSeatId());
		main_seat.setTaken(true);
		main_seat.setQuick(false);
		seatService.save(main_seat);
		double total = originPrice * discount / 100;
		FlightReservation reservation = new FlightReservation(loggedUser, main_seat, new HashSet<Passanger>(), flight,
				total, new Date(), reservationBean.getId(), new HashSet<>(), new HashSet<>());

		QuickFlightReservation quick = quickReservationService.findOne(reservationBean.getId());
		quick.setTaken(true);
		reservation.setDiscount(quick.getDiscount());
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

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String confirmReservationRequest(Long requestId, RegisteredUser logged) {

		FlightReservation request = reservationService.findOne(requestId);
		for (Passanger p : request.getPassangers()) {
			if (p.getFriend() != null && p.getFriend().getId() == logged.getId()) {
				p.setConfirmed(true);
				p.getSeat().setTaken(true);
				passangerService.save(p);
				reservationService.save(request);
				return "success";
			}
		}
		return "It's not your reservation request";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String refuseReservationRequest(Long requestId, RegisteredUser logged) {

		FlightReservation reservation = reservationService.findOne(requestId);
		for (Passanger passanger : reservation.getPassangers()) {
			if (passanger.getFriend() != null) {
				if (passanger.getFriend().getId() == logged.getId()) {
					passanger.getSeat().setTaken(false);
					passanger.setConfirmed(false);
					seatService.save(passanger.getSeat());
					reservation.setPrice(reservation.getPrice() - passanger.getPrice());
					reservationService.save(reservation);
					return "success";
				}
			}
		}
		return "It's not your reservation request";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String cancelReservation(Long id, RegisteredUser loggedUser) {
		FlightReservation reservation = reservationService.findOne(id);
		if (reservation == null) {
			return null;
		}
		Date today = new Date();
		long diffInMillies = reservation.getFlight().getStartDate().getTime() - today.getTime();
		long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (diff < 3) {
			return "You can not cancel because it's less than 3 hours before starting!";
		}
		if (reservation.getUser().getId() != loggedUser.getId()) {
			for (Passanger p : reservation.getPassangers()) {
				if (p.getFriend() != null && p.getFriend().getId() == loggedUser.getId()) {
					p.getSeat().setTaken(false);
					seatService.save(p.getSeat());
					reservation.setPrice(reservation.getPrice() - p.getPrice());
					reservation.getPassangers().remove(p);
					reservationService.save(reservation);
					passangerService.remove(p.getId());
					return "success";
				}
			}
		} else {

			if (reservation.getQuickId() != -1l) {
				QuickFlightReservation quick = quickReservationService.findOne(reservation.getQuickId());
				quick.setTaken(false);
				reservation.getSeat().setTaken(false);
				reservation.getSeat().setQuick(true);
				seatService.save(reservation.getSeat());
				reservationService.remove(id);
				return "success";
			}

			for (HotelReservation h : reservation.getHoteReservations()) {

				hotelReservationService.remove(h.getId());
			}
			for (VehicleReservation v : reservation.getVehicleReservations()) {
				vehicleReservationService.remove(v.getId());
			}
			for (Passanger p : reservation.getPassangers()) {
				p.getSeat().setTaken(false);
				seatService.save(p.getSeat());
			}
			reservationService.remove(id);
			return "success";
		}
		return "It's not your reservation.";
	}

	private Set<HotelReservation> getHotelReservations(ArrayList<Long> ids) {
		Set<HotelReservation> retVal = new HashSet<HotelReservation>();
		if (ids.size() == 0) {
			return retVal;
		}
		for (Long id : ids) {
			retVal.add(hotelReservationService.findOne(id));
		}
		return retVal;
	}

	private Set<VehicleReservation> getRentReservations(ArrayList<Long> ids) {
		Set<VehicleReservation> retVal = new HashSet<VehicleReservation>();
		if (ids.size() == 0) {
			return retVal;
		}
		for (Long id : ids) {
			retVal.add(vehicleReservationService.findOne(id));
		}
		return retVal;
	}

}
