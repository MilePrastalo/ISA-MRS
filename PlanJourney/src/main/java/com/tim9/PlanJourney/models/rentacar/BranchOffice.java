package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity

public class BranchOffice {	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RentACarCompany company;
	
	public BranchOffice() {
		super();
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
	
	
}
