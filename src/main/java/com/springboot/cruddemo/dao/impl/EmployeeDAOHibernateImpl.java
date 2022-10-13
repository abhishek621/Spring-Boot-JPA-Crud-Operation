package com.springboot.cruddemo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field from EntityManager
	private EntityManager entityManager;

	// setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAllEmployee() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		// execute query and get result list
		List<Employee> employees = query.getResultList();
		// return the queryresult
		return employees;
	}

	@Override
	public Employee findByEmployeeId(int employeeId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, employeeId);
		// return the employee
		return theEmployee;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// save the employee
		currentSession.saveOrUpdate(theEmployee);

	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteByEmployeeId(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// delete employee with primary key
		Query<Employee> query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		query.executeUpdate();
	}

}
