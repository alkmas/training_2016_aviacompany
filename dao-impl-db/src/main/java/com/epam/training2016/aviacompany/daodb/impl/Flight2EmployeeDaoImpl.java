package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

@Repository
public class Flight2EmployeeDaoImpl extends BaseDaoImpl<Flight2Employee> {
	final private String SQL_UPDATE = 
			"UPDATE flight_2_employee SET flight_id=:flightId,"
			+ "employee_id=:employeeId,"
			+ "departure=:departure WHERE id=:id";

	Flight2EmployeeDaoImpl() {
		super(Flight2Employee.class, "flight_2_employee");
	}

	@Override
	public String getSQLUpdate() {
		return SQL_UPDATE;
	}

}
