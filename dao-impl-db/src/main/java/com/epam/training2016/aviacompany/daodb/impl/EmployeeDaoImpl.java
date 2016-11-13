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
	
	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID = 
			"SELECT e.id, e.first_name, e.last_name, e.birthday, e.job_title_id, t.id AS team_id"
			+ " FROM employee e LEFT JOIN team t ON t.pilot = e.id OR t.navigator = e.id"
			+ " OR t.radioman = e.id  OR t.stewardess1 = e.id  OR t.stewardess2 = e.id"; 
	
	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_ID =
			SQL_SELECT_EMPLOYEE_WITH_TEAM_ID + " WHERE e.id=?";
	
	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_JOB_ID =
			SQL_SELECT_EMPLOYEE_WITH_TEAM_ID + " WHERE e.job_title_id=?";

	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

	@Override
	public EmployeeWithTeam getWithTeamById(Long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_ID, 
				new Object[] { id }, 
				new EmployeeWithTeamMapper());
	}

	@Override
	public List<EmployeeWithTeam> getAllWithTeam() {
		return jdbcTemplate.query(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID, 
				new EmployeeWithTeamMapper());
	}

	@Override
	public List<EmployeeWithTeam> getAllWithTeamByJobId(Long jobId) {
		return jdbcTemplate.query(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_JOB_ID,
				new Object[] {jobId},
				new EmployeeWithTeamMapper());
	}

}
