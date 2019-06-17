package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.City;
import com.tim9.PlanJourney.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository repository;

	public City findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public City findByName(String name) {
		return repository.findByName(name);
	}

	public List<City> findAll() {
		return repository.findAll();
	}

	public Page<City> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public City save(City city) {
		return repository.save(city);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
