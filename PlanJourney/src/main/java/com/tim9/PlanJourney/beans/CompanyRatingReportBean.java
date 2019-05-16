package com.tim9.PlanJourney.beans;

import java.util.ArrayList;

public class CompanyRatingReportBean {
	private double avgRating;
	private ArrayList<Integer> ratings;
	public CompanyRatingReportBean() {
		super();
		ratings = new ArrayList<>();
		ratings.add(0);
		ratings.add(0);
		ratings.add(0);
		ratings.add(0);
		ratings.add(0);
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public ArrayList<Integer> getRatings() {
		return ratings;
	}
	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}
	
}
