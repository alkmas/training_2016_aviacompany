package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.datamodel.Flight;

public interface FlightService extends BaseService<Flight>{
	List<Flight> getAllByDate(Date date);
	/**
	 * Есть ли данный рейс на данную дату
	 * @param flightId
	 * @param date
	 * @return
	 */
	boolean isFlightExistByDate(Long flightId, Date date);
	List<Flight> getAllByDateWithoutTeam(Date date);
	List<FlightWithAirportsAndDaysWeek>	getAllWithFullInfo();
}
