package com.hcl.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private String employeeName;
	
	private int age;
	
	private double salary;
	
	private String designation;
	
	private String phoneNumber;
	
}
