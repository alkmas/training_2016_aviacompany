package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.FlightDao;
import com.epam.training2016.aviacompany.daodb.mapper.FlightWithAirportMapper;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@Repository
public class FlightDaoImpl extends BaseDaoImpl<Flight> implements FlightDao{
	final private String SQL_UPDATE = "UPDATE flight SET name=:name,"
			+ "airport_src_id=:airportSrcId,"
			+ "airport_dst_id=:airportDstId,"
			+ "departure_time=:departureTime,"
			+ "arrival_time=:arrivalTime WHERE id=:id";

	final private String SQL_FLIGHT_WITH_AIRPORT = 
			"SELECT * FROM flight f "
			+ "LEFT JOIN airport a_src ON f.airport_src_id = a_src.id "
			+ "LEFT JOIN airport a_dst ON f.airport_dst_id = a_dst.id";
	
	FlightDaoImpl() {
		super(Flight.class, "flight");
	}

	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE;
	}

	@Override
	public FlightWithAirport getWithAirport(Long id) {
		String sql = SQL_FLIGHT_WITH_AIRPORT + " WHERE f.id=?";
		return jdbcTemplate.queryForObject(sql, 
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
		String sql = SQL_FLIGHT_WITH_AIRPORT +
				" LEFT JOIN flight_day_week d ON d.flight_id = f.id" +
				" WHERE d.day_week=?";
		return jdbcTemplate.query(sql, 
				new Object[] { dayWeek }, 
				new FlightWithAirportMapper());
	}

}
