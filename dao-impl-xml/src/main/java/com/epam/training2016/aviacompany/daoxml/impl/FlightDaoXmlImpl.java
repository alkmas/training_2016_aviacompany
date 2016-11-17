package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.daoapi.IFlightDaysDao;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightDaoXmlImpl extends BaseDaoXmlImpl<Flight> implements IFlightDao{
	
	@Inject
	private IFlightDaysDao flightDaysDao;
	
	@Override
	public List<Flight> getAllForDays(Integer dayWeek) {
		List<Flight> resultList = new ArrayList<>();
		for(FlightDays flightDays: flightDaysDao.getAllByDay(dayWeek)) {
			resultList.add(this.getById(flightDays.getId()));
		}
		return resultList;
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
