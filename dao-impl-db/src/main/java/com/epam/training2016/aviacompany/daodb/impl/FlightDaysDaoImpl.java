package com.epam.training2016.aviacompany.daodb.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.mapper.FlightDaysMapper;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightDaysDaoImpl extends BaseDaoImpl<FlightDays> {
	final private String SQL_UPDATE_BY_ID = 
			"UPDATE flight_days SET day1=:day1, day2=:day2, day3=:day3, day4=:day4, day5=:day5," 
			+ "day6=:day6, day7=:day7 WHERE id=:id";

	final private String SQL_INSERT = 
			"INSERT INTO flight_days(day1,day2,day3,day4,day5,day6,day7)"
			+ " VALUES(:day1, day2, :day3, :day4, :day5, :day6, day7)";
	
	@Inject
	protected JdbcTemplate jdbcTemplate;
	
	
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}
	
	
	private MapSqlParameterSource getSqlParameterSource(FlightDays entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", entity.getId());
		for(int i=1; i<=7; i++) {
			parameters.addValue("day"+i, entity.getDay(i));
		}
		return parameters;
	}
	
	
	public FlightDays getById(Long id) {
		return super.getById(id, new FlightDaysMapper());
	}

	public List<FlightDays> getAll() {
		return super.getAll(new FlightDaysMapper());
	}

	public void update(FlightDays entity) {
		super.update(entity, getSqlParameterSource(entity));
	}

	public Long insert(FlightDays entity) {
		return super.insert(entity, getSqlParameterSource(entity));
	}
}
