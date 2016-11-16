package com.epam.training2016.aviacompany.daoapi.customentity;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;

public class FlightWithEmployee {
	private Flight flight;
	private Employee employee;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}

