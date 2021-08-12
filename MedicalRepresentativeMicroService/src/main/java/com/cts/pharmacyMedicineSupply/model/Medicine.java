package com.cts.pharmacyMedicineSupply.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

//import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
	private String medicineName;
	private String[] chemicalComposition;
	private String targetAilment;
	//to be in LOCALDATE FORMAT
	private LocalDate dateOfExpiry;
	private int numberOfTabletsInStocks;
	
}