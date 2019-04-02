package com.tim9.PlanJourney.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class FlightAdmin extends User {
	
	
	@OneToOne( fetch = FetchType.LAZY)
	FlightCompany flightCompany;
	
	
	public FlightAdmin() {
	}
	
	
	public FlightAdmin( String username, String password, String firstName, String lastName, String email) {
		super(username,password,firstName,lastName,email);
	}
	
	public FlightAdmin( String username, String password, String firstName, String lastName, String email, FlightCompany fc) {
		super(username,password,firstName,lastName,email);
		this.flightCompany = fc;
	}

}
