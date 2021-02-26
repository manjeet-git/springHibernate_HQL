package com.hrm.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="email")
public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180029798066286688L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email_id")
	private int emailId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	
	/*
	 * I had this error because I was parsing a list of objects mapped on both
	 * sides @OneToMany and @ManyToOne to json using jackson which caused an
	 * infinite loop.
	 * 
	 * If you are in the same situation you can solve this by using
	 */
	@JsonBackReference
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id",nullable = false)
	private Employee employee;

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Email [emailId=" + emailId + ", username=" + username + ", password=" + password + ", employee="
				+ employee + "]";
	}
	


	
	
	
	
}
