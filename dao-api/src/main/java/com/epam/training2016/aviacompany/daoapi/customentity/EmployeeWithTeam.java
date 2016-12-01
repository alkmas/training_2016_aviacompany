package com.epam.training2016.aviacompany.daoapi.customentity;

import java.io.Serializable;

import com.epam.training2016.aviacompany.datamodel.Employee;

public class EmployeeWithTeam implements Serializable {
	private static final long serialVersionUID = 1L;
	private Employee employee;
	private Long teamId;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	@Override
	public String toString() {
		return "EmployeeWithTeam [employee=" + employee + ", teamId=" + teamId + "]";
	}
	
	
}
