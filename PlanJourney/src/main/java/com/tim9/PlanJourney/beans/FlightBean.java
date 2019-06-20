package com.tim9.PlanJourney.beans;

import java.util.Date;

import com.tim9.PlanJourney.models.flight.Flight;

public class FlightBean {

	private String startDestination;
	private String endDestination;
	private Date startDate;
	private String startDate_str;
	private String endDate_str;
	private Date endDate;
	private int flightDuration;
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
	private Long id;
	private Long companyId;
	private double rate;

	private int firstClassRows;
	private int firstClassColumns;
	private int businessRows;
	private int businessColumns;
	private int economicRows;
	private int economicColumns;

	private float latitude;
	private float longitude;
	private String additionalServices;
	private String transitions;
	private int transitionsNum;
	
	private String cityName;

	public FlightBean() {
		// TODO Auto-generated constructor stub
	}

	public FlightBean(String startDestination, String endDestination, Date startDate, String startDate_str,
			String endDate_str, Date endDate, int flightDuration, int flightLength, double businessPrice,
			double economicPrice, double firstClassPrice, double minBusiness, double minEconomic, double minFirstClass,
			double maxBusiness, double maxEconomic, double maxFirstClass, String flightCompany, Long id, Long companyId,
			double rate, int firstClassRows, int firstClassColumns, int businessRows, int businessColumns,
			int economicRows, int economicColumns) {
		super();
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.startDate = startDate;
		this.startDate_str = startDate_str;
		this.endDate_str = endDate_str;
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
		this.id = id;
		this.companyId = companyId;
		this.rate = rate;
		this.firstClassRows = firstClassRows;
		this.firstClassColumns = firstClassColumns;
		this.businessRows = businessRows;
		this.businessColumns = businessColumns;
		this.economicRows = economicRows;
		this.economicColumns = economicColumns;
	}

	public FlightBean(Flight flight, String companyName, String startDate, String endDate) {
		this.startDestination = flight.getStartDestination().getName();
		this.endDestination = flight.getEndDestination().getName();
		this.startDate = flight.getStartDate();
		this.endDate = flight.getEndDate();
		this.flightDuration = flight.getFlightDuration();
		this.flightLength = flight.getFlightLength();
		this.businessPrice = flight.getBusinessPrice();
		this.economicPrice = flight.getEconomicPrice();
		this.firstClassPrice = flight.getFirstClassPrice();
		this.flightCompany = companyName;
		this.id = flight.getId();
		this.startDate_str = startDate;
		this.endDate_str = endDate;
		this.latitude = flight.getEndDestination().getLatitude();
		this.longitude = flight.getEndDestination().getLongitude();
		this.additionalServices = flight.getAdditionalServices();
		this.transitions = flight.getTransitions();
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

	public String getStartDate_str() {
		return startDate_str;
	}

	public void setStartDate_str(String startDate_str) {
		this.startDate_str = startDate_str;
	}

	public String getEndDate_str() {
		return endDate_str;
	}

	public void setEndDate_str(String endDate_str) {
		this.endDate_str = endDate_str;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getFirstClassRows() {
		return firstClassRows;
	}

	public void setFirstClassRows(int firstClassRows) {
		this.firstClassRows = firstClassRows;
	}

	public int getFirstClassColumns() {
		return firstClassColumns;
	}

	public void setFirstClassColumns(int firstClassColumns) {
		this.firstClassColumns = firstClassColumns;
	}

	public int getBusinessRows() {
		return businessRows;
	}

	public void setBusinessRows(int businessRows) {
		this.businessRows = businessRows;
	}

	public int getBusinessColumns() {
		return businessColumns;
	}

	public void setBusinessColumns(int businessColumns) {
		this.businessColumns = businessColumns;
	}

	public int getEconomicRows() {
		return economicRows;
	}

	public void setEconomicRows(int economicRows) {
		this.economicRows = economicRows;
	}

	public int getEconomicColumns() {
		return economicColumns;
	}

	public void setEconomicColumns(int economicColumns) {
		this.economicColumns = economicColumns;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(String additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getTransitions() {
		return transitions;
	}

	public void setTransitions(String transitions) {
		this.transitions = transitions;
	}

	public int getTransitionsNum() {
		return transitionsNum;
	}

	public void setTransitionsNum(int transitionsNum) {
		this.transitionsNum = transitionsNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

}
