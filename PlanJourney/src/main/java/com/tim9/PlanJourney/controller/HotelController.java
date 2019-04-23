package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

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

import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	private HotelService service;

	@Autowired
	private DestinationService destinationService;

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
	public ResponseEntity<Hotel> getHotel(@PathVariable("name") String name) {

		Hotel hotel = service.findByName(name);

		if (hotel == null) {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
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
}
