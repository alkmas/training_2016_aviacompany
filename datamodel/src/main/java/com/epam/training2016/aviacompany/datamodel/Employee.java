package com.epam.training2016.aviacompany.datamodel;

import java.sql.Date;

public class Employee extends AbstractModel {
	private String firstName;
	private String lastName;
	private Date birthday;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(Long jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public boolean equals(Employee obj) {
		if (this == obj) return true;
		if (super.equals(obj)
				&& this.firstName.equals(obj.getFirstName()) 
				&& this.lastName.equals(obj.getLastName())
				&& this.birthday.equals(obj.getBirthday())
				&& (this.jobTitleId == obj.getJobTitleId())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", jobTitleId=" + jobTitleId + "]";
	}

	
}
