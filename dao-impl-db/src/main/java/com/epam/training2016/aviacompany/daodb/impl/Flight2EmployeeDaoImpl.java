package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.Flight2EmployeeDao;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

@Repository
public class Flight2EmployeeDaoImpl extends BaseDaoImpl<Flight2Employee> implements Flight2EmployeeDao {

	Flight2EmployeeDaoImpl() {
		super(Flight2Employee.class);
	}

}
