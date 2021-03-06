package com.tim9.PlanJourney.beans;

public class VehicleReservationBean {
	private Long id;
	private Long vehicleId;
	private String vehicleName;
	private String locationPick;
	private String locationReturn;
	private String dateFrom;
	private String dateTo;
	private double price;
	private int status;
	private int rating;
	private int discount;
	private String responseStatus;
	
	public VehicleReservationBean() {
		super();
	}
	 
	public VehicleReservationBean(String vehicleName, String locationPick,
			String locationReturn, String dateFrom, String dateTo, double price) {
		super();
		this.vehicleName = vehicleName;
		this.locationPick = locationPick;
		this.locationReturn = locationReturn;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getLocationPick() {
		return locationPick;
	}
	public void setLocationPick(String locationPick) {
		this.locationPick = locationPick;
	}
	public String getLocationReturn() {
		return locationReturn;
	}
	public void setLocationReturn(String locationReturn) {
		this.locationReturn = locationReturn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
	
	
	
	
	

}
