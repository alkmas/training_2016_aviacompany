package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;


public class Flight2Employee extends AbstractModel {
	private Long flightId;
	private Long employeeId;
	private Date departure;
	
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

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	
	@Override
	public String toString() {
		return "Flight2Employee [flightId=" + flightId + ", employeeId=" + employeeId + ", departure=" + departure
				+ "]";
	}
	
}
