package com.tim9.PlanJourney.beans;

public class ReviewBean {
	private Long reservationId;
	private int	rating;
	
	public ReviewBean() {
		super();
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	

}
