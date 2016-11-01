package com.epam.training2016.aviacompany.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.FlightDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;


@Service
public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
	@Inject
	private FlightDaoImpl flightDao;

	@Override
	public void saveAll(List<Flight> entities) {
		for (Flight entity : entities) {
			save(entity);
		}
	}

	@Override
	public void save(Flight entity) {
		if (entity.getId() == null) {
			entity.setId(flightDao.insert(entity));
		} else {
			flightDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return flightDao != null;
	}

	@Override
	public Flight getById(Long id) {
		return flightDao.getById(id);
	}

	@Override
	public List<Flight> getAll() {
		return flightDao.getAll();
	}

	@Override
	public List<FlightWithAirport> getAllByAway(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return flightDao.getAllForDayWeek(
				new Long(calendar.get(Calendar.DAY_OF_WEEK)));
	}

	@Override
	public void deleteById(Long id) {
		flightDao.deleteById(id);
	}

}
