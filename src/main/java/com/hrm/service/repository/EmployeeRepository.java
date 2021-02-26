package com.hrm.service.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrm.entity.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String SAVE_EMPLOYEE="";	
	private static final String SHOW_EMPLOYEE_BY_ID="from Employee e where e.id =: id";
	private static final String  SHOW_EMPLOYEES="from Employee";
	private static final String UPDATE_EMPLOYEE_BY_ID="update Employee e set e.empName=?,e.salary=? where e.id=?";
	private static final String DELETE_EMPLOYEE_BY_ID="delete from Employee e where e.id=?";
	
	
	public Session getSession() {
		Session session=sessionFactory.getCurrentSession();
		if(session==null) {
			session=sessionFactory.openSession();
		}
		return session;
	}
	
	
	
	public int saveEmployee(Employee emp) {
		Session session=getSession();
		Query query=session.createQuery(SAVE_EMPLOYEE);
		return query.executeUpdate();
	}
	
	
	public Employee getEmployeeById(int id) {
		Session session=getSession();
		Query query=session.createQuery(SHOW_EMPLOYEE_BY_ID);
		query.setParameter("id", id);
		
		List<Employee> employees=query.getResultList();
		if(employees.size()==1) {
			return employees.get(0);
		}
		return new Employee();
		
	}
	
	
	public List<Employee> getEmployees() {
		Session session=getSession();
		Query query=session.createQuery(SHOW_EMPLOYEES);
		List<Employee> employees=query.getResultList();
		return employees;
		
	}
	
	
	public int deleteEmployee(int id) {
		Session session=getSession();
		Query query=session.createQuery(DELETE_EMPLOYEE_BY_ID);
		query.setParameter(1,id);
		return query.executeUpdate();
	}
	


}
