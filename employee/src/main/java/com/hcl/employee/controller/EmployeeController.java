package com.hcl.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.dto.HikeDetailsDto;
import com.hcl.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping()
	public ResponseEntity<String> saveEmployees(@RequestBody List<EmployeeDto> employeeDto){
		log.info("Save collection of employees");
        return new ResponseEntity<String>(employeeService.saveEmployees(employeeDto),HttpStatus.CREATED);	
	}
	
	@GetMapping()
	public ResponseEntity<List<String>> getEmployees(){
		log.info("List of employeeNames whose salary is greater than 50000");
		return new ResponseEntity<List<String>>(employeeService.getEmployees(),HttpStatus.OK);	
	}
	
	@GetMapping("/salary")
	public ResponseEntity<?> getEmployeesBySalary(){
		log.info("List of employeeName and designation whose salary is less than 20000");
		List<EmployeeResponseDto> employeeResponseDto=employeeService.getEmployeesBySalary();
		if(employeeResponseDto.size()>0) {
			return new ResponseEntity<List<EmployeeResponseDto>>(employeeResponseDto,HttpStatus.OK);	
		}
		log.warn("There are no employees with salary<20000");
		return new ResponseEntity<String>("There are no employees with salary<20000",HttpStatus.CONFLICT);	
	}
	
	
	@PutMapping()
	public ResponseEntity<?> updateEmployeesSalary(){
		log.info("List of employeeName and salary who got hike of 10000");
		List<HikeDetailsDto> hikeDetailsDto=employeeService.updateEmployeesSalary();
		if(hikeDetailsDto.size()>0) {
			return new ResponseEntity<List<HikeDetailsDto>>(hikeDetailsDto,HttpStatus.OK);
		}
		log.warn("None of the employees got hike");
		return new ResponseEntity<String>("None of the employees got hike",HttpStatus.CONFLICT);		
	}
	
}
