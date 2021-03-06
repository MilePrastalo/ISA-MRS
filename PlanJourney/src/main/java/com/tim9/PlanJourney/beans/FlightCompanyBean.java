package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class FlightCompanyBean {
	
	private Long id;
	private String name;
	private String address;
	private String description;
	private String seatsInfo;
	private String laguageInfo;
	private ArrayList<DestinationBean> airports;
	private double rating;
	
	public FlightCompanyBean() {
		// TODO Auto-generated constructor stub
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public FlightCompanyBean(Long id, String name, String address, String description, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}
	
	public FlightCompanyBean(Long id, String name, String address, String description, double rating, String laguageInfo, String seatsConfiguration) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = rating;
		this.laguageInfo = laguageInfo;
		this.seatsInfo = seatsConfiguration;
	}

	public ArrayList<DestinationBean> getAirports() {
		return airports;
	}

	public void setAirports(ArrayList<DestinationBean> airports) {
		this.airports = airports;
	}

	public String getSeatsInfo() {
		return seatsInfo;
	}

	public void setSeatsInfo(String seatsInfo) {
		this.seatsInfo = seatsInfo;
	}

	public String getLaguageInfo() {
		return laguageInfo;
	}

	public void setLaguageInfo(String laguageInfo) {
		this.laguageInfo = laguageInfo;
	}
	

}
