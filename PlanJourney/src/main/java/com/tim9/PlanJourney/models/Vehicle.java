package com.tim9.PlanJourney.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "maker", nullable = false)
	private String maker;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "year", nullable = false)
	private int year;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	
	public Vehicle() {
		super();
	}

	

	public Vehicle(String name, String maker, String type, int year, double price) {
		super();
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.year = year;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMaker() {
		return maker;
	}


	public void setMaker(String maker) {
		this.maker = maker;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
