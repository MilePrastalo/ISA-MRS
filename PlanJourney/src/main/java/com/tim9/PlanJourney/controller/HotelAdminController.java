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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.QuickHotelReservationBean;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.hotel.QuickReservationPriceReduction;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.HotelService;
import com.tim9.PlanJourney.service.QuickReservationPriceReductionService;
import com.tim9.PlanJourney.service.UserService;

@RestController
public class HotelAdminController {

	@Autowired
	private HotelAdminService adminService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private UserService userService;

	@Autowired
	private QuickReservationPriceReductionService quickPriceService;

	@RequestMapping(value = "/api/getHotelAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public ResponseEntity<HotelAdmin> getHotelAdmin(@PathVariable("username") String username) {
		HotelAdmin admin = adminService.findByUsername(username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Checks if admin exists.
			if (admin == null) {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}
			return new ResponseEntity<HotelAdmin>(admin, HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/getHotelAdminProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	// Method returns hotel admin information
	public @ResponseBody HotelAdmin getHotelAdminProfile() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			HotelAdmin user = (HotelAdmin) userService.findOneByUsername(username);
			System.out.println(user.getHotel().getName());
			System.out.println(user.getHotel().getDestination().getName());
			return user;
		}
		return null;
	}

	@RequestMapping(value = "/api/addHotelAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody ResponseEntity<HotelAdmin> addHotelAdmin(@RequestBody HotelAdmin admin) {
		// Finds if new admin's given hotel exists.
		Hotel h = hotelService.findByName(admin.getHotel().getName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (adminService.findByUsername(admin.getUsername()) == null && h != null) {
				Authority authority = authorityService.findByName("HOTEL_ADMIN");
				ArrayList<Authority> auth = new ArrayList<Authority>();
				auth.add(authority);
				admin.setAuthorities(auth);

				admin.setHotel(h);
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				admin.setPassword(encoder.encode(admin.getPassword()));
				HotelAdmin a = (HotelAdmin) adminService.save(admin);
				return new ResponseEntity<HotelAdmin>(a, HttpStatus.OK);
			} else {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/removeHotelAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<HotelAdmin> removeHotelAdmin(@PathVariable("username") String username) {

		HotelAdmin admin = adminService.findByUsername(username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Checks if admin exists.
			if (admin == null) {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}

			adminService.remove(admin.getId());
			return new ResponseEntity<HotelAdmin>(admin, HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/updateHotelAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody HotelAdmin updateHotelAdmin(@RequestBody HotelAdmin hotelAdmin) {
		HotelAdmin existingHotelAdmin = adminService.findOne(hotelAdmin.getId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (existingHotelAdmin.getUsername().equals(hotelAdmin.getUsername())) {
				HotelAdmin h = (HotelAdmin) adminService.save(hotelAdmin);
				return h;
			}
		}
		return null;
	}

	// Difference between quick reservation and normal reservation is lack of user
	// and price reduction in quick reservation.
	@RequestMapping(value = "/api/addQuickHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean addQuickHotelReservations(@RequestBody QuickHotelReservationBean reservationBean) {

		// Checks if hotel exists.
		Hotel hotel = hotelService.findByName(reservationBean.getHotelName());
		if (hotel == null) {
			return false;
		}
		// Finds selected room and check if it exists.
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
		// Splits date and checks if reservation date is valid.
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
		
		// Adds new reservation
		HotelReservation reservation = new HotelReservation();
		reservation.setFirstDay(firstDay);
		reservation.setLastDay(lastDay);
		reservation.setHotel(hotel);
		reservation.setRoom(room);
		reservation.setPaidPrice(days * room.getPricePerDay() * (reservationBean.getPriceReduction() / 100));
		hotel.getReservations().add(reservation);
		hotelService.save(hotel);
		
		// Adds price reduction for new reservation
		QuickReservationPriceReduction qrpr = new QuickReservationPriceReduction();
		qrpr.setReservation(reservation);
		qrpr.setPriceReduction(reservationBean.getPriceReduction());
		quickPriceService.save(qrpr);

		return true;
	}

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

}
