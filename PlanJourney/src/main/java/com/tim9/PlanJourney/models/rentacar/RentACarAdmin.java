package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim9.PlanJourney.models.User;

@Entity
@Table(name = "RentACarAdmin")
public class RentACarAdmin extends User {
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("admins")
	private RentACarCompany service;

	public RentACarAdmin() {
		super();
	}

	public RentACarAdmin(Long id, RentACarCompany service) {
		super();
	}

	public RentACarAdmin(String username, String password, String firstName, String lastName, String email) {
		super(0l, username, password, firstName, lastName, email);
	}

	public RentACarCompany getService() {
		return service;
	}

	public void setService(RentACarCompany service) {
		this.service = service;
	}

}
