package com.cts.pharmacyMedicineSupply.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.pharmacyMedicineSupply.Repository.DoctorRepo;
@ExtendWith(MockitoExtension.class)
class RepresentativeServiceImplTest {

	
	 @InjectMocks RepresentativeServiceImpl representativeServiceImpl;
	
	@Mock
	DoctorRepo doctorRepo;
	
	@Mock
	MedicalRepService medicalRepService;
	
	@Test
	public void TestStringtoDate() {
		LocalDate dt=LocalDate.parse("2021-07-19");
		assertEquals(dt,representativeServiceImpl.stringToDate("2021-07-19"));
	}
	
	@Test
	public void TestListOfDate() {
		LocalDate dt=LocalDate.parse("2021-07-19");
		String date[]= {"2021-07-19","2021-07-20","2021-07-21","2021-07-22","2021-07-23"};
		assertArrayEquals(date,representativeServiceImpl.getListOfDates(dt));
	}
}
