package com.tim9.PlanJourney.hotel;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.RegisteredUser;

@Entity
public class HotelReservation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Hotel hotel;

	@OneToOne
	private HotelRoom room;

	@Column(name = "firstDay", unique = false, nullable = false)
	private Date firstDay;

	@Column(name = "lastDay", unique = false, nullable = false)
	private Date lastDay;
	@ManyToOne
	private RegisteredUser user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdditionalCharges> additionalCharges = new HashSet<AdditionalCharges>();

	@Column(name = "paidPrice", unique = false, nullable = false)
	private float paidPrice;

	public HotelReservation() {
	}

	public HotelReservation(Long id, Hotel hotel, HotelRoom room, Date firstDay, Date lastDay,
			Set<AdditionalCharges> additionalCharges, float paidPrice) {
		this.id = id;
		this.hotel = hotel;
		this.room = room;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.additionalCharges = additionalCharges;
		this.paidPrice = paidPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}
	

}
