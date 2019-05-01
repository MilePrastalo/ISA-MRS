package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
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

import com.tim9.PlanJourney.beans.HotelReservationBean;
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
	public Hotel getHotel(@PathVariable("name") String name) {

		Hotel hotel = service.findByName(name);

		return hotel;
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
			RegisteredUser user = userService.findByUsername(reservationBean.getUsername());
			HotelRoom room = null;
			for (HotelRoom r : hotel.getRooms()) {
				if (r.getRoomNumber() == reservationBean.getRoomNumber()) {
					room = r;
					break;
				}
			}
			long time = reservationBean.getLastDay().getTime() - reservationBean.getFirstDay().getTime();
			long days = TimeUnit.MILLISECONDS.toDays(time);

			HotelReservation reservation = new HotelReservation();
			reservation.setFirstDay(reservationBean.getFirstDay());
			reservation.setLastDay(reservationBean.getLastDay());
			reservation.setHotel(hotel);
			reservation.setRoom(room);
			reservation.setPaidPrice(days * room.getPricePerDay());
			hotel.getReservations().add(reservation);
			user.getHotelReservations().add(reservation);

			service.save(hotel);
			userService.save(user);

			return true;
		}
		return false;
	}

}
