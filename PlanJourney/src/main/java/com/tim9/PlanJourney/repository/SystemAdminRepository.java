package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.SystemAdmin;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {

	SystemAdmin findByUsername(String username);
}
