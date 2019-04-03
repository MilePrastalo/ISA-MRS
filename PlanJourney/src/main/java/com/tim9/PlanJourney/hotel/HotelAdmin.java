package com.tim9.PlanJourney.hotel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.tim9.PlanJourney.models.User;

@Entity
public class HotelAdmin extends User {

	@ManyToOne
	private Hotel hotel;

	public HotelAdmin() {
	}

	public HotelAdmin(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email);
	}

	public HotelAdmin(String username, String password, String firstName, String lastName, String email, Hotel h) {
		super(username, password, firstName, lastName, email);
		this.hotel = h;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
