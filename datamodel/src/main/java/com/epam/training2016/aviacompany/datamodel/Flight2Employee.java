package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Flight2Employee extends AbstractModel {
	private Long flightId;
	private Long employeeId;
	private Date arrival;
	
	
	public Flight2Employee(Long flightId, Long employeeId, Date arrival) {
		super();
		this.flightId = flightId;
		this.employeeId = employeeId;
		this.arrival = arrival;
	}
	
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	
	
	
}
