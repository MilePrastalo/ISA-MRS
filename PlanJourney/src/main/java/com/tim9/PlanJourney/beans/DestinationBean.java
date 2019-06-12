package com.tim9.PlanJourney.beans;


public class DestinationBean {
	
	
	private String name;
	private String description;
	private String coordinates;
	private String address;
	private float longitude;
	private float latitude;
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
	
	public DestinationBean(long id, String name, String description, String address, float longitude, float latitude ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public void setLatitude(float latidude) {
		this.latitude = latidude;
	}
	
	

}
