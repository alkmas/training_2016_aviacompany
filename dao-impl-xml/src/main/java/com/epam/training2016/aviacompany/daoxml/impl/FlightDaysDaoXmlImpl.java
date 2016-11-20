package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlightDaysDao;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightDaysDaoXmlImpl extends BaseDaoXmlImpl<FlightDays> implements IFlightDaysDao {

	@Override
	public List<FlightDays> getAllByDay(Integer day) {
		List<FlightDays> resultList = new ArrayList<>();
		for(FlightDays flightDays: this.getAll()) {
			if (flightDays.getDay(day)) {
				resultList.add(flightDays);
			}
		}
		return resultList;
	}

	@Override
	public Class<FlightDays> getGenericType() {
		return FlightDays.class;
	}
	
}
