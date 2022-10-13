package com.springboot.cruddemo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO {

	// define field from EntityManager
	private EntityManager entityManager;

	// setup constructor injection
	@Autowired
	public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployee() {

		// create a query
		Query query = entityManager.createQuery("from Employee");
		// execute query and get result list
		List<Employee> employees = query.getResultList();
		// return the results
		return employees;
	}

	@Override
	public Employee findByEmployeeId(int employeeId) {
		Employee theEmployee = entityManager.find(Employee.class, employeeId);
		return theEmployee;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		// update with id from db
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteByEmployeeId(int theId) {
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		query.executeUpdate();
	}

}
