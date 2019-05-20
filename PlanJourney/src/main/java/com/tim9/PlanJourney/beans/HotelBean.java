package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class HotelBean {
	private String name;
	private String address;
	private String description;
	private ArrayList<HotelRoomBean> rooms;
	private ArrayList<HotelReservationBean> reservations;
	private double rating;
	private DestinationBean destination;
	private float longitude;
	private float latitude;

	public HotelBean(String name, String address, String description, ArrayList<HotelRoomBean> rooms,
			ArrayList<HotelReservationBean> reservations, double rating, DestinationBean destination, float longitude,
			float latitude) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.rooms = rooms;
		this.reservations = reservations;
		this.rating = rating;
		this.destination = destination;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public DestinationBean getDestination() {
		return destination;
	}

	public void setDestination(DestinationBean destination) {
		this.destination = destination;
	}

	public HotelBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<HotelRoomBean> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<HotelRoomBean> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<HotelReservationBean> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<HotelReservationBean> reservations) {
		this.reservations = reservations;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
}
