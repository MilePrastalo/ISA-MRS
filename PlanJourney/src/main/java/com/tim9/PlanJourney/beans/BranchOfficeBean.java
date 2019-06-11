package com.tim9.PlanJourney.beans;


public class BranchOfficeBean {
	private Long id;
	private String name;
	private String address;
	private String destination;
	private float longitude;
	private float latitude;
	
	public BranchOfficeBean(String name, String destination) {
		super();
		this.name = name;
		this.destination = destination;
	}
	
	public BranchOfficeBean(String name, String address, String destination) {
		super();
		this.name = name;
		this.address = address;
		this.destination = destination;
	}

	public BranchOfficeBean() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
	
	

}
