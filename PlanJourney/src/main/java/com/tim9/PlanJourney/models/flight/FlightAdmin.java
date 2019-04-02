package com.tim9.PlanJourney.models.flight;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.User;

@Entity
public class FlightAdmin extends User {
	
	
	@ManyToOne()
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


	public FlightCompany getFlightCompany() {
		return flightCompany;
	}


	public void setFlightCompany(FlightCompany flightCompany) {
		this.flightCompany = flightCompany;
	}
	
	

}
