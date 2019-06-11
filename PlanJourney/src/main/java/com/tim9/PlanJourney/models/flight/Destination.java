package com.tim9.PlanJourney.models.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Destination {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "coordinates")
	private String coordinates;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name="longitude", nullable = true)
	private float longitude;
	
	@Column(name="latitude", nullable = true)
	private float latitude;
	
	public Destination() {
		// TODO Auto-generated constructor stub
	}

	public Destination( String naziv, String opis, String coordinates) {
		super();
		this.name = naziv;
		this.description = opis;
		this.coordinates = coordinates;
	}
	
	
	public Destination( String name, String description, String address, float longitude, float latitude ) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
	

}
