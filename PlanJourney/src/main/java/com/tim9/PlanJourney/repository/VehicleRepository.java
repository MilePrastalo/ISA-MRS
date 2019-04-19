package com.tim9.PlanJourney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.PlanJourney.models.rentacar.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Long>  {

	@Query("select v from Vehicle v, RentACarCompany rc JOIN rc.vehicles rcv where rcv.id = v.id and rc.id = ?1")
	List<Vehicle> findByServiceId(Long id);
}
