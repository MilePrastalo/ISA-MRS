package com.tim9.PlanJourney.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.models.flight.Ticket;

public class FlightBean {
	

	private String startDestination;
	private String endDestination;
	private Date startDate;
	private Date endDate;
	private int  flightDuration;
	private int flightLength;
	private double businessPrice;
	private double economicPrice;
	private double firstClassPrice;
	
	public FlightBean() {
		// TODO Auto-generated constructor stub
	}

	
	public FlightBean(String startDestination, String endDestination, Date startDate, Date endDate, int flightDuration,
			int flightLength, double businessPrice, double economicPrice, double firstClassPrice) {
		super();
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.flightDuration = flightDuration;
		this.flightLength = flightLength;
		this.businessPrice = businessPrice;
		this.economicPrice = economicPrice;
		this.firstClassPrice = firstClassPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}

	public int getFlightLength() {
		return flightLength;
	}

	public void setFlightLength(int flightLength) {
		this.flightLength = flightLength;
	}

	public double getBusinessPrice() {
		return businessPrice;
	}

	public void setBusinessPrice(double businessPrice) {
		this.businessPrice = businessPrice;
	}

	public double getEconomicPrice() {
		return economicPrice;
	}

	public void setEconomicPrice(double economicPrice) {
		this.economicPrice = economicPrice;
	}

	public double getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}



	public String getStartDestination() {
		return startDestination;
	}



	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}



	public String getEndDestination() {
		return endDestination;
	}



	public void setEndDestination(String endDestination) {
		this.endDestination = endDestination;
	}
	
	
	

}
