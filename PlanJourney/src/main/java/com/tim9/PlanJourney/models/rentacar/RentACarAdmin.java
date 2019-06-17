package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
		super(username, password, firstName, lastName, email);
	}

	public RentACarCompany getService() {
		return service;
	}

	public void setService(RentACarCompany service) {
		this.service = service;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
