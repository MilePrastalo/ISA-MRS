package com.tim9.PlanJourney.beans;

import java.util.Date;

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
	private double minBusiness;
	private double minEconomic;
	private double minFirstClass;
	private double maxBusiness;
	private double maxEconomic;
	private double maxFirstClass;
	private String flightCompany;
	private String  economicCapacity;
	private String buisinesssCapacity;
	private String firstClassCapacity;
	
	
	public FlightBean() {
		// TODO Auto-generated constructor stub
	}


	public FlightBean(String startDestination, String endDestination, Date startDate, Date endDate, int flightDuration,
			int flightLength, double businessPrice, double economicPrice, double firstClassPrice, double minBusiness,
			double minEconomic, double minFirstClass, double maxBusiness, double maxEconomic, double maxFirstClass,
			String flightCompany, String economicCapacity, String buisinesssCapacity, String firstClassCapacity) {
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
		this.minBusiness = minBusiness;
		this.minEconomic = minEconomic;
		this.minFirstClass = minFirstClass;
		this.maxBusiness = maxBusiness;
		this.maxEconomic = maxEconomic;
		this.maxFirstClass = maxFirstClass;
		this.flightCompany = flightCompany;
		this.economicCapacity = economicCapacity;
		this.buisinesssCapacity = buisinesssCapacity;
		this.firstClassCapacity = firstClassCapacity;
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


	public double getMinBusiness() {
		return minBusiness;
	}


	public void setMinBusiness(double minBusiness) {
		this.minBusiness = minBusiness;
	}


	public double getMinEconomic() {
		return minEconomic;
	}


	public void setMinEconomic(double minEconomic) {
		this.minEconomic = minEconomic;
	}


	public double getMinFirstClass() {
		return minFirstClass;
	}


	public void setMinFirstClass(double minFirstClass) {
		this.minFirstClass = minFirstClass;
	}


	public double getMaxBusiness() {
		return maxBusiness;
	}


	public void setMaxBusiness(double maxBusiness) {
		this.maxBusiness = maxBusiness;
	}


	public double getMaxEconomic() {
		return maxEconomic;
	}


	public void setMaxEconomic(double maxEconomic) {
		this.maxEconomic = maxEconomic;
	}


	public double getMaxFirstClass() {
		return maxFirstClass;
	}


	public void setMaxFirstClass(double maxFirstClass) {
		this.maxFirstClass = maxFirstClass;
	}


	public String getFlightCompany() {
		return flightCompany;
	}


	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}


	public String getEconomicCapacity() {
		return economicCapacity;
	}


	public void setEconomicCapacity(String economicCapacity) {
		this.economicCapacity = economicCapacity;
	}


	public String getBuisinesssCapacity() {
		return buisinesssCapacity;
	}


	public void setBuisinesssCapacity(String buisinesssCapacity) {
		this.buisinesssCapacity = buisinesssCapacity;
	}


	public String getFirstClassCapacity() {
		return firstClassCapacity;
	}


	public void setFirstClassCapacity(String firstClassCapacity) {
		this.firstClassCapacity = firstClassCapacity;
	}
	
	

}
