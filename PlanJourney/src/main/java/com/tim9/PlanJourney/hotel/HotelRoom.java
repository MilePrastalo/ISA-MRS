package com.tim9.PlanJourney.hotel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HotelRoom {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "roomNumber", unique = false, nullable = false)
	private int roomNumber;

	@Column(name = "numberOfBeds", unique = false, nullable = false)
	private int numberOfBeds;

	@Column(name = "pricePerDay", unique = false, nullable = false)
	private float pricePerDay;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdditionalCharges> additionalCharges = new HashSet<AdditionalCharges>();

	public HotelRoom() {
	}

	public HotelRoom(Long id, int roomNumber, int numberOfBeds, float pricePerDay,
			Set<AdditionalCharges> additionalCharges) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.numberOfBeds = numberOfBeds;
		this.pricePerDay = pricePerDay;
		this.additionalCharges = additionalCharges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
