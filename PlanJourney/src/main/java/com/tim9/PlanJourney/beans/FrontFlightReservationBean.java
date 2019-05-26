package com.tim9.PlanJourney.beans;

public class FrontFlightReservationBean {
	private Long id;
	private String startDestination;
	private String endDestination;
	private String startDate;
	private String endDate;
	private String travelClassa;
	private double price;
	private int passangers;
	private  int rating;
	private int seatRow;
	private int seatColumn;
	
	
	public FrontFlightReservationBean(Long id, String startDestination, String endDestination, String startDate,
			String endDate, String travelClassa, double price, int passangers, int rating, int seatRow,
			int seatColumn) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.travelClassa = travelClassa;
		this.price = price;
		this.passangers = passangers;
		this.rating = rating;
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
	}
	public FrontFlightReservationBean() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTravelClassa() {
		return travelClassa;
	}
	public void setTravelClassa(String travelClassa) {
		this.travelClassa = travelClassa;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPassangers() {
		return passangers;
	}
	public void setPassangers(int passangers) {
		this.passangers = passangers;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public int getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
	
	
	
}
