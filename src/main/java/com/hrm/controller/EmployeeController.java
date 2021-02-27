package com.hrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.custom.exception.EmployeeNotFoundException;
import com.hrm.entity.Employee;
import com.hrm.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/hrm")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl service;
	
	@GetMapping(value = "/get-employee-by-id/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee=service.showtEmployeeById(id);
		if(employee!=null)
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/get-employees",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Employee>> getEmployeeById() {
		List<Employee> employees=service.showEmployees();
		if(employees.size()>0)
		return new ResponseEntity(employees, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PostMapping(value = "/update-employee-by_id", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> updateEmpById(@RequestBody Employee emp){		
		if(service.IsExistEmp(emp.getId())) {
			service.updateEmployeeById(emp);
			return new ResponseEntity<Employee>(service.showtEmployeeById(emp.getId()), HttpStatus.OK);
		}else 
		throw new EmployeeNotFoundException("Employee havind id "+emp.getId()+" doesn't found...");
		
	}

	
	@PostMapping(value = "/delete-employee-by_id/{id}")
	public ResponseEntity<String>  deleteEmpById(@PathVariable int id){ 
		String DELETE_MSG="";
		if(service.IsExistEmp(id)) {
			int index=service.removeEmployeeById(id);
			if(index==1)
			{  DELETE_MSG="Employee having id "+id+" has been succesfully deleted..";
				return new ResponseEntity<String>(DELETE_MSG,HttpStatus.OK);
			}
		}
		else {
			throw new EmployeeNotFoundException("Employee can't delete because because doesn't exist havind id "+id);
		}
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
