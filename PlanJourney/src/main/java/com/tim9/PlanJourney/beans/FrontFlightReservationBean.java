package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

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
	private double priceForSeat;
	private int flightDuration;
	private int flightLenght;
	private String created;
	private ArrayList<PassangerBean> passangersInfo;
	private ArrayList<HotelReservationBean> hotelsReservations;
	private ArrayList<VehicleReservationBean> rentReservations;
	private String creator;
	private float latitude;
	private float longitude;
	private int status;
	private int discount;
	
	public FrontFlightReservationBean() {
		super();
	}
	
	public FrontFlightReservationBean(Long id, String startDestination, String endDestination, String startDate,
			String endDate, double price, int passangers, int rating) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.passangers = passangers;
		this.rating = rating;
	}
	
	
	public FrontFlightReservationBean(Long id, String startDestination, String endDestination, String startDate,
			String endDate,  double price, int passangers) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.passangers = passangers;
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
	public int getFlightDuration() {
		return flightDuration;
	}
	public void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}
	public int getFlightLenght() {
		return flightLenght;
	}
	public void setFlightLenght(int flightLenght) {
		this.flightLenght = flightLenght;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public ArrayList<PassangerBean> getPassangersInfo() {
		return passangersInfo;
	}
	public void setPassangersInfo(ArrayList<PassangerBean> passangersInfo) {
		this.passangersInfo = passangersInfo;
	}
	public ArrayList<HotelReservationBean> getHotelsReservations() {
		return hotelsReservations;
	}
	public void setHotelsReservations(ArrayList<HotelReservationBean> hotelsReservations) {
		this.hotelsReservations = hotelsReservations;
	}
	public ArrayList<VehicleReservationBean> getRentReservations() {
		return rentReservations;
	}
	public void setRentReservations(ArrayList<VehicleReservationBean> rentReservations) {
		this.rentReservations = rentReservations;
	}

	public double getPriceForSeat() {
		return priceForSeat;
	}

	public void setPriceForSeat(double priceForSeat) {
		this.priceForSeat = priceForSeat;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
}
