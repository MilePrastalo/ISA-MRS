package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.tim9.PlanJourney.models.flight.Destination;


@Entity

public class BranchOffice {	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="address",nullable=false)
	private String address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RentACarCompany company;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Destination destination;
	
	public BranchOffice() {
		super();
	}
	
	

	public BranchOffice(String name, String address, RentACarCompany company, Destination destination) {
		super();
		this.name = name;
		this.address = address;
		this.company = company;
		this.destination = destination;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public RentACarCompany getCompany() {
		return company;
	}
	public void setCompany(RentACarCompany company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
