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

	public boolean equals(Flight2Employee obj) {
		if (super.equals(obj)
				&& (this.flightId == obj.getFlightId())
				&& (this.employeeId == obj.getEmployeeId())
				&& this.departure.equals(obj.getDeparture())) {
			return true;			
		}
		return false;
	}

	public boolean filter(Flight2Employee objFilter) {
		if (super.filter(objFilter)
				&& ((this.flightId == objFilter.getFlightId()) || (objFilter.getFlightId() == null))
				&& ((this.employeeId == objFilter.getEmployeeId()) || (objFilter.getEmployeeId() == null))
				&& (this.departure.equals(objFilter.getDeparture()) || (objFilter.getDeparture() == null))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Flight2Employee [flightId=" + flightId + ", employeeId=" + employeeId + ", departure=" + departure
				+ "]";
	}
	
}
