package com.tim9.PlanJourney.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
@Entity
public class RegisteredUser extends User {
	
	//lista rezervacija  letova
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
		super(username,password,firstName,lastName,email);
	}

	public Set<VehicleReservation> getVehicleReservations() {
		return vehicleReservations;
	}

	public void setVehicleReservations(Set<VehicleReservation> vehicleReservations) {
		this.vehicleReservations = vehicleReservations;
	}
	

}
