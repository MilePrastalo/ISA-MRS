package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	private HotelService service;

	@RequestMapping(value = "/api/getAllHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ArrayList<Hotel> getAllHotels() throws Exception {
		ArrayList<Hotel> hotel = (ArrayList<Hotel>) service.findAll();

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
	public @ResponseBody ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		if (service.findByAddress(hotel.getAddress()) == null && service.findByName(hotel.getName()) == null) {
			Hotel h = (Hotel) service.save(hotel);
			return new ResponseEntity<Hotel>(h, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeHotel/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<Hotel> removeHotel(@PathVariable("name") String name) {

		Hotel hotel = service.findByName(name);

		if (hotel == null) {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
		}

		service.remove(hotel.getId());
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	// Metod za izmenu hotela, dodavanje soba, rezervacija...
	@RequestMapping(value = "/api/updateHotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
		Hotel existingHotel = service.findOne(hotel.getId());
		if (existingHotel.getAddress().equals(hotel.getAddress()) && existingHotel.getName().equals(hotel.getName())) {
			Hotel h = (Hotel) service.save(hotel);
			return new ResponseEntity<Hotel>(h, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.CONFLICT);
		}
	}
}
