package com.epam.training2016.aviacompany.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.FlightDayWeekDaoImpl;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;
import com.epam.training2016.aviacompany.services.FlightDayWeekService;

@Service
public class FlightDayWeekServiceImpl implements FlightDayWeekService {
	@Inject
	private FlightDayWeekDaoImpl flightDayWeekDao;

	@Override
	public void saveAll(List<FlightDayWeek> entities) {
		for(FlightDayWeek entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(FlightDayWeek entity) {
		flightDayWeekDao.insert(entity);
	}

	@Override
	public boolean isDaoExist() {
		return flightDayWeekDao != null;
	}

	@Override
	public List<FlightDayWeek> get(Long id) {
		return flightDayWeekDao.getByFlightId(id);
	}

	@Override
	public void insert(FlightDayWeek entity) {
		flightDayWeekDao.insert(entity);
	}
}
