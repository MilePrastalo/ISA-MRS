package com.tim9.PlanJourney.models;

public class RegisteredUser extends User {
	
	//lista rezervacija  letova
	//lista rezervacija  hotela
	//lista rezervacija  rent-a-car
	
	//lista prijatelja
	//lista poslatih zahteva
	//lista primljenih zahteva
	
	public RegisteredUser() {
		// TODO Auto-generated constructor stub
	}
	
	public RegisteredUser(int id, String username, String password, String firstName, String lastName, String email) {
		super(id,username,password,firstName,lastName,email);
	}

}
