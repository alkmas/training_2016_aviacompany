package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.BaseDao;
import com.epam.training2016.aviacompany.datamodel.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> {
	EmployeeDaoImpl() {
		super(Employee.class);
	}

}
