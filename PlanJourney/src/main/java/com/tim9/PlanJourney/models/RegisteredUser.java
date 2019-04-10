package com.tim9.PlanJourney.models;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
@Entity
public class RegisteredUser extends User {
	
	@OneToMany(mappedBy="user")
	private Set<Ticket> flightReservations = new HashSet<Ticket>(); 
	//lista rezervacija  hotela
	//lista rezervacija  rent-a-car
	
	//lista prijatelja
	//lista poslatih zahteva
	//lista primljenih zahteva
	@OneToMany(mappedBy="user")
	private Set<VehicleReservation> vehicleReservations;
	public RegisteredUser() {
		// TODO Auto-generated constructor stub
	}
	
	public RegisteredUser( String username, String password, String firstName, String lastName, String email) {
		super(0l,username,password,firstName,lastName,email);
	}

	public Set<VehicleReservation> getVehicleReservations() {
		return vehicleReservations;
	}

	public void setVehicleReservations(Set<VehicleReservation> vehicleReservations) {
		this.vehicleReservations = vehicleReservations;
	}

	public Set<Ticket> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(Set<Ticket> flightReservations) {
		this.flightReservations = flightReservations;
	}
	
	

}
