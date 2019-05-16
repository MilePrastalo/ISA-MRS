package com.tim9.PlanJourney.beans;


public class ReservationRequestBean {
	
	private Long calledUserId;
	private Long requestId;
	private Long seatId;
	private String username;
	private String password;
	
	public ReservationRequestBean() {
		// TODO Auto-generated constructor stub
	}

	public ReservationRequestBean(Long requestId,  String username, String password) {
		super();
		this.requestId = requestId;
		this.password = password;
		this.username = username;
	}

	public Long getCalledUserId() {
		return calledUserId;
	}

	public void setCalledUserId(Long calledUserId) {
		this.calledUserId = calledUserId;
	}


	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
