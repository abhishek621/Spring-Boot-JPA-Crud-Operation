package com.springboot.cruddemo.service;

import java.util.List;

import com.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

public List<Employee> getAllEmployee();
	
	public Employee getByEmployeeId(int employeeId);
	
	public void saveEmployee(Employee theEmployee);
	
	public void deleteByEmployeeId(int theId);
}
