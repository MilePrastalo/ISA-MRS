package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;

public interface RentACarAdminRepository extends JpaRepository<RentACarAdmin, Long> {

	RentACarAdmin findByUsername(String username);

}
