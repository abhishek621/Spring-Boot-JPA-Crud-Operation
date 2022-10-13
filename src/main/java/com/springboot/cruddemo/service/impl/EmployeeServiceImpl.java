package com.springboot.cruddemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// field injection
	//@Autowired
	private EmployeeDAO employeeDAO;

	// this will also work : constructor injection
	 @Autowired
	 public EmployeeServiceImpl(@Qualifier("employeeDaoJpaImpl") EmployeeDAO theEmployeeDAO) {
	 this.employeeDAO = theEmployeeDAO;
	 }

	@Override
	@Transactional
	public List<Employee> getAllEmployee() {
		return employeeDAO.findAllEmployee();
	}

	@Override
	@Transactional
	public Employee getByEmployeeId(int employeeId) {
		return employeeDAO.findByEmployeeId(employeeId);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		employeeDAO.saveEmployee(theEmployee);
	}

	@Override
	@Transactional
	public void deleteByEmployeeId(int theId) {
		employeeDAO.deleteByEmployeeId(theId);
	}

}
