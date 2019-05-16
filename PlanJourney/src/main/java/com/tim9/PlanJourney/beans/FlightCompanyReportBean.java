package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class FlightCompanyReportBean {
	
	
	private ArrayList<String> labels;
	private ArrayList<Integer> data;
	
	public FlightCompanyReportBean() {
		// TODO Auto-generated constructor stub
	}
	
	public FlightCompanyReportBean(ArrayList<String> labels, ArrayList<Integer> data) {
		super();
		this.labels = labels;
		this.data = data;
	}


	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public ArrayList<Integer> getData() {
		return data;
	}

	public void setData(ArrayList<Integer> data) {
		this.data = data;
	}
	
	

}
