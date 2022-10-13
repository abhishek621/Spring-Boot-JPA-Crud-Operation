package com.springboot.cruddemo.dao;

import java.util.List;

import com.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAllEmployee();
	
	public Employee findByEmployeeId(int employeeId);
	
	public void saveEmployee(Employee theEmployee);
	
	public void deleteByEmployeeId(int theId);
}
