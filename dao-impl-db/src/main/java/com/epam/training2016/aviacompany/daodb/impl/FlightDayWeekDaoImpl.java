package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.FlightDayWeekDao;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

@Repository
public class FlightDayWeekDaoImpl implements FlightDayWeekDao {
	final private String SQL_INSERT = 
			"INSERT INTO flight_day_week(flight_id, day_week) VALUES(?,?)";
	final private String SQL_SELECT_BY_FLIGHT_ID = "SELECT * FROM flight_day_week WHERE flight_id=?";
	final private String SQL_DELETE_RECORD = "DELETE FROM flight_day_week WHERE flight_id=? AND day_week=?"; 
	final private String SQL_DELETE_BY_FLIGHT_ID = "DELETE FROM flight_day_week WHERE flight_id=?";
	
	
	@Inject
	protected JdbcTemplate jdbcTemplate;

	
	@Override
	public void insert(FlightDayWeek entity) {
		jdbcTemplate.update(SQL_INSERT, entity.getFlightId(), entity.getDayWeek());
	}

	@Override
	public List<FlightDayWeek> getByFlightId(Long id) {
		return jdbcTemplate.query(SQL_SELECT_BY_FLIGHT_ID,
				new Object[] { id },
				new BeanPropertyRowMapper<FlightDayWeek>(FlightDayWeek.class));
	}

	@Override
	public void delete(FlightDayWeek entity) {
		jdbcTemplate.update(SQL_DELETE_RECORD, 
				new Object[] { entity.getFlightId(), entity.getDayWeek() });
	}

	@Override
	public void deleteByFlightId(Long flightId) {
		jdbcTemplate.update(SQL_DELETE_BY_FLIGHT_ID, 
				new Object[] { flightId });
	}


}
