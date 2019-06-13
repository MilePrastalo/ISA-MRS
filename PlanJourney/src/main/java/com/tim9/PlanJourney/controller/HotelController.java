package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import com.tim9.PlanJourney.hotel.AdditionalCharges;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.City;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.service.CityService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.FlightReservationService;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.HotelReservationService;
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

	@Autowired
	private HotelAdminService hotelAdminService;

	@Autowired
	private HotelReservationService hotelReservationService;

	@Autowired
	private FlightReservationService flightReservationService;

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/api/getAllHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<HotelBean> getAllHotels() throws Exception {
		ArrayList<Hotel> hotels = (ArrayList<Hotel>) service.findAll();
		ArrayList<HotelBean> hotelBeans = new ArrayList<HotelBean>();
		for (Hotel h : hotels) {
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
				ArrayList<AdditionalCharges> ac = new ArrayList<AdditionalCharges>(r.getAdditionalCharges());
				roomBean.setAdditionalCharges(ac);
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

			hb.setCityName(h.getCity().getName());
			hotelBeans.add(hb);
		}
		return hotelBeans;
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
		hb.setCityName(h.getCity().getName());
		hb.setLatitude(h.getLatitude());
		hb.setLongitude(h.getLongitude());
		ArrayList<HotelRoomBean> rb = new ArrayList<HotelRoomBean>();
		for (HotelRoom r : h.getRooms()) {
			HotelRoomBean roomBean = new HotelRoomBean();
			roomBean.setRoomNumber(r.getRoomNumber());
			roomBean.setNumberOfBeds(r.getNumberOfBeds());
			roomBean.setPricePerDay(r.getPricePerDay());
			ArrayList<AdditionalCharges> ac = new ArrayList<AdditionalCharges>(r.getAdditionalCharges());
			roomBean.setAdditionalCharges(ac);
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
	public @ResponseBody boolean addHotel(@RequestBody HotelBean hb) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			if (hb.getName() == null || hb.getName().equals("".trim())) {
				System.out.println("Puko 1");
				return false;
			}
			if (hb.getAddress() == null || hb.getAddress().equals("".trim())) {
				System.out.println("Puko 2");
				return false;
			}
			if (hb.getCityName() == null || hb.getCityName().equals("".trim())) {
				System.out.println("Puko 3");
				return false;
			}
			// checks if hotel already exists on given address or name in same the city.
			ArrayList<Hotel> hotels = (ArrayList<Hotel>) service.findAll();

			for (Hotel h : hotels) {
				if (h.getAddress().equals(hb.getAddress()) && h.getCity().getName().equals(hb.getCityName())) {
					System.out.println("Puko 4");
					return false;
				} else if (h.getName().equals(hb.getName()) && h.getCity().getName().equals(hb.getCityName())) {
					System.out.println("Puko 5");
					return false;
				}
			}
			// Checks if city with given name exists.
			City city = cityService.findByName(hb.getCityName());
			if (city == null) {
				System.out.println("Puko 6");
				return false;
			}

			Hotel newHotel = new Hotel();
			newHotel.setName(hb.getName());
			newHotel.setAddress(hb.getAddress());
			newHotel.setDescription(hb.getDescription());
			newHotel.setLatitude(hb.getLatitude());
			newHotel.setLongitude(hb.getLongitude());
			newHotel.setCity(city);

			service.save(newHotel);

			return true;

		}
		System.out.println("Puko 7");
		return false;
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

	// Metod za izmenu osnovnih podataka hotela.
	@RequestMapping(value = "/api/updateHotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean updateHotel(@RequestBody HotelBean hb) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) hotelAdminService.findByUsername(username);
			Hotel hotel = admin.getHotel();

			if (hotel.getName().equals(hb.getName())) {
				hotel.setAddress(hb.getAddress());
				hotel.setDescription(hb.getDescription());
				hotel.setLatitude(hb.getLatitude());
				hotel.setLongitude(hb.getLongitude());

				service.save(hotel);
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/api/addHotelRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean addHotelRoom(@RequestBody HotelRoomBean hrb) {
		System.out.println(hrb);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) hotelAdminService.findByUsername(username);
			Hotel hotel = admin.getHotel();

			HotelRoom hr = new HotelRoom();
			hr.setNumberOfBeds(hrb.getNumberOfBeds());
			hr.setPricePerDay(hrb.getPricePerDay());
			hr.setRating(0);
			hr.setRoomNumber(hrb.getRoomNumber());
			Set<AdditionalCharges> ac = new HashSet<AdditionalCharges>(hrb.getAdditionalCharges());
			hr.setAdditionalCharges(ac);
			hotel.getRooms().add(hr);

			service.save(hotel);
			return true;
		}
		return false;
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
				if (h.getCity().getName().equals(val)) {
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
	public @ResponseBody Long addHotelReservation(@RequestBody HotelReservationBean reservationBean) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// checks if hotel already exists on given address and same destination.
			Hotel hotel = service.findByName(reservationBean.getHotelName());
			if (hotel == null) {
				return null;
			}
			RegisteredUser user = userService.findByUsername(reservationBean.getUsername());
			if (user == null) {
				return null;
			}
			HotelRoom room = null;
			for (HotelRoom r : hotel.getRooms()) {
				if (r.getRoomNumber() == reservationBean.getRoomNumber()) {
					room = r;
					break;
				}
			}
			if (room == null) {
				return null;
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
				return null;
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

			// Adds choosen additional charges to reservation.
			for (String aCName : reservationBean.getAdditionalCharges()) {
				for (AdditionalCharges ac : room.getAdditionalCharges()) {
					if (ac.getName().equals(aCName)) {
						reservation.getAdditionalCharges().add(ac);
					}
				}
			}
			// service.save(hotel);
			HotelReservation newReservation = hotelReservationService.save(reservation);
			System.out.println(newReservation.getId());
			// userService.save(user);

			return newReservation.getId();
		}
		return null;
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
	public @ResponseBody Long buyQuickHotelReservation(@RequestBody HotelReservationBean reservationBean) {
		// checks logged user.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// checks if hotel already exists on given address and same destination.
			Hotel hotel = service.findByName(reservationBean.getHotelName());
			if (hotel == null) {
				return null;
			}

			// checks registered user
			String username = authentication.getName();
			RegisteredUser user = userService.findByUsername(username);
			if (user == null) {
				return null;
			}

			// Splits date and checks if reservation date is valid.
			Calendar c = Calendar.getInstance();
			HotelReservation foundReservation = new HotelReservation();
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
						foundReservation = hr;
						break;
					}
				}
			}

			// service.save(hotel);
			// userService.save(user);
			foundReservation.setUser(user);
			hotelReservationService.save(foundReservation);

			return foundReservation.getId();
		}
		return null;
	}

	@RequestMapping(value = "/api/cancelHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody boolean cancelHotelReservation(@RequestBody HotelReservationBean reservationBean) {
		HotelReservation hr = hotelReservationService.findOne(reservationBean.getId());
		System.out.println(reservationBean.getId());
		if (!hr.getUser().getUsername().equals(reservationBean.getUsername())) {
			return false;
		}

		if (!hr.getHotel().getName().equals(reservationBean.getHotelName())) {
			return false;
		}
		// Checks if cancelation is less than 3 days before.
		Calendar cal = Calendar.getInstance();
		cal.setTime(hr.getFirstDay());
		cal.add(Calendar.DATE, -3);
		Date dateBefore3Days = cal.getTime();
		if (dateBefore3Days.after(new Date())) {
			return false;
		}
		// Removing hotel reservation from flight reservation.
		ArrayList<HotelReservation> flightsHotelReservations = new ArrayList<HotelReservation>();
		FlightReservation fr = flightReservationService.findOne(hr.getFlightReservation().getId());
		System.out.println(fr.getId());
		for (HotelReservation h : fr.getHoteReservations()) {
			if (!(h.getId() == hr.getId())) {
				flightsHotelReservations.add(h);
			}
		}
		HashSet<HotelReservation> setFHR = new HashSet<HotelReservation>();
		setFHR.addAll(flightsHotelReservations);
		fr.setHoteReservations(setFHR);
		flightReservationService.save(fr);
		hotelReservationService.remove(hr.getId());

		return true;
	}

}
