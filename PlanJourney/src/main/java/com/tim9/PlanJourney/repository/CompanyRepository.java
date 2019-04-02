package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
