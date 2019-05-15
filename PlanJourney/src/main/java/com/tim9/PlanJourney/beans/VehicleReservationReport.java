package com.tim9.PlanJourney.beans;

public class VehicleReservationReport {
	private String dateFrom;
	private String dateTo;
	private int type;
	public VehicleReservationReport() {
		super();
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

}
