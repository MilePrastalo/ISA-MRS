package com.tim9.PlanJourney.beans;

public class HotelSearchBean {
	private String hotelName;
	private int numberOfBeds;
	private String cityName;
	private int fYear;
	private int fMonth;
	private int fDay;
	private int lYear;
	private int lMonth;
	private int lDay;
	private int minPrice;
	private int maxPrice;

	public HotelSearchBean(String hotelName, int numberOfBeds, String cityName, int fYear, int fMonth, int fDay,
			int lYear, int lMonth, int lDay, int minPrice, int maxPrice) {
		this.hotelName = hotelName;
		this.numberOfBeds = numberOfBeds;
		this.cityName = cityName;
		this.fYear = fYear;
		this.fMonth = fMonth;
		this.fDay = fDay;
		this.lYear = lYear;
		this.lMonth = lMonth;
		this.lDay = lDay;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public HotelSearchBean() {
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public int getlYear() {
		return lYear;
	}

	public void setlYear(int lYear) {
		this.lYear = lYear;
	}

	public int getlMonth() {
		return lMonth;
	}

	public void setlMonth(int lMonth) {
		this.lMonth = lMonth;
	}

	public int getlDay() {
		return lDay;
	}

	public void setlDay(int lDay) {
		this.lDay = lDay;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

}
