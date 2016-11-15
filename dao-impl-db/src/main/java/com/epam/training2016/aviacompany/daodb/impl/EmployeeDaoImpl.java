package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.EmployeeDao;
import com.epam.training2016.aviacompany.daodb.mapper.EmployeeWithTeamMapper;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {
	private String SQL_UPDATE_BY_ID = 
			"UPDATE employee SET first_name=:firstName,"
			+ "last_name=:lastName,"
			+ "birthday=:birthday,"
			+ "job_title_id=:jobTitleId WHERE id=:id";
	
	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}


}
