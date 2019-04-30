package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.RegisteredUser;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Long>  {
	public RegisteredUser findOneByUsername(String username);

}
