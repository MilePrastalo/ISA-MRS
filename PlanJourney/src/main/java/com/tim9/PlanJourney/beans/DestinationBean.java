package com.tim9.PlanJourney.beans;

import javax.persistence.Column;

public class DestinationBean {
	
	
	private String name;
	private String description;
	private String coordinates;
	private Long id;
	
	public DestinationBean() {
		// TODO Auto-generated constructor stub
	}

	public DestinationBean(String name, String description, String coordinates) {
		super();
		this.name = name;
		this.description = description;
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
