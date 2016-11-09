package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.FlightDao;
import com.epam.training2016.aviacompany.daodb.mapper.FlightWithAirportMapper;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@Repository
public class FlightDaoImpl extends BaseDaoImpl<Flight> implements FlightDao{
	final private static String NAME_TABLE = "flight";

	private String SQL_UPDATE_BY_ID = "UPDATE flight SET name=:name,"
			+ "airport_src_id=:airportSrcId,"
			+ "airport_dst_id=:airportDstId,"
			+ "departure_time=:departureTime,"
			+ "arrival_time=:arrivalTime WHERE id=:id";
	private String SQL_FLIGHT_WITH_AIRPORT = 
			"SELECT * FROM flight f "
			+ "LEFT JOIN airport a_src ON f.airport_src_id = a_src.id "
			+ "LEFT JOIN airport a_dst ON f.airport_dst_id = a_dst.id";
	private String SQL_FLIGHT_WITH_AIRPORT_BY_ID =
			SQL_FLIGHT_WITH_AIRPORT + " WHERE f.id=?";
	private String SQL_FLIGHT_WITH_AIRPORT_BY_WEEKDAY = 
			SQL_FLIGHT_WITH_AIRPORT +
			" LEFT JOIN flight_day_week d ON d.flight_id = f.id" +
			" WHERE d.day_week=?";
	private String SQL_DELETE_BY_FLIGHT_ID = "DELETE FROM flight WHERE id=?";
	private String SQL_DELETE_BY_AIRPORT_SRC_ID = "DELETE FROM flight WHERE airport_src_id=?";
	private String SQL_DELETE_BY_AIRPORT_DST_ID = "DELETE FROM flight WHERE airport_dst_id=?";
	
	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

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
	public List<FlightWithAirport> getAllForDayWeek(Long dayWeek) {
		return jdbcTemplate.query(SQL_FLIGHT_WITH_AIRPORT_BY_WEEKDAY, 
				new Object[] { dayWeek }, 
				new FlightWithAirportMapper());
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

}
