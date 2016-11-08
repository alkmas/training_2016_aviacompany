package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.FlightDayWeekDaoImpl;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;
import com.epam.training2016.aviacompany.services.FlightDayWeekService;

@Service
public class FlightDayWeekServiceImpl implements FlightDayWeekService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightDayWeekServiceImpl.class);
	@Inject
	private FlightDayWeekDaoImpl flightDayWeekDao;

	@Override
	@Transactional
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
	public List<FlightDayWeek> getByFlightId(Long flightId) {
		return flightDayWeekDao.getByFlightId(flightId);
	}

	@Override
	public void delete(FlightDayWeek entity) {
		flightDayWeekDao.delete(entity);
	}

	@Override
	public void saveDaysWeekForFlight(Long flightId, List<Long> days) {
		if ((days == null) || (days.size() == 0) || flightId == null) {
			
		}
		else {
			List<FlightDayWeek> flightDayWeekList = new ArrayList<FlightDayWeek>();
			for(Long day: days) {
				flightDayWeekList.add(new FlightDayWeek(flightId, day));
			}
			saveAll(flightDayWeekList);
		}
		
	}

	
	@Override
	public void deleteByFlightId(Long flightId) {
		flightDayWeekDao.deleteByFlightId(flightId);
	}

	
	@Override
	public List<Long> getDaysFromList(List<FlightDayWeek> flightDaysWeek) {
		if ((flightDaysWeek == null) || (flightDaysWeek.size() == 0)) {
			return null;
		}
		List<Long> resultList = new ArrayList<Long>();
		for(FlightDayWeek flightDayWeek: flightDaysWeek) {
			resultList.add(flightDayWeek.getDayWeek());
		}
		return resultList;
	}

}
