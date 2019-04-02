package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	VehicleRepository repository;
	
	public Vehicle findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Vehicle> findAll() {
		return repository.findAll();
	}
	
	public Page<Vehicle> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Vehicle save(Vehicle vehicle) {
		return repository.save(vehicle);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
