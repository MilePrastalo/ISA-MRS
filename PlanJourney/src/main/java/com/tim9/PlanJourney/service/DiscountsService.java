package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Discounts;
import com.tim9.PlanJourney.repository.DiscountsRepository;

@Service
public class DiscountsService {
	@Autowired
	DiscountsRepository repository;

	public Discounts findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<Discounts> findAll() {
		return repository.findAll();
	}

	public Page<Discounts> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Discounts save(Discounts discounts) {
		return repository.save(discounts);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
