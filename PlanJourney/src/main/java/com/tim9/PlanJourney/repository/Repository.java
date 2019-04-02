package com.tim9.PlanJourney.repository;

import java.util.ArrayList;
import java.util.Collection;

import com.tim9.PlanJourney.models.FlightAdmin;
import com.tim9.PlanJourney.models.Hotel;
import com.tim9.PlanJourney.models.HotelAdmin;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;

@org.springframework.stereotype.Repository
public class Repository {
	private static ArrayList<SystemAdmin> systemAdmins = new ArrayList<SystemAdmin>();
	private static ArrayList<HotelAdmin> hotelAdmins = new ArrayList<HotelAdmin>();
	private static ArrayList<Hotel> hotels = new ArrayList<Hotel>();

	public Repository() {
		systemAdmins.add(new SystemAdmin( "sys", "123", "System", "Admin", "system.admin@somemail.com",
				new ArrayList<HotelAdmin>(), new ArrayList<FlightAdmin>(), new ArrayList<RentACarAdmin>()));

		Hotel h1 = new Hotel(0L, "Hotel1", "Pere Perica 0", "Test funkcionalnosti hotela.",
				new ArrayList<HotelAdmin>());
		Hotel h2 = new Hotel(1L, "Hotel2", "Zike Zikica 1", "Test funkcionalnosti hotela.",
				new ArrayList<HotelAdmin>());

		HotelAdmin hA1 = new HotelAdmin( "mika", "123", "Mika", "Mikic", "mika@mail.com", h1);
		HotelAdmin hA2 = new HotelAdmin( "jeca", "321", "Jelena", "Jelenic", "jeca@mail.com", h2);
		HotelAdmin hA3 = new HotelAdmin( "peca", "123", "Petar", "Petrovic", "peca@mail.com", h1);

		hotelAdmins.add(hA1);
		hotelAdmins.add(hA2);
		hotelAdmins.add(hA3);
		hotels.add(h1);
		hotels.add(h2);

	}

	public Collection<Hotel> getAllHotels() {
		return hotels;
	}

	public Hotel getHotel(String name) {
		for (Hotel h : hotels) {
			if (h.getName().equals(name)) {
				return h;
			}
		}
		return null;
	}

	public boolean addHotel(Hotel hotel) {
		boolean alreadyExists = false;
		for (Hotel h : hotels) {
			if (h.getName().equals(hotel.getName())) {
				alreadyExists = true;
				break;
			}
		}

		if (alreadyExists) {
			return false;
		} else {
			hotels.add(hotel);
		}
		return true;
	}

	public boolean updateHotel(Hotel hotel) {
		boolean updated = false;
		for (Hotel h : hotels) {
			if (h.getName().equals(hotel.getName())) {
				updated = true;
				hotels.remove(h);
				hotels.add(hotel);
				break;
			}
		}
		return updated;
	}

	public boolean removeHotel(String name) {
		boolean removed = false;
		for (Hotel h : hotels) {
			if (h.getName().equals(name)) {
				removed = true;
				hotels.remove(h);
				break;
			}
		}
		return removed;
	}

	public Collection<HotelAdmin> getAllHotelAdmins() {
		return hotelAdmins;
	}

	public HotelAdmin getHotelAdmin(String username) {
		for (HotelAdmin h : hotelAdmins) {
			if (h.getUsername().equals(username)) {
				return h;
			}
		}
		return null;
	}

	public boolean addHotelAdmin(HotelAdmin hotelAdmin) {
		boolean alreadyExists = false;
		for (HotelAdmin h : hotelAdmins) {
			if (h.getUsername().equals(hotelAdmin.getUsername())) {
				alreadyExists = true;
				break;
			}
		}

		if (alreadyExists) {
			return false;
		} else {
			hotelAdmins.add(hotelAdmin);
		}
		return true;
	}

	public boolean updateHotelAdmin(HotelAdmin hotelAdmin) {
		boolean updated = false;
		for (HotelAdmin h : hotelAdmins) {
			if (h.getUsername().equals(hotelAdmin.getUsername())) {
				updated = true;
				hotelAdmins.remove(h);
				hotelAdmins.add(hotelAdmin);
				break;
			}
		}
		return updated;
	}

	public boolean removeHotelAdmin(String username) {
		boolean removed = false;
		for (HotelAdmin h : hotelAdmins) {
			if (h.getUsername().equals(username)) {
				removed = true;
				hotelAdmins.remove(h);
				break;
			}
		}
		return removed;
	}
}
