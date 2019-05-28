package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.repository.VehicleRepository;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false)
	public Vehicle save(Vehicle vehicle) {
		return repository.save(vehicle);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
}
