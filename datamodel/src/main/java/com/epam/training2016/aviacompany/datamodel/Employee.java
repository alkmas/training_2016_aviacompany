package com.epam.training2016.aviacompany.datamodel;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс Сотрудники Компании
 * @author alex
 *
 */
public class Employee extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", jobTitleId=" + jobTitleId + "]";
	}

}
