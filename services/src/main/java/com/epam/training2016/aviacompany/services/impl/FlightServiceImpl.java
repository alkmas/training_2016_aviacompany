package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.FlightDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.FlightDayWeekService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirportAndDaysWeek;

@Service
public class FlightServiceImpl implements FlightService {
	@Inject
	private FlightDaoImpl flightDao;
	@Inject
    private BaseService<Airport> airportService;
	@Inject
	private FlightDayWeekService flightDayWeekService;

	@Override
	public void saveAll(List<Flight> entities) {
		for(Flight entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(Flight entity) {
		if (entity.getId() == null) {
			flightDao.insert(entity);
		} else {
			flightDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return flightDao != null;
	}

	@Override
	public Flight get(Long id) {
		return flightDao.get(id);
	}

	@Override
	public Long insert(Flight entity) {
		return flightDao.insert(entity);
	}

	@Override
	public FlightWithAirportAndDaysWeek getFlight(Long id) {
		Flight flight = get(id);
		Airport airportSrc = airportService.get(flight.getAirportSrcId());
		Airport airportDst = airportService.get(flight.getAirportDstId());
		List<FlightDayWeek> flightDaysWeek = flightDayWeekService.get(id);
		
		FlightWithAirportAndDaysWeek resultEntity = new FlightWithAirportAndDaysWeek();
		resultEntity.setFlight(flight);
		resultEntity.setAirportSrc(airportSrc);
		resultEntity.setAirportDst(airportDst);
		resultEntity.setDayWeeks(flightDaysWeek);
		return resultEntity;
	}

	@Override
	public FlightWithAirportAndDaysWeek getFlight(Flight flight) {
		return getFlight(flight.getId());
	}

	@Override
	public List<FlightWithAirportAndDaysWeek> getFlights(List<Flight> flights) {
		List<FlightWithAirportAndDaysWeek> newList =
				new ArrayList<FlightWithAirportAndDaysWeek>();
		for(Flight flight: flights) {
			FlightWithAirportAndDaysWeek entity = getFlight(flight.getId());
			newList.add(entity);
		}
		return newList;
	}

	@Override
	public List<FlightWithAirportAndDaysWeek> getAllFlight() {
		return getFlights(getAll());
	}

	@Override
	public List<Flight> getAll() {
		return flightDao.getAll();
	}
	
}
