package com.tim9.PlanJourney.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Hotel;
import com.tim9.PlanJourney.models.HotelAdmin;
import com.tim9.PlanJourney.repository.Repository;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Repository repository;

	@Override
	public Collection<Hotel> getAllHotels() {
		logger.info("> getAllHotels");
		Collection<Hotel> hotels = repository.getAllHotels();
		logger.info("< getAllHotels");
		return hotels;
	}

	@Override
	public Hotel getHotel(String name) {
		logger.info("> getHotel name:{}", name);
		Hotel hotel = repository.getHotel(name);
		logger.info("< getHotel name:{}", name);
		return hotel;
	}

	@Override
	public boolean addHotel(Hotel hotel) {
		logger.info("> addHotel name:{}", hotel.getName());
		boolean added = repository.addHotel(hotel);
		logger.info("< addHotel name:{}", hotel.getName());
		return added;
	}

	@Override
	public boolean updateHotel(Hotel hotel) {
		logger.info("> updateHotel name:{}", hotel.getName());
		boolean updated = repository.updateHotel(hotel);
		logger.info("< addHotel name:{}", hotel.getName());
		return updated;
	}

	@Override
	public boolean removeHotel(String name) {
		logger.info("> removeHotel name:{}", name);
		boolean removed = repository.removeHotel(name);
		logger.info("< updateHotel name:{}", name);
		return removed;
	}

	@Override
	public Collection<HotelAdmin> getAllHotelAdmins() {
		logger.info("> getAllHotelAdmins");
		Collection<HotelAdmin> hotelAdmins = repository.getAllHotelAdmins();
		logger.info("< getAllHotelAdmins");
		return hotelAdmins;
	}

	@Override
	public HotelAdmin getHotelAdmin(String username) {
		logger.info("> getHotelAdmin name:{}", username);
		HotelAdmin hotelAdmin = repository.getHotelAdmin(username);
		logger.info("< getHotelAdmin name:{}", username);
		return hotelAdmin;
	}

	@Override
	public boolean addHotelAdmin(HotelAdmin hotelAdmin) {
		logger.info("> addHotelAdmin name:{}", hotelAdmin.getUsername());
		boolean added = repository.addHotelAdmin(hotelAdmin);
		logger.info("< addHotelAdmin name:{}", hotelAdmin.getUsername());
		return added;
	}

	@Override
	public boolean updateHotelAdmin(HotelAdmin hotelAdmin) {
		logger.info("> updateHotel name:{}", hotelAdmin.getUsername());
		boolean updated = repository.updateHotelAdmin(hotelAdmin);
		logger.info("< addHotel name:{}", hotelAdmin.getUsername());
		return updated;
	}

	@Override
	public boolean removeHotelAdmin(String username) {
		logger.info("> removeHotel name:{}", username);
		boolean removed = repository.removeHotelAdmin(username);
		logger.info("< updateHotel name:{}", username);
		return removed;
	}

}
