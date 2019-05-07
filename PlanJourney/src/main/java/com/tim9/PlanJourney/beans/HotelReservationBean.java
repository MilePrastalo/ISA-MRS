package com.tim9.PlanJourney.beans;


public class HotelReservationBean {
	private String hotelName;
	private String username;
	private int roomNumber;
	private int fYear;
	private int fMonth;
	private int fDay;
	private int lYear;
	private int lMonth;
	private int lDay;

	public HotelReservationBean(String hotelName, String username, int roomNumber, int fYear, int fMonth, int fDay,
			int lYear, int lMonth, int lDay) {
		this.hotelName = hotelName;
		this.username = username;
		this.roomNumber = roomNumber;
		this.fYear = fYear;
		this.fMonth = fMonth;
		this.fDay = fDay;
		this.lYear = lYear;
		this.lMonth = lMonth;
		this.lDay = lDay;
	}

	public int getfYear() {
		return fYear;
	}

	public void setfYear(int fYear) {
		this.fYear = fYear;
	}

	public int getfMonth() {
		return fMonth;
	}

	public void setfMonth(int fMonth) {
		this.fMonth = fMonth;
	}

	public int getfDay() {
		return fDay;
	}

	public void setfDay(int fDay) {
		this.fDay = fDay;
	}

	public int getlYear() {
		return lYear;
	}

	public void setlYear(int lYear) {
		this.lYear = lYear;
	}

	public int getlMonth() {
		return lMonth;
	}

	public void setlMonth(int lMonth) {
		this.lMonth = lMonth;
	}

	public int getlDay() {
		return lDay;
	}

	public void setlDay(int lDay) {
		this.lDay = lDay;
	}

	public HotelReservationBean() {
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

}