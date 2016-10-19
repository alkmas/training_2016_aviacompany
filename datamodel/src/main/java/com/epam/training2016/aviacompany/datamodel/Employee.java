package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Employee extends AbstractModel {
	private String firstName;
	private String lastName;
	private Date birtday;
	private Long jobTitleId;
	
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Employee(String firstName, String lastName, Long jobTitleId) {
		this( firstName, lastName);
		this.jobTitleId = jobTitleId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirtday() {
		return birtday;
	}
	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}
	public Long getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(Long jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
	
}
