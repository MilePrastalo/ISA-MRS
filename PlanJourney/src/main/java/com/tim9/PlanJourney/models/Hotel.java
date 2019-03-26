package com.tim9.PlanJourney.models;

import java.util.ArrayList;

public class Hotel extends Company {
	private ArrayList<HotelAdmin> admins;

	public Hotel() {
		super();
	}

	public Hotel(Long id, String name, String adress, String description, ArrayList<HotelAdmin> admins) {
		super(id, name, adress, description);
		this.admins = admins;
	}

	public ArrayList<HotelAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<HotelAdmin> admins) {
		this.admins = admins;
	}
}
