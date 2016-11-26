package com.epam.training2016.aviacompany.web.model;

import java.util.Date;

public class EmployeeModel {
	private String firstName;
	private String lastName;
	private String birthday;
	private Long jobTitleId;
	
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Long getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(Long jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
	
}
