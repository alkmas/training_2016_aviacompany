package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

/**
 * Класс Сотрудники Компании
 * @author alex
 *
 */
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

	public void setBirthday(Date birtday) {
		this.birthday = birtday;
	}

	public Long getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(Long jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
/*
	public boolean equals(Employee obj) {
		if (super.equals(obj) && this.firstName.equals(obj.getFirstName()) 
				&& this.lastName.equals(obj.getLastName())
				&& (this.birthday.getTime() == obj.getBirthday().getTime()) && (this.jobTitleId == obj.getJobTitleId())) {
			return true;
		}
		return false;
	}
*/
	public boolean isNullFirstName() {
		return firstName == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((jobTitleId == null) ? 0 : jobTitleId.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (jobTitleId == null) {
			if (other.jobTitleId != null)
				return false;
		} else if (!jobTitleId.equals(other.jobTitleId))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public boolean isNullLastName() {
		return lastName == null;
	}

	public boolean isNullBirthday() {
		return birthday == null;
	}

	public boolean isNullJobTitleId() {
		return jobTitleId == null;
	}


	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", jobTitleId=" + jobTitleId + "]";
	}

}
