package com.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeRestController {

	// field injection
	@Autowired
	private EmployeeService employeeService;

	// // this will also work : constructor injection
	@Autowired
//	public EmployeeRestController(EmployeeService employeeService) {
//		this.employeeService = employeeService;
//	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") int employeeId) {
		Employee theEmployeeId = employeeService.getByEmployeeId(employeeId);

		if (theEmployeeId == null) {
			throw new RuntimeException("Employee Id Not Found : " + employeeId);
		}
		return theEmployeeId;
	}

	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") int employeeId) {

		Employee tempEmployeeId = employeeService.getByEmployeeId(employeeId);
		if (tempEmployeeId == null) {
			throw new RuntimeException("Employee Id Not Found : " + employeeId);
		}
		employeeService.deleteByEmployeeId(employeeId);
		return "Employee Id got deleted : " + employeeId;
	}
}
