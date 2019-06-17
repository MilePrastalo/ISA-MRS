package com.tim9.PlanJourney.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private RegisteredUser user;

	@ManyToOne
	private Reservation reservation;

	@Column(name = "rating")
	private int rating;

	public Review() {
		super();
	}

	public Review(RegisteredUser user, Reservation reservation, int rating) {
		super();
		this.user = user;
		this.reservation = reservation;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
