package com.hcl.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int employeeId;
	
	private String employeeName; 
	
	private int age;
	
	private double salary;
	
	private String designation;
	
	private String phoneNumber;
	
	
}
