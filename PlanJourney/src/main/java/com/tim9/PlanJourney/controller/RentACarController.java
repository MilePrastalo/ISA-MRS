package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.VehicleSearchBean;
import com.tim9.PlanJourney.models.Vehicle;

@RestController

public class RentACarController {

	@RequestMapping(
			value = "/api/vehicleSearch",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<Vehicle>  searchVehicles(@RequestBody VehicleSearchBean search) throws Exception {
		System.out.println("Pozvana metoda");
		System.out.println(search.getProducer());
		System.out.println(search.getNewer());
		System.out.println(search.getOlder());
		System.out.println(search.getPriceFrom());
		System.out.println(search.getPriceTo());
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		Vehicle v1 = new Vehicle("Model3", "Tesla","Sedan", 2015, 200);
		Vehicle v2 = new Vehicle("X3", "BMW","Hatchback", 2011, 400);
		Vehicle v3 = new Vehicle("A3", "Mercedes","Sedan", 2018, 100);
		Vehicle v4 = new Vehicle("C1", "Mercedes","Hatchback", 2008, 150);
		Vehicle v5 = new Vehicle("B1", "Mercedes","Sedan", 2009, 100);
		Vehicle v6 = new Vehicle("S", "Tesla","Sedan", 2018, 300);
		Vehicle v7 = new Vehicle("X", "Tesla","Sedan", 2018, 300);
		Vehicle v8 = new Vehicle("X2", "BMW","Hatchback", 2017, 200);
		Vehicle v9 = new Vehicle("X1", "BMW","Sedan", 2014, 100);
		Vehicle v10 = new Vehicle("A8", "Mercedes","Crossover", 2015, 200);
		vehicles.add(v1);
		vehicles.add(v2);
		vehicles.add(v3);
		vehicles.add(v4);
		vehicles.add(v5);
		vehicles.add(v6);
		vehicles.add(v7);
		vehicles.add(v8);
		vehicles.add(v9);
		vehicles.add(v10);
		ArrayList<Vehicle> foundVehicles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			
			if(vehicle.getMaker().equals(search.getProducer()) &&
					vehicle.getPrice() > search.getPriceFrom() &&
					vehicle.getPrice() < search.getPriceTo() &&
					vehicle.getYear() > search.getNewer() &&
					vehicle.getYear() < search.getOlder()) {
				foundVehicles.add(vehicle);
			}
			
		}
		
		return foundVehicles;
	}
	
	@RequestMapping(
			value = "/api/getProducers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<String> getProducers() throws Exception {
		ArrayList<String> producers = new ArrayList<>();
		producers.add("Tesla");
		producers.add("Mercedes");
		producers.add("Audi");
		producers.add("BMW");
		producers.add("Opel");
		producers.add("Fiat");
		return producers;
	}
	
	@RequestMapping(
			value = "/api/getTypes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<String> getTypes() throws Exception {
		ArrayList<String> types = new ArrayList<>();
		types.add("Sedan");
		types.add("Hatchback");
		types.add("Crossover");
		types.add("MPV");
		types.add("SUV");
		types.add("Coupe");
		return types;
	}
}
