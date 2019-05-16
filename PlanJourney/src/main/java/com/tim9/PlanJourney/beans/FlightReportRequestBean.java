package com.tim9.PlanJourney.beans;

import java.util.Date;

public class FlightReportRequestBean {
	
	
	private Date dateFrom;
	private Date dateTo;
	private String kind;
	
	public FlightReportRequestBean() {
		// TODO Auto-generated constructor stub
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	

}
