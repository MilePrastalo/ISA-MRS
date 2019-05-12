package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.tim9.PlanJourney.beans.DestinationBean;
import com.tim9.PlanJourney.beans.HotelBean;
import com.tim9.PlanJourney.beans.HotelReservationBean;
import com.tim9.PlanJourney.beans.HotelRoomBean;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.HotelService;
import com.tim9.PlanJourney.service.RegisteredUserService;

@RestController
public class HotelController {
	@Autowired
	private HotelService service;

	@Autowired
	private DestinationService destinationService;

	@Autowired
	private RegisteredUserService userService;

	@RequestMapping(value = "/api/getAllHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Hotel> getAllHotels() throws Exception {
		ArrayList<Hotel> hotel = (ArrayList<Hotel>) service.findAll();
		for (Hotel h : hotel) {
			System.out.println(h.getName());
		}
		return hotel;
	}

	@RequestMapping(value = "/api/getHotel/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public HotelBean getHotel(@PathVariable("name") String name) {

		Hotel h = service.findByName(name);

		HotelBean hb = new HotelBean();
		hb.setName(h.getName());
		hb.setAddress(h.getAddress());
		hb.setDescription(h.getDescription());
		hb.setRating(h.getRating());
		ArrayList<HotelRoomBean> rb = new ArrayList<HotelRoomBean>();
		for (HotelRoom r : h.getRooms()) {
			HotelRoomBean roomBean = new HotelRoomBean();
			roomBean.setRoomNumber(r.getRoomNumber());
			roomBean.setNumberOfBeds(r.getNumberOfBeds());
			roomBean.setPricePerDay(r.getPricePerDay());
			roomBean.setAdditionalCharges(r.getAdditionalCharges());
			rb.add(roomBean);
		}
		hb.setRooms(rb);

		ArrayList<HotelReservationBean> reservationBeans = new ArrayList<HotelReservationBean>();
		// Creates hotel reservation beans from hotel reservations.
		for (HotelReservation hr : h.getReservations()) {
			Calendar c = Calendar.getInstance();
			c.setTime(hr.getFirstDay());
			HotelReservationBean reservationBean = new HotelReservationBean();
			reservationBean.setRoomNumber(hr.getRoom().getRoomNumber());
			if (hr.getUser() != null) {
				reservationBean.setUsername(hr.getUser().getUsername());
			}
			reservationBean.setHotelName(hr.getHotel().getName());
			reservationBean.setfYear(c.get(Calendar.YEAR));
			reservationBean.setfMonth(c.get(Calendar.MONTH) + 1);
			reservationBean.setfDay(c.get(Calendar.DAY_OF_MONTH));
			c.setTime(hr.getLastDay());
			reservationBean.setHotelName(hr.getHotel().getName());
			reservationBean.setlYear(c.get(Calendar.YEAR));
			reservationBean.setlMonth(c.get(Calendar.MONTH) + 1);
			reservationBean.setlDay(c.get(Calendar.DAY_OF_MONTH));
			reservationBean.setDiscount(hr.getDiscount());
			reservationBean.setNumberOfBeds(hr.getRoom().getNumberOfBeds());
			reservationBean.setPaidPrice(hr.getPaidPrice());
			reservationBeans.add(reservationBean);
		}
		hb.setReservations(reservationBeans);

		DestinationBean db = new DestinationBean();
		db.setName(h.getDestination().getName());
		db.setDescription(h.getDestination().getDescription());
		db.setCoordinates(h.getDestination().getCoordinates());
		hb.setDestination(db);

		return hb;
	}

	@RequestMapping(value = "/api/getUserHotelReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody ArrayList<HotelReservationBean> getHotelReservations() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RegisteredUser user = (RegisteredUser) userService.findByUsername(username);

		ArrayList<HotelReservationBean> hotelReservationBeans = new ArrayList<HotelReservationBean>();

		if (user != null) {
			// Creates hotel reservation beans from hotel reservations.
			for (HotelReservation hr : user.getHotelReservations()) {
				HotelReservationBean reservationBean = new HotelReservationBean();
				reservationBean.setRoomNumber(hr.getRoom().getRoomNumber());
				reservationBean.setUsername(hr.getUser().getUsername());
				reservationBean.setHotelName(hr.getHotel().getName());
				Calendar c = Calendar.getInstance();
				c.setTime(hr.getFirstDay());
				reservationBean.setfYear(c.get(Calendar.YEAR));
				reservationBean.setfMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setfDay(c.get(Calendar.DAY_OF_MONTH));
				c.setTime(hr.getLastDay());
				reservationBean.setHotelName(hr.getHotel().getName());
				reservationBean.setlYear(c.get(Calendar.YEAR));
				reservationBean.setlMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setlDay(c.get(Calendar.DAY_OF_MONTH));
				reservationBean.setId(hr.getId());
				hotelReservationBeans.add(reservationBean);
			}
		}
		return hotelReservationBeans;
	}

	@RequestMapping(value = "/api/addHotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// checks if hotel already exists on given address and same destination.
			Hotel addressCheck = service.findByAddress(hotel.getAddress());
			Destination dest = destinationService.findOne(hotel.getDestination().getId());
			boolean alreadyExists = false;
			if (addressCheck != null) {
				if (addressCheck.getDestination().getId() == hotel.getDestination().getId()) {
					alreadyExists = true;
				}
			}
			if (!alreadyExists && service.findByName(hotel.getName()) == null) {
				hotel.setDestination(dest);
				Hotel h = (Hotel) service.save(hotel);
				return new ResponseEntity<Hotel>(new Hotel(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/removeHotel/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<Hotel> removeHotel(@PathVariable("name") String name) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Hotel hotel = service.findByName(name);
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			if (hotel == null) {
				return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
			}

			service.remove(hotel.getId());
			return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		}
		return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
	}

	// Metod za izmenu hotela, dodavanje soba, rezervacija...
	@RequestMapping(value = "/api/updateHotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody Hotel updateHotel(@RequestBody Hotel hotel) {
		Hotel existingHotel = service.findOne(hotel.getId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (existingHotel.getAddress().equals(hotel.getAddress())
					&& existingHotel.getName().equals(hotel.getName())) {
				Hotel h = (Hotel) service.save(hotel);
				return null;
			}
		}
		return null;
	}

	@RequestMapping(value = "/api/searchHotels/{values}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Hotel> searchHotels(@PathVariable("values") String values) throws Exception {
		ArrayList<Hotel> hotels = (ArrayList<Hotel>) service.findAll();
		ArrayList<Hotel> foundHotels = new ArrayList<Hotel>();
		String[] split = values.split("\\|");
		System.out.println(values);
		String crit = split[0];
		// Returns all hotels if search input is empty.
		if (values.endsWith("|")) {
			return hotels;
		}
		String val = split[1];
		if (crit.equals("name")) {
			for (Hotel h : hotels) {
				if (h.getName().equals(val)) {
					foundHotels.add(h);
				}
			}
		} else if (crit.equals("destination")) {
			for (Hotel h : hotels) {
				if (h.getDestination().getName().equals(val)) {
					foundHotels.add(h);
				}
			}
		} else if (crit.equals("nob")) {
			for (Hotel h : hotels) {
				for (HotelRoom r : h.getRooms()) {
					if (r.getNumberOfBeds() == Integer.parseInt(val)) {
						if (!foundHotels.contains(h))
							foundHotels.add(h);
					}
				}
			}
		}
		return foundHotels;
	}

	@RequestMapping(value = "/api/addHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody boolean addHotelReservation(@RequestBody HotelReservationBean reservationBean) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// checks if hotel already exists on given address and same destination.
			Hotel hotel = service.findByName(reservationBean.getHotelName());
			if (hotel == null) {
				return false;
			}
			RegisteredUser user = userService.findByUsername(reservationBean.getUsername());
			if (user == null) {
				return false;
			}
			HotelRoom room = null;
			for (HotelRoom r : hotel.getRooms()) {
				if (r.getRoomNumber() == reservationBean.getRoomNumber()) {
					room = r;
					break;
				}
			}
			if (room == null) {
				return false;
			}

			// Creating Date objects

			Date firstDay = new Date();
			Calendar c = Calendar.getInstance();
			System.out.println("Dates: " + reservationBean.getfYear() + " m: " + reservationBean.getfMonth() + " d: "
					+ reservationBean.getfDay());
			c.set(reservationBean.getfYear(), reservationBean.getfMonth() - 1, reservationBean.getfDay(), 0, 0);
			firstDay.setTime(c.getTimeInMillis());

			Date lastDay = new Date();
			c.set(reservationBean.getlYear(), reservationBean.getlMonth() - 1, reservationBean.getlDay(), 0, 0);
			lastDay.setTime(c.getTimeInMillis());

			if (!checkDate(firstDay, lastDay, hotel, reservationBean.getRoomNumber())) {
				return false;
			}

			// Calculating number of days.
			long time = lastDay.getTime() - firstDay.getTime();
			long days = TimeUnit.MILLISECONDS.toDays(time);

			HotelReservation reservation = new HotelReservation();
			reservation.setFirstDay(firstDay);
			reservation.setLastDay(lastDay);
			reservation.setHotel(hotel);
			reservation.setRoom(room);
			reservation.setDiscount(0);
			reservation.setPaidPrice(days * room.getPricePerDay());
			reservation.setUser(user);
			hotel.getReservations().add(reservation);
			user.getHotelReservations().add(reservation);

			service.save(hotel);
			userService.save(user);

			return true;
		}
		return false;
	}

	// Backend checks for adding new reservations.
	private boolean checkDate(Date firstDay, Date lastDay, Hotel hotel, int roomNumber) {

		if (firstDay.after(lastDay)) {
			return false;
		}

		for (HotelReservation hr : hotel.getReservations()) {
			if (roomNumber == hr.getRoom().getRoomNumber()) {
				if (lastDay.after(hr.getFirstDay()) && lastDay.before(hr.getLastDay())) {
					return false;
				}
				if (firstDay.after(hr.getFirstDay()) && firstDay.before(hr.getLastDay())) {
					return false;
				}
				if (firstDay.equals(hr.getFirstDay()) || lastDay.equals(hr.getLastDay())) {
					return false;
				}
			}
		}
		return true;
	}

	// Price Reduction should be added to reservations!
	@RequestMapping(value = "/api/buyQuickHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody boolean buyQuickHotelReservation(@RequestBody HotelReservationBean reservationBean) {
		// checks logged user.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// checks if hotel already exists on given address and same destination.
			Hotel hotel = service.findByName(reservationBean.getHotelName());
			if (hotel == null) {
				return false;
			}

			// checks registered user
			String username = authentication.getName();
			RegisteredUser user = userService.findByUsername(username);
			if (user == null) {
				return false;
			}

			// Splits date and checks if reservation date is valid.
			Calendar c = Calendar.getInstance();

			for (HotelReservation hr : hotel.getReservations()) {
				if (hr.getRoom().getRoomNumber() == reservationBean.getRoomNumber()) {
					c.setTime(hr.getFirstDay());
					int fYear = c.get(Calendar.YEAR);
					int fMonth = c.get(Calendar.MONTH) + 1;
					int fDay = c.get(Calendar.DAY_OF_MONTH);
					c.setTime(hr.getLastDay());
					int lYear = c.get(Calendar.YEAR);
					int lMonth = c.get(Calendar.MONTH) + 1;
					int lDay = c.get(Calendar.DAY_OF_MONTH);
					if (fYear == reservationBean.getfYear() && fMonth == reservationBean.getfMonth()
							&& fDay == reservationBean.getfDay() && lYear == reservationBean.getlYear()
							&& lMonth == reservationBean.getlMonth() && lDay == reservationBean.getlDay()) {
						hr.setUser(user);
						break;
					}
				}
			}

			service.save(hotel);
			userService.save(user);

			return true;
		}
		return false;
	}

}
