package com.tim9.PlanJourney.beans;


public class VehicleSearchReturnBean {
	
	private String id;
	private String name;
	private String maker;
	private String type;
	private int year;
	private Double price;
	private double rating;
	private int seats;
	public VehicleSearchReturnBean(String name, String maker, String type, int year, Double price,double rating) {
		super();
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.year = year;
		this.price = price;
		this.rating = rating;
	}
	public VehicleSearchReturnBean(String id,String name, String maker, String type, int year, Double price,double rating) {
		super();
		this.id = id;
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.year = year;
		this.price = price;
		this.rating = rating;
	}
	public VehicleSearchReturnBean() {
		super();
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
	
}
