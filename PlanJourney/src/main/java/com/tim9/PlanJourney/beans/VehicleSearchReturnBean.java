package com.tim9.PlanJourney.beans;


public class VehicleSearchReturnBean {
	
	private String name;
	private String maker;
	private String type;
	private int year;
	private Double price;
	public VehicleSearchReturnBean(String name, String maker, String type, int year, Double price) {
		super();
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.year = year;
		this.price = price;
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
	
}