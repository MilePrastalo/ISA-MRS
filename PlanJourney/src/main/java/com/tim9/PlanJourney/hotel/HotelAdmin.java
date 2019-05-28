package com.tim9.PlanJourney.hotel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim9.PlanJourney.models.User;

@Entity
public class HotelAdmin extends User {

	@ManyToOne
	@JsonIgnoreProperties({ "admins", "reservations" })
	private Hotel hotel;
	

	public HotelAdmin() {
		super();
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

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
