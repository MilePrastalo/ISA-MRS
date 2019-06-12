package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class RentACarProfileBean {
	private String name;
	private String address;
	private String description;
	
	private double rating;
	
	private ArrayList<VehicleSearchReturnBean> vehicles;
	private ArrayList<BranchOfficeBean> offices;
	public RentACarProfileBean() {
		super();
	}
	
	public RentACarProfileBean(String name, String address, String description) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
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

	public ArrayList<VehicleSearchReturnBean> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<VehicleSearchReturnBean> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<BranchOfficeBean> getOffices() {
		return offices;
	}

	public void setOffices(ArrayList<BranchOfficeBean> offices) {
		this.offices = offices;
	}
	
	
	
}
