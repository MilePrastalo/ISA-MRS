package com.tim9.PlanJourney.beans;

import java.util.HashSet;
import java.util.Set;

import com.tim9.PlanJourney.hotel.AdditionalCharges;

public class HotelRoomBean {
	private int roomNumber;
	private int numberOfBeds;
	private float pricePerDay;
	private Set<AdditionalCharges> additionalCharges = new HashSet<AdditionalCharges>();

	public HotelRoomBean(int roomNumber, int numberOfBeds, float pricePerDay,
			Set<AdditionalCharges> additionalCharges) {
		this.roomNumber = roomNumber;
		this.numberOfBeds = numberOfBeds;
		this.pricePerDay = pricePerDay;
		this.additionalCharges = additionalCharges;
	}

	public HotelRoomBean() {
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public float getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(float pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Set<AdditionalCharges> getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(Set<AdditionalCharges> additionalCharges) {
		this.additionalCharges = additionalCharges;
	}
}
