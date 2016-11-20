package com.epam.training2016.aviacompany.daoxml.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Employee;

@Repository
public class EmployeetDaoXmlImpl extends BaseDaoXmlImpl<Employee> {

	@Override
	public Class<Employee> getGenericType() {
		return Employee.class;
	}

}
