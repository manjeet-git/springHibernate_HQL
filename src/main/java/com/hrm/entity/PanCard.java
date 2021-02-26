package com.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="pancard")
public class PanCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pancard_id")
	private int pancardId;
	
	@Column(name="pancard_name")
	private String pancardHolderName;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	public int getPancardId() {
		return pancardId;
	}

	public void setPancardId(int pancardId) {
		this.pancardId = pancardId;
	}

	public String getPancardHolderName() {
		return pancardHolderName;
	}

	public void setPancardHolderName(String pancardHolderName) {
		this.pancardHolderName = pancardHolderName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "PanCard [pancardId=" + pancardId + ", pancardHolderName=" + pancardHolderName + ", employee=" + employee
				+ "]";
	}

	
	
	
	
	
	

}
