package com.tim9.PlanJourney.beans;

import java.util.Date;

public class HotelReservationBean {
	private String hotelName;
	private String username;
	private int roomNumber;
	private Date firstDay;
	private Date lastDay;

	public HotelReservationBean(String hotelName, String username, int roomNumber, Date firstDay, Date lastDay) {
		this.hotelName = hotelName;
		this.username = username;
		this.roomNumber = roomNumber;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
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

	public Date getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

}
