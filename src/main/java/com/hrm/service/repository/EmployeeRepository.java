package com.hrm.service.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrm.entity.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/* private static final String SAVE_EMPLOYEE=""; */	
	private static final String SHOW_EMPLOYEE_BY_ID="from Employee e where e.id =: id ";
	private static final String  SHOW_EMPLOYEES="from Employee e order by e.salary asc";
	private static final String UPDATE_EMPLOYEE_BY_ID="update Employee e set e.empName=: empname, e.salary=: salary where e.id=:id";
	private static final String DELETE_EMPLOYEE_BY_ID="delete from Employee e where e.id=:id";
	
	
	public Session getSession() {
		Session session=sessionFactory.getCurrentSession();
		if(session==null) {
			session=sessionFactory.openSession();
		}
		return session;
	}
	
	
	
	/*
	 * public int saveEmployee(Employee emp) { Session session=getSession(); Query
	 * query=session.createQuery(SAVE_EMPLOYEE); return query.executeUpdate(); }
	 */
	
	
	public Employee getEmployeeById(int id) {
		Session session=getSession();
		Query query=session.createQuery(SHOW_EMPLOYEE_BY_ID);
		query.setParameter("id", id);
		
		List<Employee> employees=query.getResultList();
		if(employees.size()==1) {
			return employees.get(0);
		}
		return null;
		
	}
	
	
	public List<Employee> getEmployees() {
		Session session=getSession();
		Query query=session.createQuery(SHOW_EMPLOYEES);
		List<Employee> employees=query.getResultList();
		return employees;
		
	}
	
	
	public int deleteEmployee(int id) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery(DELETE_EMPLOYEE_BY_ID);
		query.setParameter("id",id);
		int index= query.executeUpdate();
		
		System.out.println("Index value "+index);
		if(index==1) {
			tx.commit();
			return index;
		}
		tx.rollback();
		return index;
	}
	
	public int updateEmployee(Employee emp) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery(UPDATE_EMPLOYEE_BY_ID);
		query.setParameter("empname", emp.getEmpName());
		query.setParameter("salary", emp.getSalary());
		query.setParameter("id",emp.getId());

		int index= query.executeUpdate();
		System.out.println("Index value "+index);
		if(index==1) {
			tx.commit();
			return index;
		}
		tx.rollback();
		return index;
	}
	
	public boolean isEmployeeExistHaingId(int id) {
		Employee emp=getEmployeeById(id);
		if(emp==null)
			return false;
		return true;
	}
	


}
