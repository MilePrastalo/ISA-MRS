package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.rentacar.RentACarCompany;

public interface RentACarCompanyRepository extends JpaRepository<RentACarCompany, Long> {

	RentACarCompany findByAddress(String address);

	RentACarCompany findByName(String name);
}
