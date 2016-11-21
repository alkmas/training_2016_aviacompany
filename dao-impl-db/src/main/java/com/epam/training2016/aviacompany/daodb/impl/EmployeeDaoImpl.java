package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.daodb.mapper.EmployeeWithTeamMapper;
import com.epam.training2016.aviacompany.datamodel.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> {
	private String SQL_UPDATE_BY_ID = 
			"UPDATE employee SET first_name=:firstName,"
			+ "last_name=:lastName,"
			+ "birthday=:birthday,"
			+ "job_title_id=:jobTitleId WHERE id=:id";
	
	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}


	@Override
	public Class<Employee> getGenericTypeClass() {
		return Employee.class;
	}


}
