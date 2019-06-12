package com.tim9.PlanJourney.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public City(String name) {
		this.name = name;
	}

	public City() {
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
