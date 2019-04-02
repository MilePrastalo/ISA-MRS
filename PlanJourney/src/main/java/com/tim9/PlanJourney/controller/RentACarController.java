package com.tim9.PlanJourney.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.RentACarProfileBean;
import com.tim9.PlanJourney.beans.VehicleSearchBean;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.service.VehicleService;

@RestController

public class RentACarController {
	
	private static RentACarCompany rentACarService;
	@Autowired
	private VehicleService vehicleService;
	@RequestMapping(
			value = "/api/vehicleSearch",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	//Recieves parameters for search and returns list of found vehicles
	public @ResponseBody ArrayList<Vehicle>  searchVehicles(@RequestBody VehicleSearchBean search) throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles = (ArrayList<Vehicle>)vehicleService.findAll();
		ArrayList<Vehicle> foundVehicles = new ArrayList<>();
		//Needs optimisation
		for (Vehicle vehicle : vehicles) {	
			if( (vehicle.getMaker().equals(search.getProducer()) ||search.getProducer().equals("")) &&
					(vehicle.getPrice() > search.getPriceFrom() ||  search.getPriceFrom() == 0) &&
					(vehicle.getPrice() < search.getPriceTo() || search.getPriceTo()==0  )&&
					(vehicle.getYear() > search.getNewer() ||  search.getNewer()==0 )&&
					(vehicle.getYear() < search.getOlder() ||  search.getOlder() == 0) && 
					(vehicle.getType().equals(search.getType()) ||search.getType().equals("")))		{
				foundVehicles.add(vehicle);
			}
		}
		
		return foundVehicles;
	}
	
	@RequestMapping(
			value = "/api/getProducers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	//Returns list of producers/makers
	//Probably will create table in database in the future - priority low
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
	@CrossOrigin()
	//Returns list of types of vehicles
	//Probably will create table in database in the future - priority low
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
	
	@RequestMapping(
			value = "/api/getRentACarCompany",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody RentACarCompany getRentACarCompany() throws Exception {
		if(rentACarService == null) {
			rentACarService = new RentACarCompany();
			rentACarService.setName("Super Car");
			rentACarService.setAddress("Belgrade Nemanjina 11");
			rentACarService.setDescription("Best rent-a-car service ever");
		}
		return rentACarService;
	}
	
	@RequestMapping(
			value = "/api/updateRentACarProfile",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public void  updateRentACarProfile(@RequestBody RentACarProfileBean profile) throws Exception {
		rentACarService.setName(profile.getName());
		rentACarService.setDescription(profile.getDescription());
		rentACarService.setAddress(profile.getAddress());
	}
}
