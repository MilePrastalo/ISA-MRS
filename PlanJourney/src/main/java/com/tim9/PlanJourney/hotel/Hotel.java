package com.tim9.PlanJourney.hotel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;

@Entity
public class Hotel extends Company {

	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelAdmin> admins = new HashSet<HotelAdmin>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelRoom> rooms = new HashSet<HotelRoom>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelReservation> reservations = new HashSet<HotelReservation>();

	public Hotel() {
		super();
	}

	public Hotel(Long id, String name, String adress, String description) {
		super(id, name, adress, description);
	}

	public Set<HotelAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<HotelAdmin> admins) {
		this.admins = admins;
	}

	public Set<HotelRoom> getRooms() {
		return rooms;
	}

	public void setRooms(Set<HotelRoom> rooms) {
		this.rooms = rooms;
	}

	public Set<HotelReservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<HotelReservation> reservations) {
		this.reservations = reservations;
	}

}
