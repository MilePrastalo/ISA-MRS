package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.hotel.HotelAdmin;

public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {
	
	HotelAdmin findByUsername(String username);
}
