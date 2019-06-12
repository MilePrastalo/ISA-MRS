package com.tim9.PlanJourney.beans;

public class PassangerBean {
	
	
	private Long seatId;
	private String firstName;
	private String lastName;
	private String passport;
	private double price;
	private Long friendId;
	private int seatRow;
	private int seatColumn;
	private String travelClass;
	private String status;
	
	public PassangerBean() {
		// TODO Auto-generated constructor stub
	}

	

	public PassangerBean(Long seatId, String firstName, String lastName, String passport, double price, Long friendId,
			int seatRow, int seatColumn, String travelClass) {
		super();
		this.seatId = seatId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.price = price;
		this.friendId = friendId;
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.travelClass = travelClass;
	}
	
	public PassangerBean(String firstName, String lastName, String passport, double price,
			int seatRow, int seatColumn, String travelClass) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.price = price;
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.travelClass = travelClass;
		
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
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



	public String getTravelClass() {
		return travelClass;
	}



	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
