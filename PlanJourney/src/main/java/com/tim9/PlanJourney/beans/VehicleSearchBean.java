package com.tim9.PlanJourney.beans;

public class VehicleSearchBean {
	private String producer;
	private int newer;
	private int older;
	private int priceFrom;
	private int priceTo;
	private String type;
	public VehicleSearchBean() {
		super();
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public int getNewer() {
		return newer;
	}
	public void setNewer(int newer) {
		this.newer = newer;
	}
	public int getOlder() {
		return older;
	}
	public void setOlder(int older) {
		this.older = older;
	}
	public int getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}
	public int getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
