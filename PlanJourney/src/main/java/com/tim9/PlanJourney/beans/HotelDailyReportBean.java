package com.tim9.PlanJourney.beans;

//Bean used for representing daily reservations
public class HotelDailyReportBean {
	private int tYear;
	private int tMonth;
	private int tDay;
	private int numberOfReservations;
	private float earnings;

	public HotelDailyReportBean() {
	}

	public HotelDailyReportBean(int tYear, int tMonth, int tDay, int numberOfReservations, float earnings) {
		this.tYear = tYear;
		this.tMonth = tMonth;
		this.tDay = tDay;
		this.numberOfReservations = numberOfReservations;
		this.earnings = earnings;
	}

	public int gettYear() {
		return tYear;
	}

	public void settYear(int tYear) {
		this.tYear = tYear;
	}

	public int gettMonth() {
		return tMonth;
	}

	public void settMonth(int tMonth) {
		this.tMonth = tMonth;
	}

	public int gettDay() {
		return tDay;
	}

	public void settDay(int tDay) {
		this.tDay = tDay;
	}

	public int getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public float getEarnings() {
		return earnings;
	}

	public void setEarnings(float earnings) {
		this.earnings = earnings;
	}

}
