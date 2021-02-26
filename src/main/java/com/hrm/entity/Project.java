package com.hrm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="project")
public class Project {

	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="client")
	private String client;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="project_employee" ,joinColumns = {@JoinColumn(name = "project_id")},inverseJoinColumns ={@JoinColumn(name = "emp_id")})
	private List<Employee> employee;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", client=" + client + ", employee="
				+ employee + "]";
	}
	
	
	
}
