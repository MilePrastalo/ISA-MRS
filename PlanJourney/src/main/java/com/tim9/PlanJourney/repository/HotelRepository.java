package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	Hotel findByAddress(String address);
	
	Hotel findByName(String name);
}
