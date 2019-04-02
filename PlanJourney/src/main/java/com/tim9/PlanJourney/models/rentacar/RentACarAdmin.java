package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tim9.PlanJourney.models.User;

@Entity
@Table(name="RentACarAdmin")
public class RentACarAdmin extends User {
	@ManyToOne(fetch = FetchType.EAGER)
	private RentACarCompany service;

	public RentACarAdmin() {
		super();
	}

	public RentACarAdmin(RentACarCompany service) {
		super();
		this.service = service;
	}

	public RentACarCompany getService() {
		return service;
	}

	public void setService(RentACarCompany service) {
		this.service = service;
	}

	
	
	

	
}
