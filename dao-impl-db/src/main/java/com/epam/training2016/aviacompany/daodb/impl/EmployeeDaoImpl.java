package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.EmployeeDao;
import com.epam.training2016.aviacompany.daodb.mapper.EmployeeWithJobTitleMapper;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {
	final private String SQL_EMPLOYEE_WITH_JOBTITLE = 
			"SELECT * FROM employee e"
			+ " LEFT JOIN job_title j ON e.job_title_id = j.id";

	
	EmployeeDaoImpl() {
		super(Employee.class, "employee");
	}

	@Override
	public EmployeeWithJobtitle getWithJobtitle(Long id) {
		String sql = SQL_EMPLOYEE_WITH_JOBTITLE + " WHERE f.id=?";
		return jdbcTemplate.queryForObject(sql, 
				new Object[] { id }, 
				new EmployeeWithJobTitleMapper());
	}

	@Override
	public List<EmployeeWithJobtitle> getAllWithJobtitle() {
		return jdbcTemplate.query(SQL_EMPLOYEE_WITH_JOBTITLE, 
				new EmployeeWithJobTitleMapper());
	}

}
