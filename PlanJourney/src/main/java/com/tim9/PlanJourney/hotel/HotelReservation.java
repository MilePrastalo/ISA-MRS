package com.tim9.PlanJourney.hotel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Reservation;

@Entity
public class HotelReservation extends Reservation {

	@ManyToOne
	@JsonIgnoreProperties({ "admins", "reservations", "destinations" })
	private Hotel hotel;

	@OneToOne
	private HotelRoom room;

	@Column(name = "firstDay", unique = false, nullable = false)
	private Date firstDay;

	@Column(name = "lastDay", unique = false, nullable = false)
	private Date lastDay;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdditionalCharges> additionalCharges = new HashSet<AdditionalCharges>();

	@Column(name = "paidPrice", unique = false, nullable = false)
	private float paidPrice;

	@Column(name = "discount", unique = false, nullable = false)
	private int discount;

	public HotelReservation() {
	}

	public HotelReservation(Long id, Hotel hotel, HotelRoom room, Date firstDay, Date lastDay, RegisteredUser user,
			Set<AdditionalCharges> additionalCharges, float paidPrice, int discount) {

	}

	public HotelReservation(Long id, Hotel hotel, HotelRoom room, Date firstDay, Date lastDay,
			Set<AdditionalCharges> additionalCharges, float paidPrice, int discount) {
		setId(id);
		this.hotel = hotel;
		this.room = room;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.additionalCharges = additionalCharges;
		this.paidPrice = paidPrice;
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public HotelRoom getRoom() {
		return room;
	}

	public void setRoom(HotelRoom room) {
		this.room = room;
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

	public Set<AdditionalCharges> getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(Set<AdditionalCharges> additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public float getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(float paidPrice) {
		this.paidPrice = paidPrice;
	}

}
