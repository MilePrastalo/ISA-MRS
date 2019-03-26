package com.tim9.PlanJourney.models;

public class RentACarAdmin extends RegisteredUser {
	private RentACarService service;

	public RentACarAdmin() {
		super();
	}

	public RentACarAdmin(RentACarService service) {
		super();
		this.service = service;
	}

	public RentACarService getService() {
		return service;
	}

	public void setService(RentACarService service) {
		this.service = service;
	}
	

	
}
