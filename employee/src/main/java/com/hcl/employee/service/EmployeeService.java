package com.hcl.employee.service;

import java.util.List;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.dto.HikeDetailsDto;

public interface EmployeeService {

	String saveEmployees(List<EmployeeDto> employeeDto);

	List<String> getEmployees();

	List<EmployeeResponseDto> getEmployeesBySalary();

	List<HikeDetailsDto> updateEmployeesSalary();

}
