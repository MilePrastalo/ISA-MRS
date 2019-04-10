package com.tim9.PlanJourney.hotel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim9.PlanJourney.models.User;

@Entity
public class HotelAdmin extends User {

	@ManyToOne
	@JsonIgnoreProperties("admins")
	private Hotel hotel;

	public HotelAdmin() {
	}

	public HotelAdmin(String username, String password, String firstName, String lastName, String email) {
		super(0l,username, password, firstName, lastName, email);
	}

	public HotelAdmin(String username, String password, String firstName, String lastName, String email, Hotel h) {
		super(0l,username, password, firstName, lastName, email);
		this.hotel = h;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
