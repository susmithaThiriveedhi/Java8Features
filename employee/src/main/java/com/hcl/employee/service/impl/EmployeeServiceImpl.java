package com.hcl.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.dto.HikeDetailsDto;
import com.hcl.employee.entity.Employee;
import com.hcl.employee.repository.EmployeeRepository;
import com.hcl.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public String saveEmployees(List<EmployeeDto> employeeDto) {
		String regex = "[7-9][0-9]{9}";
		List<EmployeeDto> employeeDtos=employeeDto.stream().filter(employees -> !(employees.getEmployeeName().isEmpty()) && 
				employees.getAge()>18 && (employees.getPhoneNumber()).matches(regex)).collect(Collectors.toList());
	
		employeeDtos.forEach(employees->{
			Employee employee=new Employee();
			BeanUtils.copyProperties(employees,employee);
			employeeRepository.save(employee);
		});
		
		employeeDto.forEach(emp->{
			if(!employeeDtos.contains(emp)) { 
			log.info(emp.toString());
			}
		});
		if(employeeDtos.size()>0) {
			return "Employees added Successfully";
		}
		return "Please enter the data in correct format";
	}
	
	@Override
	public List<String> getEmployees() {
		List<Employee> employees=employeeRepository.findAll();
		
		List<Employee> employeeList=employees.stream().filter(employee->employee.getSalary()>50000).collect(Collectors.toList());
		
		List<String> employeeNameList=new ArrayList<>();
		
		employeeList.forEach(employee-> {
			log.info(employee.getEmployeeName());
			employeeNameList.add(employee.getEmployeeName());
		});
		if(employeeNameList.size()>0) {
			return employeeNameList;
		}
		return null;
	}

	@Override
	public List<EmployeeResponseDto> getEmployeesBySalary() {
		
		List<Employee> employees=employeeRepository.findAll();
		
		List<Employee> employeeList=employees.stream().filter(employee->employee.getSalary()<20000).collect(Collectors.toList());
		
		List<EmployeeResponseDto> employeeResponseDtoList=new ArrayList<>();
		
		employeeList.forEach(employee-> {
			log.info(employee.getEmployeeName());
			EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto();
			BeanUtils.copyProperties(employee,employeeResponseDto);
			employeeResponseDtoList.add(employeeResponseDto);
		});
		return employeeResponseDtoList;
	}

	@Override
	@Transactional
	public List<HikeDetailsDto> updateEmployeesSalary() {
		List<Employee> employees=employeeRepository.findAll();
		
		List<Employee> employeeList=employees.stream().filter(employee->employee.getSalary()<20000).collect(Collectors.toList());
		
		List<HikeDetailsDto> hikeDetailsDtoList=new ArrayList<>();
		
		employeeList.forEach(employee-> {
			HikeDetailsDto hikeDetailsDto=new HikeDetailsDto();
			employee.setSalary(employee.getSalary()+10000);
			employeeRepository.saveAndFlush(employee);
			BeanUtils.copyProperties(employee,hikeDetailsDto);
			log.info(hikeDetailsDto.toString());
			hikeDetailsDtoList.add(hikeDetailsDto);
		});
		
		return hikeDetailsDtoList;
		
	}

}
