package com.tim9.PlanJourney.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;

@Entity
public class RegisteredUser extends User {

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<FlightReservation> flightReservations = new HashSet<FlightReservation>();

	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	private Set<FriendRequest> sendRequests = new HashSet<FriendRequest>();

	@JsonIgnore
	@OneToMany(mappedBy = "reciever")
	private Set<FriendRequest> receivedRequests = new HashSet<FriendRequest>();

	@OneToMany(mappedBy = "user")
	private Set<Review> userReviews;

	@OneToMany(mappedBy = "user")
	private Set<VehicleReservation> vehicleReservations = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<HotelReservation> hotelReservations = new HashSet<>();

	@Column(name = "confirmed", nullable = true)
	private boolean confirmed;

	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "phone", nullable = true)
	private String phone;

	public RegisteredUser() {
		// TODO Auto-generated constructor stub
	}

	public RegisteredUser(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email);
	}

	public Set<VehicleReservation> getVehicleReservations() {
		return vehicleReservations;
	}

	public void setVehicleReservations(Set<VehicleReservation> vehicleReservations) {
		this.vehicleReservations = vehicleReservations;
	}

	public Set<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(Set<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	public Set<FriendRequest> getSendRequests() {
		return sendRequests;
	}

	public void setSendRequests(Set<FriendRequest> sendRequests) {
		this.sendRequests = sendRequests;
	}

	public Set<FriendRequest> getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(Set<FriendRequest> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

	public Set<HotelReservation> getHotelReservations() {
		return hotelReservations;
	}

	public void setHotelReservations(Set<HotelReservation> hotelReservations) {
		this.hotelReservations = hotelReservations;
	}

	public Set<Review> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(Set<Review> userReviews) {
		this.userReviews = userReviews;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
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

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
