package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.Flight2EmployeeDao;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

@Repository
public class Flight2EmployeeDaoImpl extends BaseDaoImpl<Flight2Employee> implements Flight2EmployeeDao {
	final private static String NAME_TABLE = "flight_2_employee";
	private String SQL_UPDATE_BY_ID = 
			"UPDATE flight_2_employee SET flight_id=:flightId,"
			+ "employee_id=:employeeId,"
			+ "departure=:departure WHERE id=:id";
	private String SQL_SELECT_BY_FLIGHT_ID =
			"SELECT * FROM flight_2_employee WHERE flight_id=?";
	private String SQL_SELECT_BY_EMPLOYEE_ID =
			"SELECT * FROM flight_2_employee WHERE employee_id=?";
	
	
	Flight2EmployeeDaoImpl() {
		super(Flight2Employee.class, NAME_TABLE);
	}

	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

	@Override
	public List<Flight2Employee> getByFlightId(Long id) {
		return jdbcTemplate.query(SQL_SELECT_BY_FLIGHT_ID,
				new Object[] { id },
				new BeanPropertyRowMapper<Flight2Employee>(Flight2Employee.class));
	}

	@Override
	public List<Flight2Employee> getByEmployeeId(Long id) {
		return jdbcTemplate.query(SQL_SELECT_BY_EMPLOYEE_ID,
				new Object[] { id },
				new BeanPropertyRowMapper<Flight2Employee>(Flight2Employee.class));
	}
}
