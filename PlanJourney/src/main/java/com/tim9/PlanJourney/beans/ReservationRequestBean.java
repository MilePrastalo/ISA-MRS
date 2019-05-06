package com.tim9.PlanJourney.beans;


public class ReservationRequestBean {
	
	private Long calledUserId;
	private Long flightId;
	private Long seatId;
	
	public ReservationRequestBean() {
		// TODO Auto-generated constructor stub
	}

	public ReservationRequestBean(Long calledUserId,  Long flightId, Long seatId) {
		super();
		this.calledUserId = calledUserId;
		this.flightId = flightId;
		this.seatId = seatId;
	}

	public Long getCalledUserId() {
		return calledUserId;
	}

	public void setCalledUserId(Long calledUserId) {
		this.calledUserId = calledUserId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}


}
