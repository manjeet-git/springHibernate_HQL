package com.hrm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hrm.entity.Email;


@Entity
@Table(name="employee")
public class Employee implements Serializable{

	
	private static final long serialVersionUID = 5644917307143307377L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int id;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="salary")
	private double salary;
	
	
	@OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
	private List<Email> emails;
	
	@OneToOne(mappedBy = "employee")
	private PanCard pancard;
	
	@ManyToMany(mappedBy = "employee")
	private List<Project> projects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public PanCard getPancard() {
		return pancard;
	}

	public void setPancard(PanCard pancard) {
		this.pancard = pancard;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", salary=" + salary + ", emails=" + emails
				+ ", pancard=" + pancard + ", projects=" + projects + "]";
	}

	
	

}
