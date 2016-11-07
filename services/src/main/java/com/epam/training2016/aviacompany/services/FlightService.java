package com.epam.training2016.aviacompany.services;

import java.util.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

public interface FlightService {
    void saveAll(List<Flight> entities);
    void save(Flight entity);
    void deleteById(Long id);
    boolean isDaoExist();
    Flight getById(Long id);
    Flight getByName(String name);
    List<Flight> getAll();
	List<FlightWithAirport> getAllByDate(Date date);
	/**
	 * Есть ли данный рейс на данную дату
	 * @param flightId
	 * @param date
	 * @return
	 */
	boolean isFlightExistByDate(Long flightId, Date date);

}
