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

import com.tim9.PlanJourney.beans.RentACarCompanySearchBean;
import com.tim9.PlanJourney.beans.RentACarProfileBean;
import com.tim9.PlanJourney.beans.VehicleSearchBean;
import com.tim9.PlanJourney.beans.VehicleSearchReturnBean;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.rentacar.BranchOffice;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.service.RentACarAdminService;
import com.tim9.PlanJourney.service.RentACarCompanyService;
import com.tim9.PlanJourney.service.VehicleService;

@RestController

public class RentACarController {
	
	
	private static RentACarAdmin admin;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RentACarAdminService adminService;
	
	@Autowired
	private RentACarCompanyService companyService;
	
	
	@RequestMapping(
			value = "/api/vehicleSearch",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	//Recieves parameters for search and returns list of found vehicles
	public @ResponseBody ArrayList<VehicleSearchReturnBean>  searchVehicles(@RequestBody VehicleSearchBean search) throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles = (ArrayList<Vehicle>)vehicleService.findAll();
		ArrayList<VehicleSearchReturnBean> foundVehicles = new ArrayList<>();
		System.out.println("Poziv");
		//Needs optimisation
		for (Vehicle vehicle : vehicles) {	
			if( (vehicle.getMaker().equals(search.getProducer()) ||search.getProducer().equals("")) &&
					(vehicle.getPrice() > search.getPriceFrom() ||  search.getPriceFrom() == 0) &&
					(vehicle.getPrice() < search.getPriceTo() || search.getPriceTo()==0  )&&
					(vehicle.getYear() > search.getNewer() ||  search.getNewer()==0 )&&
					(vehicle.getYear() < search.getOlder() ||  search.getOlder() == 0) && 
					(vehicle.getType().equals(search.getType()) ||search.getType().equals("")))		{
				foundVehicles.add(new VehicleSearchReturnBean(vehicle.getName(), vehicle.getMaker(), vehicle.getType(), vehicle.getYear(), vehicle.getPrice()));
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
	public @ResponseBody RentACarProfileBean getRentACarCompany() throws Exception {
		if (admin == null) {
			admin = adminService.findOne(1l);
		}
		RentACarCompany rentACarService = admin.getService();
		if(rentACarService == null) {
			rentACarService = new RentACarCompany();
			rentACarService.setName("Super Car");
			rentACarService.setAddress("Belgrade Nemanjina 11");
			rentACarService.setDescription("Best rent-a-car service ever");
		}
		return new RentACarProfileBean(rentACarService.getName(), rentACarService.getAddress(), rentACarService.getDescription());
	
	}
	
	@RequestMapping(
			value = "/api/updateRentACarProfile",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public void  updateRentACarProfile(@RequestBody RentACarProfileBean profile) throws Exception {
		if (admin == null) {
			admin = adminService.findOne(1l);
		}
		RentACarCompany rentACarService = admin.getService();
		rentACarService.setName(profile.getName());
		rentACarService.setDescription(profile.getDescription());
		rentACarService.setAddress(profile.getAddress());
		companyService.save(rentACarService);
	}
	
	@RequestMapping(
			value = "/api/getRentACarCompanies",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	//Returns list of types of vehicles
	//Probably will create table in database in the future - priority low
	public @ResponseBody ArrayList<RentACarCompanySearchBean> getCompanies(@RequestBody RentACarCompanySearchBean search) throws Exception {
		ArrayList<RentACarCompanySearchBean> companies = new ArrayList<>();
		Destination d1 = new Destination("Novi Sad", "Super grad", "021");
		Destination d2 = new Destination("Beograd", "ok", "1111");
		Destination d3 = new Destination("Nis", "ok", "521");

		BranchOffice b1 = new BranchOffice();
		b1.setName("first office");
		b1.setDestination(d1);
		BranchOffice b2 = new BranchOffice();
		b2.setName("sec office");
		b2.setDestination(d2);
		BranchOffice b3 = new BranchOffice();
		b3.setName("thi office");
		b3.setDestination(d2);
		BranchOffice b4 = new BranchOffice();
		b4.setName("fo office");
		b4.setDestination(d3);
		BranchOffice b5 = new BranchOffice();
		b5.setName("fi office");
		b5.setDestination(d1);
		BranchOffice b6 = new BranchOffice();
		b6.setName("si office");
		b6.setDestination(d3);
		
		RentACarCompany rc1 = new RentACarCompany("First Company", "adr", "Cool");
		rc1.getOffices().add(b1);
		rc1.getOffices().add(b2);
		rc1.setRating(4);
		RentACarCompany rc2 = new RentACarCompany("Second Company", "adr", "Cool");
		rc2.getOffices().add(b3);
		rc2.getOffices().add(b4);
		rc1.setRating(4.5);
		RentACarCompany rc3 = new RentACarCompany("Third Company", "adr", "Cool");
		rc3.getOffices().add(b5);
		rc3.getOffices().add(b6);
		rc3.setRating(5);
		ArrayList<RentACarCompany> companiesAll = new ArrayList<>();
		companiesAll.add(rc1);
		companiesAll.add(rc2);
		companiesAll.add(rc3);
		ArrayList<RentACarCompany> foundCOmpanies = new ArrayList<>();
		for (RentACarCompany rentACarCompany : companiesAll) {
			boolean containsLocation = false;
			if( (rentACarCompany.getName().equals(search.getName()) ||search.getName().equals("")) ) {
				if (!search.getLocation().equals("")) {
					for (BranchOffice des : rentACarCompany.getOffices()) {
						if(des.getDestination().getName().equals(search.getLocation())) {
							foundCOmpanies.add(rentACarCompany);
							break;
						}
					}
				}
				else
				{
					foundCOmpanies.add(rentACarCompany);
				}
			} 
		}
		
		for (RentACarCompany rentACarCompany : foundCOmpanies) {
			ArrayList<String> locs = new ArrayList<>();
			for (BranchOffice office : rentACarCompany.getOffices()) {
				locs.add(office.getDestination().getName());
			}
			companies.add(new RentACarCompanySearchBean(rentACarCompany.getName(), locs, rentACarCompany.getRating()));
		}
		return companies;
	}
}
