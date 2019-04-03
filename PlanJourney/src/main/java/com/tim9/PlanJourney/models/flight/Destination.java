package com.tim9.PlanJourney.models.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Destination {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "description", unique = false, nullable = true)
	private String description;
	
	@Column(name = "coordinates", unique = false, nullable = true)
	private String coordinates;
	
	public Destination() {
		// TODO Auto-generated constructor stub
	}

	public Destination( String naziv, String opis, String coordinates) {
		super();
		this.name = naziv;
		this.description = opis;
		this.coordinates = coordinates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return name;
	}

	public void setNaziv(String naziv) {
		this.name = naziv;
	}

	public String getOpis() {
		return description;
	}

	public void setOpis(String opis) {
		this.description = opis;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
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
	
	
	

}
