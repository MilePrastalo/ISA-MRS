package com.tim9.PlanJourney.controller;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import com.tim9.PlanJourney.beans.HotelBean;
import com.tim9.PlanJourney.beans.HotelReservationBean;
import com.tim9.PlanJourney.beans.HotelRoomBean;
import com.tim9.PlanJourney.beans.HotelSearchBean;
import com.tim9.PlanJourney.hotel.AdditionalCharges;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.City;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.service.CityService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.HotelReservationService;
import com.tim9.PlanJourney.service.HotelService;
import com.tim9.PlanJourney.service.RegisteredUserService;

@RestController
public class HotelController {
	@Autowired
	private HotelService service;

	@Autowired
	private RegisteredUserService userService;

	@Autowired
	private HotelAdminService hotelAdminService;

	@Autowired
	private HotelReservationService hotelReservationService;

	@Autowired
	private CityService cityService;

	@Autowired
	private DestinationService destinationService;

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
		System.out.println(name);
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
				reservationBean.setPaidPrice(hr.getPaidPrice());
				Set<Review> reviews = hr.getReservationReviews();
				for (Review r : reviews) {
					if (r.getUser().getUsername().equals(user.getUsername())) {
						reservationBean.setRating(r.getRating());
					}
				}
				Date today = new Date();
				long daysPassed = hr.getFirstDay().getTime() - today.getTime();
				int status;
				if (daysPassed > 24 * 2 * 3600 * 1000) {
					status = 0;
				} else if (today.getTime() - hr.getFirstDay().getTime() > 0) {
					status = 2;
				} else {
					status = 1;
				}
				reservationBean.setStatus(status);

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
				return false;
			}
			if (hb.getAddress() == null || hb.getAddress().equals("".trim())) {
				return false;
			}
			if (hb.getCityName() == null || hb.getCityName().equals("".trim())) {
				return false;
			}
			// checks if hotel already exists on given address or name in same the city.
			ArrayList<Hotel> hotels = (ArrayList<Hotel>) service.findAll();

			for (Hotel h : hotels) {
				if (h.getAddress().equals(hb.getAddress()) && h.getCity().getName().equals(hb.getCityName())) {
					return false;
				} else if (h.getName().equals(hb.getName()) && h.getCity().getName().equals(hb.getCityName())) {
					return false;
				}
			}
			// Checks if city with given name exists.
			City city = cityService.findByName(hb.getCityName());
			if (city == null) {
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) hotelAdminService.findByUsername(username);
			Hotel hotel = admin.getHotel();

			// checks if there is a room with same number;
			for (HotelRoom hr : hotel.getRooms()) {
				if (hr.getRoomNumber() == hrb.getRoomNumber()) {
					return false;
				}
			}

			// checks if number of beds is less than 1.
			if (hrb.getNumberOfBeds() < 1) {
				return false;
			}

			// checks if price per day is negative
			if (hrb.getPricePerDay() < 0) {
				return false;
			}

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

	@RequestMapping(value = "/api/updateHotelRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean updateHotelRoom(@RequestBody HotelRoomBean hrb) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) hotelAdminService.findByUsername(username);
			Hotel hotel = admin.getHotel();
			// checks if there is a room with same number;
			for (HotelRoom hr : hotel.getRooms()) {
				if (hr.getRoomNumber() == hrb.getRoomNumber()) {
					hr.setPricePerDay(hrb.getPricePerDay());
					;
				}
			}

			service.save(hotel);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/api/removeHotelRoom/{roomNumber}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean removeHotelRoom(@PathVariable("roomNumber") int roomNumber) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) hotelAdminService.findByUsername(username);
			Hotel hotel = admin.getHotel();
			HotelRoom foundRoom = null;

			// checks if there is a room with same number;
			for (HotelRoom hr : hotel.getRooms()) {
				if (hr.getRoomNumber() == roomNumber) {
					foundRoom = hr;
				}
			}

			if (foundRoom == null) {
				return false;
			}

			// checks if room is already reserved.
			for (HotelReservation hr : hotel.getReservations()) {
				if (hr.getRoom().getRoomNumber() == roomNumber) {
					return false;
				}
			}

			hotel.getRooms().remove(foundRoom);

			service.save(hotel);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/api/searchHotels", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<HotelBean> searchHotels(@RequestBody HotelSearchBean searchBean) throws Exception {
		ArrayList<Hotel> hotels = (ArrayList<Hotel>) service.findAll();
		ArrayList<HotelBean> hbs = new ArrayList<HotelBean>();

		for (Hotel h : hotels) {
			boolean hotelNameCheck = false;
			boolean bedCheck = false;
			boolean cityNameCheck = false;
			boolean minPriceCheck = false;
			boolean maxPriceCheck = false;

			// checks if hotel city equals searched city name.
			if (searchBean.getSearch() == 0) {
				if (searchBean.getCityName().equals("".trim())) {
					cityNameCheck = true;
				} else {
					if (h.getCity().getName().contains(searchBean.getCityName())) {
						cityNameCheck = true;
					} else {
						cityNameCheck = false;
					}
				}
			} else if (searchBean.getSearch() == 1) {
				Destination endDestination = destinationService.findByName(searchBean.getCityName());
				if (h.getCity() == endDestination.getCity()) {
					cityNameCheck = true;
				} else {
					cityNameCheck = false;
				}

			}
			
			System.out.println(cityNameCheck);

			// checks if hotel name equals searched hotel name.
			if (searchBean.getHotelName().equals("".trim())) {
				hotelNameCheck = true;
			} else {
				if (h.getName().contains(searchBean.getHotelName())) {
					hotelNameCheck = true;
				} else {
					hotelNameCheck = false;
				}
			}

			// checks every room in hotel, if it has searched number of beds.
			if (searchBean.getNumberOfBeds() == -1) {
				bedCheck = true;
			} else {
				for (HotelRoom hr : h.getRooms()) {
					if (hr.getNumberOfBeds() == searchBean.getNumberOfBeds()) {
						bedCheck = true;
					}
				}
			}

			// checks every room in hotel, if it has price per day <= than searched.
			if (searchBean.getMinPrice() == -1) {
				minPriceCheck = true;
			} else {
				for (HotelRoom hr : h.getRooms()) {
					if (hr.getPricePerDay() <= searchBean.getMinPrice()) {
						minPriceCheck = true;
					}
				}
			}

			// checks every room in hotel, if it has price per day >= than searched.
			if (searchBean.getMaxPrice() == -1) {
				maxPriceCheck = true;
			} else {
				for (HotelRoom hr : h.getRooms()) {
					if (hr.getPricePerDay() >= searchBean.getMaxPrice()) {
						maxPriceCheck = true;
					}
				}
			}

			if (hotelNameCheck && cityNameCheck && bedCheck && minPriceCheck && maxPriceCheck) {
				HotelBean hb = new HotelBean();
				hb.setName(h.getName());
				hb.setCityName(h.getCity().getName());
				hb.setAddress(h.getAddress());
				hb.setDescription(h.getDescription());

				hbs.add(hb);
			}

		}

		return hbs;

	}

	@RequestMapping(value = "/api/addHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody Long addHotelReservation(@RequestBody HotelReservationBean reservationBean) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Long id = service.addHotelReservation(reservationBean, username);

			return id;
		}
		return null;
	}

	// Price Reduction should be added to reservations!
	@RequestMapping(value = "/api/buyQuickHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public @ResponseBody Long buyQuickHotelReservation(@RequestBody HotelReservationBean reservationBean) {
		// checks logged user.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Long id = service.buyQuickReservation(reservationBean, username);

			return id;
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
		cal.add(Calendar.DATE, -2);
		Date dateBefore3Days = cal.getTime();
		if (new Date().after(dateBefore3Days)) {
			return false;
		}

		// Canceling quick hotel reservation
		if (hr.getDiscount() > 0 && hr.getUser() != null) {
			hr.setUser(null);
			hotelReservationService.save(hr);

			return true;
		}

		// Removing hotel reservation from flight reservation.
		/*
		 * ArrayList<HotelReservation> flightsHotelReservations = new
		 * ArrayList<HotelReservation>(); FlightReservation fr =
		 * flightReservationService.findOne(hr.getFlightReservation().getId());
		 * System.out.println(fr.getId()); for (HotelReservation h :
		 * fr.getHoteReservations()) { if (!(h.getId() == hr.getId())) {
		 * flightsHotelReservations.add(h); } } HashSet<HotelReservation> setFHR = new
		 * HashSet<HotelReservation>(); setFHR.addAll(flightsHotelReservations);
		 * fr.setHoteReservations(setFHR); flightReservationService.save(fr);
		 */
		hotelReservationService.remove(hr.getId());

		return true;
	}

}
