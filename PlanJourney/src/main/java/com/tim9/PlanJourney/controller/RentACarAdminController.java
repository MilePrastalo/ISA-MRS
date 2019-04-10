package com.tim9.PlanJourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.service.RentACarAdminService;
import com.tim9.PlanJourney.service.RentACarCompanyService;

@RestController
public class RentACarAdminController {

	@Autowired
	private RentACarAdminService adminService;

	@Autowired
	private RentACarCompanyService companyService;

	@RequestMapping(value = "/api/getRentACarAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<RentACarAdmin> getRentACarAdmin(@PathVariable("username") String username) {

		RentACarAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addRentACarAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody ResponseEntity<RentACarAdmin> addRentACarAdmin(@RequestBody RentACarAdmin admin) {
		// Finds if new admin's given hotel exists.
		RentACarCompany r = companyService.findByName(admin.getService().getName());

		if (adminService.findByUsername(admin.getUsername()) == null && r != null) {
			RentACarAdmin a = (RentACarAdmin) adminService.save(admin);
			return new ResponseEntity<RentACarAdmin>(a, HttpStatus.OK);
		} else {
			return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/removeRentACarAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public ResponseEntity<RentACarAdmin> removeRentACarAdmin(@PathVariable("username") String username) {

		RentACarAdmin admin = adminService.findByUsername(username);
		// Checks if admin exists.
		if (admin == null) {
			return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.CONFLICT);
		}
		// If admin exists remove him from his hotel's admins list and remove him from
		// database.
		RentACarCompany company = companyService.findOne(admin.getService().getId());
		for (RentACarAdmin a : company.getAdmins()) {
			if (a.getId() == admin.getId()) {
				company.getAdmins().remove(a);
			}
		}
		adminService.remove(admin.getId());
		return new ResponseEntity<RentACarAdmin>(admin, HttpStatus.OK);
	}
}
