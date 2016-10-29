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
	@Inject
	protected JdbcTemplate jdbcTemplate;

	@Override
	public void insert(FlightDayWeek entity) {
		String sql = "INSERT INTO flight_day_week(flight_id, day_week)" +
				" VALUES(?,?)";
		jdbcTemplate.update(sql, 
				new BeanPropertyRowMapper<FlightDayWeek>(FlightDayWeek.class));
	}

	@Override
	public List<FlightDayWeek> get(Long id) {
		return jdbcTemplate.query(
				"SELECT * FROM flight_day_week WHERE flight_id=?",
				new Object[] { id },
				new BeanPropertyRowMapper<FlightDayWeek>(FlightDayWeek.class));
	}

	@Override
	public void delete(FlightDayWeek entity) {
		jdbcTemplate.update(
				"DELETE FROM flight_day_week WHERE flight_id=? AND day_week=?", 
				new Object[] { entity.getFlightId(), entity.getDayWeek() });
	}

}
