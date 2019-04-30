package com.tim9.PlanJourney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.PlanJourney.models.FriendRequest;

public interface FriendRequestRepository  extends JpaRepository<FriendRequest, Long>{
	


}
