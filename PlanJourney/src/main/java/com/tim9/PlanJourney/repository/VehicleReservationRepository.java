package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.rentacar.VehicleReservation;

public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, Long>{

}
