package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public class FlightDaoXmlImpl extends BaseDaoXmlImpl<Flight> implements IFlightDao{
	
	
	@Override
	public List<Flight> getAllForDays(Long dayWeek) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByFlightId(Long flightId) {
		this.deleteByField("flightId", flightId);
	}

	@Override
	public void deleteByAirportSrcId(Long airportId) {
		this.deleteByField("airportSrcId", airportId);
	}

	@Override
	public void deleteByAirportDstId(Long airportId) {
		this.deleteByField("airportDstId", airportId);
	}

}
