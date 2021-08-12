package com.cts.pharmacyMedicineSupply.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pharmacyMedicineSupply.Repository.DoctorRepo;
//import com.cts.pharmacyMedicineSupply.Repository.DoctorRepo;
import com.cts.pharmacyMedicineSupply.dto.RepSchedule;
import com.cts.pharmacyMedicineSupply.feign.AuthClient;
import com.cts.pharmacyMedicineSupply.feign.StockFeignClient;
import com.cts.pharmacyMedicineSupply.model.AuthResponse;
import com.cts.pharmacyMedicineSupply.model.Doctor;
import com.cts.pharmacyMedicineSupply.model.MedicalRep;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class RepresentativeServiceImpl implements RepresentativeService {

	@Autowired
	AuthClient authClient;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	MedicalRepService medicalRepService;
	
	@Autowired
	StockFeignClient stockFeignClient;
	@Override
	public List<RepSchedule> listschedule(String dt) {
		LocalDate startDate=stringToDate(dt);
		log.info("Inside Service method for schedule");
		String[] date=getListOfDates(startDate);
		
		List<RepSchedule> schedule=new ArrayList<>();
		log.info("PRINTED LIST OF MedRep");
		System.out.println("PRINTED LIST OF MED REPRESENTATIVES");
		List<MedicalRep> listOfMedRep=medicalRepService.sendRep();
		for(int i=0;i<listOfMedRep.size();i++) {
			System.out.println(listOfMedRep.get(i));
		}
		int c=0;
		int rep=0;
		List<String> listofmed=new ArrayList<>();
		for(int j=1;j<=doctorRepo.count();j++) {
			listofmed.clear();
			Doctor d=doctorRepo.getOne(j);
			String docName=d.getDoctorname();
			log.info("DOCTOR'S NAME:"+docName);
			String slot="1 PM to 2 PM";
			log.info("DOCTOR'S TIME SLOT:"+slot);
			String contactNumber=d.getContactnumber();
			log.info("DOCTOR'S CONTACT NUMBER:"+contactNumber);
			String targetAilment=d.getTreatingailment();
			log.info("DOCTOR'S TARGETING AILMENT:"+targetAilment);
			listofmed=stockFeignClient.getMedicineByTreatingAilment(targetAilment);
			String med = String.join(",", listofmed);
			System.out.println(med);
			schedule.add(new RepSchedule(listOfMedRep.get(rep++).getMedicalRepName(),docName,targetAilment,med,slot,date[c++],contactNumber));
			if(rep==3)rep=0;
		}		
		return schedule;
	}
	
	public String[] getListOfDates(LocalDate startDate) {
		String[] date=new String[5];
		for(int i=0;i<5;i++) {
			if(!startDate.getDayOfWeek().toString().equals("SUNDAY")) {
				date[i]=startDate.toString();
			}
			else {
				startDate=startDate.plusDays(1);
				date[i]=startDate.toString();
			}
			startDate=startDate.plusDays(1);
		}
		return date;
	}

	/*
	 * This methods takes String date as Input 
	 * And return Date in the form of LocalDate
	 */
	public LocalDate stringToDate(String dt) {
		LocalDate localDate = LocalDate.parse(dt);
		return localDate;
	}
	
	public boolean isSessionValid(String token) {
		try {
			AuthResponse authResponse = authClient.getValidity(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	

}
