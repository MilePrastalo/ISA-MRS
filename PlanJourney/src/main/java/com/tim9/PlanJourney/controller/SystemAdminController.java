package com.tim9.PlanJourney.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.Hotel;
import com.tim9.PlanJourney.models.HotelAdmin;
import com.tim9.PlanJourney.service.SystemAdminService;

@RestController
public class SystemAdminController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemAdminService systemAdminService;

	// Controller za Hotel CRUD metode.

	@RequestMapping(value = "/api/getAllHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hotel>> getAllHotels() {
		logger.info("> getAllHotels");

		Collection<Hotel> hotels = systemAdminService.getAllHotels();

		logger.info("< getAllHotels");

		return new ResponseEntity<Collection<Hotel>>(hotels, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/getHotel/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> getHotel(@PathVariable("name") String name) {
		logger.info("> getHotel name:{}", name);

		Hotel hotel = systemAdminService.getHotel(name);

		logger.info("< getHotel name:{}", name);

		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/removeHotel/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Hotel> removeHotel(@PathVariable("name") String name) {
		logger.info("> deleteHotel name:{}", name);

		boolean removed = systemAdminService.removeHotel(name);

		logger.info("< deleteHotel name:{}", name);

		if (removed) {
			return new ResponseEntity<Hotel>(HttpStatus.OK);
		}
		return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/api/addHotel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		logger.info("> addHotel name:{}", hotel.getName());

		boolean added = systemAdminService.addHotel(hotel);

		logger.info("< addHotel name:{}", hotel.getName());

		if (added) {
			return new ResponseEntity<Hotel>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Hotel>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/updateHotel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
		logger.info("> updateHotel name:{}", hotel.getName());

		boolean updated = systemAdminService.updateHotel(hotel);

		logger.info("< updateHotel name:{}", hotel.getName());

		if (updated) {
			return new ResponseEntity<Hotel>(HttpStatus.OK);
		}
		return new ResponseEntity<Hotel>(HttpStatus.CONFLICT);
	}

	// Controller za HotelAdmina CRUD metode.

	@RequestMapping(value = "/api/getAllHotelAdmins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelAdmin>> getAllHotelAdmins() {
		logger.info("> getAllHotelAdmins");

		Collection<HotelAdmin> hotelAdmins = systemAdminService.getAllHotelAdmins();

		logger.info("< getAllHotelAdmins");

		return new ResponseEntity<Collection<HotelAdmin>>(hotelAdmins, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/getHotelAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelAdmin> getHotelAdmin(@PathVariable("username") String username) {
		logger.info("> getHotelAdmin username:{}", username);

		HotelAdmin hotelAdmin = systemAdminService.getHotelAdmin(username);

		logger.info("< getHotelAdmin username:{}", username);

		return new ResponseEntity<HotelAdmin>(hotelAdmin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/removeHotelAdmin/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<HotelAdmin> removeHotelAdmin(@PathVariable("username") String username) {
		logger.info("> deleteHotelAdmin username:{}", username);

		boolean removed = systemAdminService.removeHotelAdmin(username);

		logger.info("< deleteHotelAdmin username:{}", username);

		if (removed) {
			return new ResponseEntity<HotelAdmin>(HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/api/addHotelAdmin", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelAdmin> addHotelAdmin(@RequestBody HotelAdmin hotelAdmin) {
		logger.info("> addHotelAdmin username:{}", hotelAdmin.getUsername());

		boolean added = systemAdminService.addHotelAdmin(hotelAdmin);

		logger.info("< addHotelAdmin username:{}", hotelAdmin.getUsername());

		if (added) {
			return new ResponseEntity<HotelAdmin>(HttpStatus.CREATED);
		}
		return new ResponseEntity<HotelAdmin>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/updateHotelAdmin", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelAdmin> updateHotelAdmin(@RequestBody HotelAdmin hotelAdmin) {
		logger.info("> updateHotelAdmin username:{}", hotelAdmin.getUsername());

		boolean updated = systemAdminService.updateHotelAdmin(hotelAdmin);

		logger.info("< updateHotelAdmin username:{}", hotelAdmin.getUsername());

		if (updated) {
			return new ResponseEntity<HotelAdmin>(HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(HttpStatus.CONFLICT);
	}

}
