package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.EmployeeDao;
import com.epam.training2016.aviacompany.daodb.mapper.EmployeeWithJobTitleMapper;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {
	private String SQL_UPDATE_BY_ID = 
			"UPDATE employee SET first_name=:firstName,"
			+ "last_name=:lastName,"
			+ "birthday=:birthday,"
			+ "job_title_id=:jobTitleId WHERE id=:id";
	private String SQL_EMPLOYEE_WITH_JOBTITLE = 
			"SELECT * FROM employee e"
			+ " LEFT JOIN job_title j ON e.job_title_id = j.id";
	private String SQL_EMPLOYEE_WITH_JOBTITLE_BY_ID =
			SQL_EMPLOYEE_WITH_JOBTITLE + " WHERE e.id=?";
	private String SQL_SELECT_BY_JOBTITLE_ID =
			"SELECT * FROM employee WHERE job_title_id=?";
	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

	@Override
	public EmployeeWithJobtitle getWithJobtitle(Long id) {
		return jdbcTemplate.queryForObject(SQL_EMPLOYEE_WITH_JOBTITLE_BY_ID, 
				new Object[] { id }, 
				new EmployeeWithJobTitleMapper());
	}

	@Override
	public List<EmployeeWithJobtitle> getAllWithJobtitle() {
		return jdbcTemplate.query(SQL_EMPLOYEE_WITH_JOBTITLE, 
				new EmployeeWithJobTitleMapper());
	}

	@Override
	public List<Employee> getByJobTitleId(Long jobtitleId) {
		return jdbcTemplate.query(SQL_SELECT_BY_JOBTITLE_ID,
				new Object[] {jobtitleId},
				new BeanPropertyRowMapper<Employee>(Employee.class));
	}

}
