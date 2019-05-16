package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class VehicleReportReturn {
	private ArrayList<String> dates;
	private ArrayList<Integer> counts;
	public VehicleReportReturn() {
		super();
		dates = new ArrayList<>();
		counts = new ArrayList<>();
	}
	public ArrayList<String> getDates() {
		return dates;
	}
	public void setDates(ArrayList<String> dates) {
		this.dates = dates;
	}
	public ArrayList<Integer> getCounts() {
		return counts;
	}
	public void setCounts(ArrayList<Integer> counts) {
		this.counts = counts;
	}
	
}
