package com.tim9.PlanJourney.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discounts {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "hotelDiscount")
	private int hotelDiscount;

	@Column(name = "numberOfHotelReservations")
	private int numberOfHotelReservations;

	@Column(name = "rentACarDiscount")
	private int rentACarDiscount;

	@Column(name = "numberOfRACReservations")
	private int numberOfRACReservations;

	@Column(name = "flightDiscount")
	private int flightDiscount;

	@Column(name = "numberOfFlightReservations")
	private int numberOfFlightReservations;

	public Discounts() {
	}
	
	
	public Discounts(int hotelDiscount, int numberOfHotelReservations, int rentACarDiscount,
			int numberOfRACReservations, int flightDiscount, int numberOfFlightReservations) {
		this.hotelDiscount = hotelDiscount;
		this.numberOfHotelReservations = numberOfHotelReservations;
		this.rentACarDiscount = rentACarDiscount;
		this.numberOfRACReservations = numberOfRACReservations;
		this.flightDiscount = flightDiscount;
		this.numberOfFlightReservations = numberOfFlightReservations;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHotelDiscount() {
		return hotelDiscount;
	}

	public void setHotelDiscount(int hotelDiscount) {
		this.hotelDiscount = hotelDiscount;
	}

	public int getNumberOfHotelReservations() {
		return numberOfHotelReservations;
	}

	public void setNumberOfHotelReservations(int numberOfHotelReservations) {
		this.numberOfHotelReservations = numberOfHotelReservations;
	}

	public int getRentACarDiscount() {
		return rentACarDiscount;
	}

	public void setRentACarDiscount(int rentACarDiscount) {
		this.rentACarDiscount = rentACarDiscount;
	}

	public int getNumberOfRACReservations() {
		return numberOfRACReservations;
	}

	public void setNumberOfRACReservations(int numberOfRACReservations) {
		this.numberOfRACReservations = numberOfRACReservations;
	}

	public int getFlightDiscount() {
		return flightDiscount;
	}

	public void setFlightDiscount(int flightDiscount) {
		this.flightDiscount = flightDiscount;
	}

	public int getNumberOfFlightReservations() {
		return numberOfFlightReservations;
	}

	public void setNumberOfFlightReservations(int numberOfFlightReservations) {
		this.numberOfFlightReservations = numberOfFlightReservations;
	}

}
