package com.tim9.PlanJourney.models;

public class HotelAdmin extends User {
	private Hotel hotel;

	public HotelAdmin() {
		super();
	}

	public HotelAdmin(int id, String username, String password, String firstName, String lastName, String email,
			Hotel hotel) {
		super(id, username, password, firstName, lastName, email);
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
