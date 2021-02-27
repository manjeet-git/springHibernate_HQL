package com.hrm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.entity.Employee;
import com.hrm.service.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeRepository repository;
	
	
	public Employee showtEmployeeById(int id) {
		return repository.getEmployeeById(id);
		
	}
	
	
	
	public List<Employee> showEmployees(){
		List<Employee> employees=repository.getEmployees();
		return employees;
	}
	
	
	public int removeEmployeeById(int id) {
		return repository.deleteEmployee(id);
	}
	

	public int updateEmployeeById(Employee emp) {
		return repository.updateEmployee(emp);
	}
	
	public boolean IsExistEmp(int id) {
		return repository.isEmployeeExistHaingId(id);
	}
	
}
