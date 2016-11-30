package com.epam.training2016.aviacompany.daodb.impl;


import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.mapper.FlightDaysMapper;
import com.epam.training2016.aviacompany.daodb.util.CacheDao;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightDaysDaoImpl extends BaseDaoImpl<FlightDays> {

	final private String SQL_UPDATE_BY_ID = 
			"UPDATE flight_days SET day1=:day1, day2=:day2, day3=:day3, day4=:day4, day5=:day5," 
			+ "day6=:day6, day7=:day7 WHERE id=:id";

	final private String SQL_INSERT = 
			"INSERT INTO flight_days(id,day1,day2,day3,day4,day5,day6,day7)"
			+ " VALUES(:id, :day1, :day2, :day3, :day4, :day5, :day6, :day7)";

	@Inject
	protected JdbcTemplate jdbcTemplate;
	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Inject
	private CacheDao<FlightDays> cache;

	
	
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
		return cache.cacheEntity(
				() -> this.getById(id, new FlightDaysMapper()),
				"flightDays", id);
	}

	public List<FlightDays> getAll() {
		return cache.cacheList(
				() -> this.getAll(new FlightDaysMapper()),
				"flightDaysAll");
	}

	public void update(FlightDays entity) {
		this.update(entity, getSqlParameterSource(entity));
		cache.put("flightDays", entity.getId(), entity);
	}

	public Long insert(FlightDays entity) {
		namedParameterJdbcTemplate.update(SQL_INSERT, getSqlParameterSource(entity));
		Long id = entity.getId();
		cache.put("flightDays", id, entity);
		return id; 
	}


	@Override
	public Class<FlightDays> getGenericTypeClass() {
		return FlightDays.class;
	}
}
