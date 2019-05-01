package com.tim9.PlanJourney.beans;

public class VehicleReservationSearchBean {
	private String id; //ID of company
	private String dateFrom;
	private String dateTo;
	private String officePick;
	private String officeReturn;
	public VehicleReservationSearchBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getOfficePick() {
		return officePick;
	}
	public void setOfficePick(String officePick) {
		this.officePick = officePick;
	}
	public String getOfficeReturn() {
		return officeReturn;
	}
	public void setOfficeReturn(String officeReturn) {
		this.officeReturn = officeReturn;
	}
	
	
	
	
	

}
