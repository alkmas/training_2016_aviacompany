package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> {
	EmployeeDaoImpl() {
		super(Employee.class);
	}

}
