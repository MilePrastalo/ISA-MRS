package com.tim9.PlanJourney.models;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class FlightCompany extends Company {
	
	private ArrayList<FlightAdmin> flightAdmins;
	private ArrayList<Destination> destinations;
	//letovi
	
	public FlightCompany() {
		super();
	}
	
	public FlightCompany(Long id, String name, String adress, String description) {
		super(id,name,adress, description);
	}

}
