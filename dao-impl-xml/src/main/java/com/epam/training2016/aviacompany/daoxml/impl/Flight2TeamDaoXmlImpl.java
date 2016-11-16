package com.epam.training2016.aviacompany.daoxml.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlight2TeamDao;
import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

@Repository
public class Flight2TeamDaoXmlImpl extends BaseDaoXmlImpl<Flight2Team> implements IFlight2TeamDao{

	@Override
	public List<Flight2Team> getByFlightId(Long flightId) {
        throw new UnsupportedOperationException();
	}

	@Override
	public List<Flight2Team> getByTeamId(Long teamId) {
        throw new UnsupportedOperationException();
	}

	@Override
	public List<Flight2Team> getByDeparture(Date date) {
        throw new UnsupportedOperationException();
	}

	@Override
	public Flight2Team getByTeamIdAndDate(Long teamId, Date date) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByFlightIdAndDate(Long flightId, Date date) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByTeamId(Long teamId) {
        throw new UnsupportedOperationException();
	}



}
