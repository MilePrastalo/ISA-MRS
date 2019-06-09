package com.tim9.PlanJourney.hotel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.tim9.PlanJourney.models.Company;
import com.tim9.PlanJourney.models.flight.Destination;

@Entity
public class Hotel extends Company {

	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelAdmin> admins = new HashSet<HotelAdmin>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelRoom> rooms = new HashSet<HotelRoom>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelReservation> reservations = new HashSet<HotelReservation>();

	@ManyToOne(fetch = FetchType.EAGER)
	private Destination destination;
	
	@Column(name="longitude", nullable = true)
	private float longitude;
	
	@Column(name="latitude", nullable = true)
	private float latitude;

	
	public Hotel() {
		super();
	}

	public Hotel(Long id, String name, String adress, String description, double rating) {
		super(id, name, adress, description, rating);
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

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


}
