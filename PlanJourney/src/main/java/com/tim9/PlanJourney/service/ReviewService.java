package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.repository.ReviewRepository;
@Service
public class ReviewService {
	@Autowired
	private ReviewRepository repository;

	public Review findOne(Long id) {
		return repository.getOne(id);
	}

	public List<Review> findAll() {
		return repository.findAll();
	}

	public Review save(Review review) {
		return repository.save(review);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
