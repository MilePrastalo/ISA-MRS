package com.tim9.PlanJourney.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "additionalCharges")
public class AdditionalCharges {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "pricePerDay", unique = false, nullable = false)
	private int pricePerDay;

	public AdditionalCharges() {
	}

	public AdditionalCharges(Long id, String name, int pricePerDay) {
		this.id = id;
		this.name = name;
		this.pricePerDay = pricePerDay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

}
