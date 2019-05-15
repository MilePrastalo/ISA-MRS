package com.tim9.PlanJourney.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.CompanyRatingReportBean;
import com.tim9.PlanJourney.beans.VehicleReportReturn;
import com.tim9.PlanJourney.beans.VehicleReservationReport;
import com.tim9.PlanJourney.beans.VehicleSearchBean;
import com.tim9.PlanJourney.beans.VehicleSearchReturnBean;
import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.Vehicle;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.BranchOfficeService;
import com.tim9.PlanJourney.service.DestinationService;
import com.tim9.PlanJourney.service.QuickVehicleReservationService;
import com.tim9.PlanJourney.service.RegisteredUserService;
import com.tim9.PlanJourney.service.RentACarAdminService;
import com.tim9.PlanJourney.service.RentACarCompanyService;
import com.tim9.PlanJourney.service.VehicleReservationService;
import com.tim9.PlanJourney.service.VehicleService;
@RestController
public class RentACarReportController {
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
	private VehicleReservationService reservationService;
	
	@Autowired
	private RentACarCompanyService companyService;
	@Autowired
	private RegisteredUserService userService;
	
	@Autowired
	private QuickVehicleReservationService quickService;
	
	@RequestMapping(value = "/api/getCompanyRatingReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody CompanyRatingReportBean getCaompanyRating()
			throws Exception {
		RentACarAdmin admin = getAdmin();
		RentACarCompany company = companyService.findOne(admin.getService().getId());
		company.getReservations();
		CompanyRatingReportBean report = new CompanyRatingReportBean();
		ArrayList<VehicleReservation> reservations = new ArrayList<>();
		
		reservations.addAll(company.getReservations());
		for (VehicleReservation vehicleReservation : reservations) {
			for (Review review : vehicleReservation.getReservationReviews()) {
				report.getRatings().set(review.getRating()-1, report.getRatings().get(review.getRating()-1)+1);
			}
		}
		report.setAvgRating(company.getRating());
		return report;
	}
	@RequestMapping(value = "/api/getCompanyReservationsReport", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody VehicleReportReturn getReservationsReport(@RequestBody  VehicleReservationReport search)
			throws Exception {
		RentACarAdmin admin = getAdmin();
		RentACarCompany company = companyService.findOne(admin.getService().getId());
		company.getReservations();
		VehicleReportReturn report = new VehicleReportReturn();
		ArrayList<VehicleReservation> reservations = new ArrayList<>();
		
		reservations.addAll(company.getReservations());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(search.getDateFrom());
		Date endDate = sdf.parse(search.getDateTo());
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(startDate);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(endDate);
		
		//Create lists with zeros
		long span = endDate.getTime() - startDate.getTime();
		long cols;
		if (search.getType() == 0) {
			cols = TimeUnit.DAYS.convert(span, TimeUnit.MILLISECONDS);
			for(int i = 0; i<=cols;i++) {
				report.getDates().add(sdf.format(calStart.getTime()));
				report.getCounts().add(0);
				calStart.add(calStart.DATE, 1);
			}
		}
		else if (search.getType() == 1) {
			cols = TimeUnit.DAYS.convert(span, TimeUnit.MILLISECONDS);
			int weekStart = calStart.get(Calendar.WEEK_OF_YEAR);
			int weekEnd = calEnd.get(Calendar.WEEK_OF_YEAR);
			boolean ajusted = false;
			for(int i = 0; i<=weekEnd-weekStart;i++) {
				report.getDates().add(sdf.format(calStart.getTime()));
				report.getCounts().add(0);
				if(!ajusted) {
					calStart.add(calStart.DATE, 9-calStart.get(Calendar.DAY_OF_WEEK));
					ajusted = true;
				}
				else {
					calStart.add(calStart.DATE, 7);
				}
			}
		}
		else if (search.getType() == 2) {
			boolean ajusted = false;
			int monthStart = calStart.get(Calendar.MONTH);
			int montEnd = calEnd.get(Calendar.MONTH);

			for(int i = 0; i<=montEnd-monthStart;i++) {
				report.getDates().add(sdf.format(calStart.getTime()));
				report.getCounts().add(0);
				int sub = calStart.get(Calendar.DAY_OF_MONTH);
				if(!ajusted) {
					calStart.add(calStart.DATE,-sub+1);
					calStart.add(calStart.MONTH, 1);
					ajusted= true;
				}
				else {
					calStart.add(calStart.MONTH, 1);
				}
			}
		}
		for (VehicleReservation vehicleReservation : reservations) {
			for(int i = report.getCounts().size()-1; i>=0;i--) {
				Date d = sdf.parse(report.getDates().get(i));
				if(vehicleReservation.getDateFrom().after(d)) {
					report.getCounts().set(i, report.getCounts().get(i)+1);
					break;
				}
			}
		}
		return report;
	}
	private RentACarAdmin getAdmin() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RentACarAdmin admin = (RentACarAdmin) adminService.findByUsername(username);

		return admin;

	}
	
	@RequestMapping(value = "/api/getCompanyEarningsReport", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public @ResponseBody double getEarningsReport(@RequestBody  VehicleReservationReport search)
			throws Exception {
		RentACarAdmin admin = getAdmin();
		RentACarCompany company = companyService.findOne(admin.getService().getId());
		company.getReservations();
		VehicleReportReturn report = new VehicleReportReturn();
		ArrayList<VehicleReservation> reservations = new ArrayList<>();
		
		reservations.addAll(company.getReservations());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(search.getDateFrom());
		Date endDate = sdf.parse(search.getDateTo());
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(startDate);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(endDate);
		
		double earnings = 0.0;
		
		
		for (VehicleReservation vehicleReservation : reservations) {
			Date from = sdf.parse(search.getDateFrom());
			Date to = sdf.parse(search.getDateTo());
			if(vehicleReservation.getDateFrom().after(from)) {
				earnings+=vehicleReservation.getCena();
			}
			
		}
		
		return earnings;
	}
}
