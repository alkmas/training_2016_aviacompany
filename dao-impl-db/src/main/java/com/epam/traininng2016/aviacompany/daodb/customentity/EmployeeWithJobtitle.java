package com.epam.traininng2016.aviacompany.daodb.customentity;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;

public class EmployeeWithJobtitle {
	private Employee employee;
	private JobTitle jobtitle;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public JobTitle getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(JobTitle jobtitle) {
		this.jobtitle = jobtitle;
	}
	@Override
	public String toString() {
		return "EmployeeWithJobtitle [employee=" + employee + ", jobtitle=" + jobtitle + "]";
	}
	
	
}
