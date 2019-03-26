package com.tim9.PlanJourney.models;

public class FlightAdmin extends User {
	
	FlightCompany flightCompany;
	
	
	public FlightAdmin() {
	}
	
	
	public FlightAdmin(int id, String username, String password, String firstName, String lastName, String email) {
		super(id,username,password,firstName,lastName,email);
	}
	
	public FlightAdmin(int id, String username, String password, String firstName, String lastName, String email, FlightCompany fc) {
		super(id,username,password,firstName,lastName,email);
		this.flightCompany = fc;
	}

}
