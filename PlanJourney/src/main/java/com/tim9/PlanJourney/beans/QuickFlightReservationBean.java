package com.tim9.PlanJourney.beans;

public class QuickFlightReservationBean {
	
	
	private Long flightId;
	private Long seatId;
	private double originPrice;
	private int discount;
	
	public QuickFlightReservationBean() {
		// TODO Auto-generated constructor stub
	}

	public QuickFlightReservationBean(Long flightId, Long seatId, double originPrice, int discount) {
		super();
		this.flightId = flightId;
		this.seatId = seatId;
		this.originPrice = originPrice;
		this.discount = discount;
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

	public double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	

}
