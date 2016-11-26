package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.daoapi.IFlightDaysDao;
import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.daoxml.utils.BaseXML;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightDaoXmlImpl extends BaseDaoXmlImpl<Flight> implements IFlightDao{
	@Inject
	private IFlightDaysDao flightDaysDao;
	
	@Override
	public List<Flight> getAllForDays(Integer dayWeek) {
		List<Flight> resultList = new ArrayList<>();
		for(FlightDays flightDays: flightDaysDao.getAllByDay(dayWeek)) {
			Flight flight = this.getById(flightDays.getId());
			resultList.add(flight);
		}
		return resultList;
	}

	@Override
	public void deleteByFlightId(Long flightId) {
		baseXML.deleteByField("flightId", flightId);
	}

	@Override
	public void deleteByAirportSrcId(Long airportId) {
		baseXML.deleteByField("airportSrcId", airportId);
	}

	@Override
	public void deleteByAirportDstId(Long airportId) {
		baseXML.deleteByField("airportDstId", airportId);
	}

	@Override
	public Class<Flight> getGenericType() {
		return Flight.class;
	}

	@Override
	public List<FlightWithAirportsAndDaysWeek> getAllWithFullInfo() {
		throw new UnsupportedOperationException();
	}

}
