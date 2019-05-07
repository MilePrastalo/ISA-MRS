package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.hotel.HotelReservation;

public interface HotelReservationRepository  extends JpaRepository<HotelReservation, Long>{

}
