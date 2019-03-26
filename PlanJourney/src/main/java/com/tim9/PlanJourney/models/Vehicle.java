package com.tim9.PlanJourney.models;

public class Vehicle {
	private String name;
	private String maker;
	private int year;
	private double price;
	
	
	public Vehicle() {
		super();
	}


	public Vehicle(String name, String maker, int year, double price) {
		super();
		this.name = name;
		this.maker = maker;
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
}
