package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

// Bean used for week/Month/Year representation of reservations.
public class HotelReportBean {
	private String reportName;
	private ArrayList<HotelDailyReportBean> dailyReports;
	private int fYear;
	private int fMonth;
	private int fDay;
	private int numberOfReservations;
	private float totalEarnings;

	public HotelReportBean() {
	}

	public HotelReportBean(String reportName, ArrayList<HotelDailyReportBean> dailyReports, int fYear, int fMonth,
			int fDay, int numberOfReservations, float totalEarnings) {
		this.reportName = reportName;
		this.dailyReports = dailyReports;
		this.fYear = fYear;
		this.fMonth = fMonth;
		this.fDay = fDay;
		this.numberOfReservations = numberOfReservations;
		this.totalEarnings = totalEarnings;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public ArrayList<HotelDailyReportBean> getDailyReports() {
		return dailyReports;
	}

	public void setDailyReports(ArrayList<HotelDailyReportBean> dailyReports) {
		this.dailyReports = dailyReports;
	}

	public int getfYear() {
		return fYear;
	}

	public void setfYear(int fYear) {
		this.fYear = fYear;
	}

	public int getfMonth() {
		return fMonth;
	}

	public void setfMonth(int fMonth) {
		this.fMonth = fMonth;
	}

	public int getfDay() {
		return fDay;
	}

	public void setfDay(int fDay) {
		this.fDay = fDay;
	}

	public int getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public float getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(float totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

}
