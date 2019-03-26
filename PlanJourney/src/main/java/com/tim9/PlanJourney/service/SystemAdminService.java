package com.tim9.PlanJourney.service;

import java.util.Collection;

import com.tim9.PlanJourney.models.Hotel;
import com.tim9.PlanJourney.models.HotelAdmin;

public interface SystemAdminService {

	// SystemAdmin poseduje CRUD metode za kompanije i admine tih kompanija.

	// CRUD metode za system admine.
//	Collection<SystemAdmin> getAllSystemAdmins();
//	SystemAdmin getSystemAdmin(String username);
//	boolean addSystemAdmin(SystemAdmin systemAdmin);
//	boolean updateSystemAdmin(SystemAdmin systemAdmin);
//	boolean removeSystemAdmin(String username);

	// CRUD metode za Hotel klasu.
	Collection<Hotel> getAllHotels();

	Hotel getHotel(String name);

	boolean addHotel(Hotel hotel);

	boolean updateHotel(Hotel hotel);

	boolean removeHotel(String name);

	// CRUD metode za HotelAdmin klasu.
	Collection<HotelAdmin> getAllHotelAdmins();

	HotelAdmin getHotelAdmin(String username);

	boolean addHotelAdmin(HotelAdmin hotelAdmin);

	boolean updateHotelAdmin(HotelAdmin hotelAdmin);

	boolean removeHotelAdmin(String username);

}
