package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.rentacar.BranchOffice;

public class RentACarCompanySearchBean {
	private String name;
	private ArrayList<BranchOfficeBean> offices;
	private double rating;
	private String location;
	private String dateFrom;
	private String dateTo;
	public RentACarCompanySearchBean(String name, ArrayList<BranchOfficeBean> offices, double rating, String location,
			String dateFrom, String dateTo) {
		super();
		this.name = name;
		this.offices = offices;
		this.rating = rating;
		this.location = location;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public RentACarCompanySearchBean() {
		super();
	}
	public RentACarCompanySearchBean(String name, ArrayList<BranchOfficeBean> offices, double rating) {
		super();
		this.name = name;
		this.offices = offices;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<BranchOfficeBean> getOffices() {
		return offices;
	}
	public void setOffices(ArrayList<BranchOfficeBean> offices) {
		this.offices = offices;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	
	
	

}
