package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
