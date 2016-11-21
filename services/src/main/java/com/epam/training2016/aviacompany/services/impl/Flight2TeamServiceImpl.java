package com.epam.training2016.aviacompany.services.impl;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daoapi.IFlight2TeamDao;
import com.epam.training2016.aviacompany.daodb.impl.Flight2TeamDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.services.Flight2TeamService;

@Service
public class Flight2TeamServiceImpl extends BaseServiceImpl<Flight2Team> implements Flight2TeamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Flight2TeamServiceImpl.class);

	@Inject
	private IFlight2TeamDao flight2TeamDao;
/*	
	@Inject
	private FlightService flightService;
	@Inject
	private EmployeeService employeeService;
	@Inject
	private BaseServiceImpl<JobTitle> jobtitleService;
*/
	@Override
	public Flight2Team getByTeamIdAndDate(Long teamId, Date date) {
		try {
			return flight2TeamDao.getByTeamIdAndDate(teamId, date);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Method: getByTeamIdAndDate. Record (teamId:" + teamId + ", date:" + date
					+ " from table Flight2Team not exist.");
			return null;
		}
	}

	@Override
	public List<Flight2Team> getByFlightId(Long id) {
		return flight2TeamDao.getByFlightId(id);
	}

	@Override
	public List<Flight2Team> getByTeamId(Long teamId) {
		return flight2TeamDao.getByTeamId(teamId);
	}

	@Override
	public List<Flight2Team> getByDeparture(Date date) {
		return flight2TeamDao.getByDeparture(date);
	}
	
	@Override
	public void deleteByTeamId(Long teamId) {
		flight2TeamDao.deleteByTeamId(teamId);
		LOGGER.info(String.format("Deleted (%s) from table (Flight2Team)", teamId));
	}

	@Override
	@Transactional
	public void deleteByFlightId(Long flightId) {
		for(Flight2Team f2e: flight2TeamDao.getByFlightId(flightId)) {
			deleteById(f2e.getId());
		}
	}
	
	@Override
	public Flight2Team getByFlightIdAndDate(Long flightId, Date date) {
		for(Flight2Team f2e: getByFlightId(flightId)) {
			if (f2e.getDeparture().getTime() == date.getTime()) {
				return f2e;
			}
		}
		return null;
	}

	@Override
	public Class<Flight2Team> getGenericTypeClass() {
		return Flight2Team.class;
	}

}
