package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.AddVehicleBean;
import com.tim9.PlanJourney.beans.EditVehicleBean;
import com.tim9.PlanJourney.beans.RentACarCompanySearchBean;
import com.tim9.PlanJourney.beans.RentACarProfileBean;
import com.tim9.PlanJourney.beans.VehicleSearchBean;
import com.tim9.PlanJourney.beans.VehicleSearchReturnBean;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.flight.Destination;
import com.tim9.PlanJourney.models.rentacar.BranchOffice;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.BranchOfficeService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.RentACarAdminService;
import com.tim9.PlanJourney.service.RentACarCompanyService;
import com.tim9.PlanJourney.service.VehicleService;

@RestController

public class RentACarController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private RentACarAdminService adminService;
	@Autowired
	private DestinationService ds;
	@Autowired
	private BranchOfficeService bs;
	@Autowired
	private AuthorityService as;


	@Autowired
	private RentACarCompanyService companyService;

	@RequestMapping(value = "/api/vehicleSearch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	// Recieves parameters for search and returns list of found vehicles
	//Visible to everyone
	public @ResponseBody ArrayList<VehicleSearchReturnBean> searchVehicles(@RequestBody VehicleSearchBean search)
			throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles = (ArrayList<Vehicle>) vehicleService.findAll();
		ArrayList<VehicleSearchReturnBean> foundVehicles = new ArrayList<>();
		System.out.println("Poziv");
		// Needs optimization
		for (Vehicle vehicle : vehicles) {
			if ((vehicle.getMaker().equals(search.getProducer()) || search.getProducer().equals(""))
					&& (vehicle.getPrice() > search.getPriceFrom() || search.getPriceFrom() == 0)
					&& (vehicle.getPrice() < search.getPriceTo() || search.getPriceTo() == 0)
					&& (vehicle.getYear() > search.getNewer() || search.getNewer() == 0)
					&& (vehicle.getYear() < search.getOlder() || search.getOlder() == 0)
					&& (vehicle.getType().equals(search.getType()) || search.getType().equals(""))) {
				foundVehicles.add(new VehicleSearchReturnBean(vehicle.getName(), vehicle.getMaker(), vehicle.getType(),
						vehicle.getYear(), vehicle.getPrice()));
			}
		}
		return foundVehicles;
	}

	@RequestMapping(value = "/api/getProducers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("*")
	// Returns list of producers/makers
	// Probably will create table in database in the future - priority low
	//Visible to everyone
	public @ResponseBody ArrayList<String> getProducers() throws Exception {
		//System.out.println(jwt);
		ArrayList<String> producers = new ArrayList<>();
		producers.add("Tesla");
		producers.add("Mercedes");
		producers.add("Audi");
		producers.add("BMW");
		producers.add("Opel");
		producers.add("Fiat");
		return producers;
	}

	@RequestMapping(value = "/api/getTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	// Returns list of types of vehicles
	// Probably will create table in database in the future - priority low
	//Visible to everyone
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
	//Function used to get Rent a car admin from token
	private RentACarAdmin getAdmin() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RentACarAdmin admin = (RentACarAdmin) adminService.findByUsername(username);
		
		return admin;
		
	}
	@RequestMapping(value = "/api/getRentACarCompany", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('RENT_ADMIN')")
	//Renturns profile of admins rent a car company
	public @ResponseBody RentACarProfileBean getRentACarCompany() throws Exception {
		
		RentACarAdmin admin = getAdmin();
		if (admin == null) {
			return null;
		}
		RentACarCompany rentACarService = admin.getService();

		return new RentACarProfileBean(rentACarService.getName(), rentACarService.getAddress(),
				rentACarService.getDescription());

	}

	@RequestMapping(value = "/api/updateRentACarProfile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasRole('RENT_ADMIN')")
	//Updates profile of rent a car company and saves in database
	public void updateRentACarProfile(@RequestBody RentACarProfileBean profile) throws Exception {
		RentACarAdmin admin = getAdmin();
		RentACarCompany rentACarService = admin.getService();
		rentACarService.setName(profile.getName());
		rentACarService.setDescription(profile.getDescription());
		rentACarService.setAddress(profile.getAddress());
		companyService.save(rentACarService);
	}

	@RequestMapping(value = "/api/getRentACarCompanies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	// Returns list of types of vehicles
	// Probably will create table in database in the future - priority low
	public @ResponseBody ArrayList<RentACarCompanySearchBean> getCompanies(
			@RequestBody RentACarCompanySearchBean search) throws Exception {
		List<RentACarCompany> companies = companyService.findAll();
		
		ArrayList<RentACarCompany> foundCOmpanies = new ArrayList<>();
		for (RentACarCompany rentACarCompany : companies) {
			boolean containsLocation = false;
			if ((rentACarCompany.getName().equals(search.getName()) || search.getName().equals(""))) {
				if (!search.getLocation().equals("")) {
					for (BranchOffice des : rentACarCompany.getOffices()) {
						if (des.getDestination().getName().equals(search.getLocation())) {
							foundCOmpanies.add(rentACarCompany);
							break;
						}
					}
				} else {
					foundCOmpanies.add(rentACarCompany);
				}
			}
		}
		ArrayList<RentACarCompanySearchBean> returnBean = new ArrayList<>();
		for (RentACarCompany rentACarCompany : foundCOmpanies) {
			ArrayList<String> locs = new ArrayList<>();
			for (BranchOffice office : rentACarCompany.getOffices()) {
				locs.add(office.getDestination().getName());
			}
			returnBean.add(new RentACarCompanySearchBean(rentACarCompany.getName(), locs, rentACarCompany.getRating()));
		}
		return returnBean;
	}

	@RequestMapping(value = "/api/addRentACarCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<RentACarCompany> addRentACarCompany(
			@RequestBody RentACarCompany rentACarCompany) {
		if (companyService.findByAddress(rentACarCompany.getAddress()) == null
				&& companyService.findByName(rentACarCompany.getName()) == null) {
			RentACarCompany r = (RentACarCompany) companyService.save(rentACarCompany);
			return new ResponseEntity<RentACarCompany>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<RentACarCompany>(rentACarCompany, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeRentACarCompany/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<RentACarCompany> removeRentACarCompany(@PathVariable("name") String name) {

		RentACarCompany rentACarCompany = companyService.findByName(name);

		if (rentACarCompany == null) {
			return new ResponseEntity<RentACarCompany>(rentACarCompany, HttpStatus.CONFLICT);
		}

		companyService.remove(rentACarCompany.getId());
		return new ResponseEntity<RentACarCompany>(rentACarCompany, HttpStatus.OK);
	}
	
	
	///Gets logged in user and his company. Creates vehicle from bean and adds it to company
	///Saves company and vehicle in database
	@RequestMapping(value = "/api/addCar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasRole('RENT_ADMIN')")
	public void addCar(@RequestBody AddVehicleBean vehicleBean) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    RentACarAdmin admin =adminService.findByUsername(currentUserName);
		    RentACarCompany company = admin.getService();
		    Vehicle v = new Vehicle(vehicleBean.getName(), vehicleBean.getMaker(), vehicleBean.getType(), Integer.parseInt(vehicleBean.getYear()), Double.valueOf(vehicleBean.getPrice()));
		    company.getVehicles().add(v);
		    vehicleService.save(v);
		    companyService.save(company);
		}
	}
	@RequestMapping(value = "/api/editCar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasRole('RENT_ADMIN')")
	public void editCar(@RequestBody EditVehicleBean vehicleBean) throws Exception {
	    RentACarAdmin admin = getAdmin();
	    RentACarCompany company = admin.getService();
	    Vehicle v = vehicleService.findOne(vehicleBean.getId());
	    v.setName(vehicleBean.getName());
	    v.setMaker(vehicleBean.getMaker());
	    v.setType(vehicleBean.getType()); 
	    v.setYear(Integer.parseInt(vehicleBean.getYear()));
	    v.setPrice(Double.valueOf(vehicleBean.getPrice()));
	    vehicleService.save(v);		
	}
	@RequestMapping(value = "/api/removeCar/{id}", method = RequestMethod.DELETE)
	@CrossOrigin()
	@PreAuthorize("hasRole('RENT_ADMIN')")
	public void removeCar(@PathVariable Long id) throws Exception {
	    vehicleService.remove(id);
	}

	//TEST ONLY Writes some rent a car companies in database
	@RequestMapping(value = "/api/addRC", method = RequestMethod.GET)
	@CrossOrigin()
	public void addRC() throws Exception {
		RentACarCompany rc1 = new RentACarCompany("Company1", "Bulevar Pere Perica", "Best rent a car company", 5);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		RentACarAdmin admin1 = new RentACarAdmin("RentMarko",crypt.encode("markovic"),"Marko","Markovic","marko@marko.marko");
		Authority authority = (Authority) as.findOne(4l);
		ArrayList<Authority> authorities = new ArrayList<>();
		authorities.add(authority);
		admin1.setAuthorities(authorities);
		admin1.setService(rc1);
		adminService.save(admin1);
		rc1.setAdmins(new HashSet<>());
		rc1.getAdmins().add(admin1);
		rc1.setOffices(new HashSet<>());
		Destination d1 = new Destination("Belgrade", "Beograd", "44.51 , 51.21");
		ds.save(d1);
		BranchOffice office1 = new BranchOffice(null,"Beogradski rent1",rc1,d1);
		rc1.getOffices().add(office1);
		bs.save(office1);
		rc1.setReservations(new HashSet<>());
		rc1.setVehicles(new HashSet<>());
		companyService.save(rc1);
	}
	
	
}
