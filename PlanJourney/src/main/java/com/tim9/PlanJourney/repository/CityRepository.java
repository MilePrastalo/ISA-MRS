package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.City;

public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String name);
}
