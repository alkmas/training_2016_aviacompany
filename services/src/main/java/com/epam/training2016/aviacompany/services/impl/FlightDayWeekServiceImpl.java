package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.FlightDayWeekDaoImpl;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.FlightDayWeekService;

@Service
public class FlightDayWeekServiceImpl implements FlightDayWeekService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightDayWeekServiceImpl.class);
	@Inject
	private FlightDayWeekDaoImpl flightDayWeekDao;

	@Override
	public void saveAll(List<FlightDays> entities) {
		for(FlightDays entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(FlightDays entity) {
		flightDayWeekDao.insert(entity);
	}

	@Override
	public boolean isDaoExist() {
		return flightDayWeekDao != null;
	}

	@Override
	public List<FlightDays> getByFlightId(Long flightId) {
		return flightDayWeekDao.getByFlightId(flightId);
	}

	@Override
	public void delete(FlightDays entity) {
		flightDayWeekDao.delete(entity);
	}

	@Override
	public void saveDaysWeekForFlight(Long flightId, List<Long> days) {
		if ((days == null) || (days.size() == 0) || flightId == null) {
			
		}
		else {
			List<FlightDays> flightDayWeekList = new ArrayList<FlightDays>();
			for(Long day: days) {
				flightDayWeekList.add(new FlightDays(flightId, day));
			}
			saveAll(flightDayWeekList);
		}
		
	}

	@Override
	public void deleteByFlightId(Long flightId) {
		flightDayWeekDao.deleteByFlightId(flightId);
	}

	@Override
	public List<Long> getDaysFromList(List<FlightDays> flightDaysWeek) {
		if ((flightDaysWeek == null) || (flightDaysWeek.size() == 0)) {
			return null;
		}
		List<Long> resultList = new ArrayList<Long>();
		for(FlightDays flightDayWeek: flightDaysWeek) {
			resultList.add(flightDayWeek.getDayWeek());
		}
		return resultList;
	}

}
