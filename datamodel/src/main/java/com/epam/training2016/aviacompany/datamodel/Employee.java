package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Employee extends AbstractModel {
	private String firstName;
	private String lastName;
	private Date birtday;
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

	public boolean equals(Employee obj) {
		if (super.equals(obj)
				&& this.firstName.equals(obj.getFirstName()) 
				&& this.lastName.equals(obj.getLastName())
				&& this.birtday.equals(obj.getBirtday())
				&& (this.jobTitleId == obj.getJobTitleId())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", birtday=" + birtday + ", jobTitleId="
				+ jobTitleId + "]";
	}
	
	
}
