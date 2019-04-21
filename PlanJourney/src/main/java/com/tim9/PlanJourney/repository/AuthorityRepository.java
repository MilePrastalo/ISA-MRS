package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}
