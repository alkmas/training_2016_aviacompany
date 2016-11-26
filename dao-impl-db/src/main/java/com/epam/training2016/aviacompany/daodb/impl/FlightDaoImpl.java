package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.daodb.mapper.FlightWithFullInfoMapper;
import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public class FlightDaoImpl extends BaseDaoImpl<Flight> implements IFlightDao {

	private String SQL_UPDATE_BY_ID = "UPDATE flight SET name=:name,"
			+ "airport_src_id=:airportSrcId,"
			+ "airport_dst_id=:airportDstId,"
			+ "departure_time=:departureTime,"
			+ "arrival_time=:arrivalTime WHERE id=:id";

	private String SQL_SELECT_FLIGHT_BY_WEEKDAY = 
			"SELECT * FROM flight f LEFT JOIN flight_days d ON d.id = f.id WHERE d.day%d=TRUE";
	
	private String SQL_DELETE_BY_FLIGHT_ID = "DELETE FROM flight WHERE id=?";
	private String SQL_DELETE_BY_AIRPORT_SRC_ID = "DELETE FROM flight WHERE airport_src_id=?";
	private String SQL_DELETE_BY_AIRPORT_DST_ID = "DELETE FROM flight WHERE airport_dst_id=?";
	
	private String SQL_SELECT_FLIGHT_WITH_FULL_INFO =
			"SELECT f.id, f.name, a1.name AS src_name, a2.name AS dst_name,"
			+ "airport_src_id, airport_dst_id, departure_time, arrival_time,"
			+ "d.id AS days_id, day1, day2, day3, day4, day5, day6, day7"
			+ " FROM flight f LEFT JOIN airport a1 ON a1.id = f.airport_src_id"
			+ " LEFT JOIN airport a2 ON a2.id = f.airport_dst_id"
			+ " LEFT JOIN flight_days d ON d.id = f.id";
	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}
/*
	@Override
	public FlightWithAirport getWithAirport(Long id) {
		return jdbcTemplate.queryForObject(SQL_FLIGHT_WITH_AIRPORT_BY_ID, 
				new Object[] { id }, 
				new FlightWithAirportMapper());
	}

	@Override
	public List<FlightWithAirport> getAllWithAirport() {
		return jdbcTemplate.query(SQL_FLIGHT_WITH_AIRPORT, 
				new FlightWithAirportMapper());
	}

	@Override
	public List<FlightWithAirport> getAllForDays(Long dayWeek) {
		return jdbcTemplate.query(String.format(SQL_FLIGHT_WITH_AIRPORT_BY_WEEKDAY, dayWeek), 
				new FlightWithAirportMapper());
	}
*/
	
	@Override
	public List<Flight> getAllForDays(Integer dayWeek) {
		return jdbcTemplate.query(String.format(SQL_SELECT_FLIGHT_BY_WEEKDAY, dayWeek), 
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public List<FlightWithAirportsAndDaysWeek> getAllWithFullInfo() {
		return jdbcTemplate.query(SQL_SELECT_FLIGHT_WITH_FULL_INFO, 
				new FlightWithFullInfoMapper());
	}
	
	@Override
	public void deleteByFlightId(Long flightId) {
		jdbcTemplate.update(SQL_DELETE_BY_FLIGHT_ID, new Object[] {flightId});
	}

	
	@Override
	public void deleteByAirportSrcId(Long airportId) {
		jdbcTemplate.update(SQL_DELETE_BY_AIRPORT_SRC_ID, new Object[] {airportId});
	}

	
	@Override
	public void deleteByAirportDstId(Long airportId) {
		jdbcTemplate.update(SQL_DELETE_BY_AIRPORT_DST_ID, new Object[] {airportId});
	}

	@Override
	public Class<Flight> getGenericTypeClass() {
		return Flight.class;
	}

}
