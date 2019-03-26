package com.tim9.PlanJourney.models;

public class HotelAdmin extends User {
	private Hotel hotel;

	public HotelAdmin() {
		super();
	}

	public HotelAdmin(Hotel hotel) {
		super();
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
