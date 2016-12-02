package com.epam.training2016.aviacompany.daodb.impl;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlight2TeamDao;
import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.daodb.mapper.Flight2TeamJoinMapper;
import com.epam.training2016.aviacompany.daodb.util.CacheDao;
import com.epam.training2016.aviacompany.daodb.util.IGetList;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

@Repository
public class Flight2TeamDaoImpl extends BaseDaoImpl<Flight2Team> implements IFlight2TeamDao {
	private String SQL_UPDATE_BY_ID = 
			"UPDATE flight_2_team SET flight_id=:flightId,"
			+ "team_id=:teamId,"
			+ "departure=:departure WHERE id=:id";
	private String SQL_SELECT_BY_FLIGHT_ID =
			"SELECT * FROM flight_2_team WHERE flight_id=?";
	private String SQL_SELECT_BY_TEAM_ID =
			"SELECT * FROM flight_2_team WHERE team_id=?";
	private String SQL_SELECT_BY_TEAM_ID_AND_DATE =
			"SELECT * FROM flight_2_team WHERE team_id=? AND departure=?";
	private String SQL_SELECT_BY_DATE =
			"SELECT * FROM flight_2_team WHERE departure=?";
	private String SQL_DELETE_BY_FLIGHT_ID_AND_DATE = 
			"DELETE FROM flight_2_team WHERE flight_id=? AND departure=?";	
	private String SQL_DELETE_BY_TEAM_ID = 
			"DELETE FROM flight_2_team WHERE team_id=?";	
	
	private String SQL_SELECT_JOIN = 
			"SELECT f2t.id, f2t.flight_id, f2t.team_id, f2t.departure, f.name,"
			+ " f.airport_src_id, f.airport_dst_id, f.departure_time, f.arrival_time,"
			+ " t.pilot, t.navigator, t.radioman, t.stewardess1, t.stewardess2"
			+ " FROM flight_2_team f2t LEFT JOIN flight f ON f.id=f2t.flight_id"
			+ "	LEFT JOIN team t ON t.id=f2t.team_id";
	
	@Inject
	private CacheDao<Flight2Team> cache;

	@Override
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

	@Override
	public List<Flight2Team> getByFlightId(Long flightId) {
		return cache.cacheList(() -> jdbcTemplate.query(SQL_SELECT_BY_FLIGHT_ID,
										new Object[] { flightId },
										new BeanPropertyRowMapper<Flight2Team>(Flight2Team.class)),
									"getByFlightId");
	}

	@Override
	public List<Flight2Team> getByTeamId(Long teamId) {
		return jdbcTemplate.query(SQL_SELECT_BY_TEAM_ID,
				new Object[] { teamId },
				new BeanPropertyRowMapper<Flight2Team>(Flight2Team.class));
	}

	@Override
	public Flight2Team getByTeamIdAndDate(Long teamId, Date date) {
		return jdbcTemplate.queryForObject(SQL_SELECT_BY_TEAM_ID_AND_DATE,
				new Object[] { teamId, date },
				new BeanPropertyRowMapper<Flight2Team>(Flight2Team.class));
	}

	@Override
	public List<Flight2Team> getByDeparture(Date date) {
		return jdbcTemplate.query(SQL_SELECT_BY_DATE,
				new Object[] { date },
				new BeanPropertyRowMapper<Flight2Team>(Flight2Team.class));
	}

	@Override
	public int deleteByFlightIdAndDate(Long flightId, Date date) {
		return jdbcTemplate.update(SQL_DELETE_BY_FLIGHT_ID_AND_DATE, 
				new Object[] { flightId, date });
	}

	@Override
	public int deleteByTeamId(Long teamId) {
		return jdbcTemplate.update(SQL_DELETE_BY_TEAM_ID, 
				new Object[] { teamId });
	}

	@Override
	public Class<Flight2Team> getGenericTypeClass() {
		return Flight2Team.class;
	}

	@Override
	public List<Flight2TeamJoin> getAllJoin() {
		return jdbcTemplate.query(SQL_SELECT_JOIN,
				new Flight2TeamJoinMapper());
	}

}
