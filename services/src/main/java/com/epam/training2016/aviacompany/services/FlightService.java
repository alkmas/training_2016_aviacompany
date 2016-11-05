package com.epam.training2016.aviacompany.services;

import java.util.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.services.utils.IdNullException;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

public interface FlightService extends BaseService<Flight> {
	/**
	 * Вернуть все рейсы на дату
	 * @param date
	 * @return
	 */
	List<FlightWithAirport> getAllByDate(Date date);
	void deleteByAirportSrcId(Long airportId) throws IdNullException;
	void deleteByAirportDstId(Long airportId) throws IdNullException;
}
