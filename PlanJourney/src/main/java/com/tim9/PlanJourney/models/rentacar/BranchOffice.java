package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class BranchOffice {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name",nullable=false)
	private String name;
	/*@ForeignKey(name="location")
	private Destination location;*/
	public BranchOffice() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
