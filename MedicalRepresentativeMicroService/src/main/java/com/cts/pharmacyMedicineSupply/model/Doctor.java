package com.cts.pharmacyMedicineSupply.model;
//H2 DB ALWAYS GENERATE DATA IN ALPHABETICAL ORDER
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	@Id
	@GeneratedValue
	//Column
	private int id;
	//Column
	private String doctorname;
	//Column
	private String contactnumber;
	//Column
	private String treatingailment;
}
