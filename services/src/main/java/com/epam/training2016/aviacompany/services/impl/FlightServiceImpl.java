package com.epam.training2016.aviacompany.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daoapi.IFlightDao;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

@Service
public class FlightServiceImpl extends BaseServiceImpl<Flight> implements FlightService {
	@Inject
	private IFlightDao flightDao;
	@Inject
	private BaseService<FlightDays> flightDaysService;
	@Inject
	private Flight2TeamService flight2TeamService;

	@Override
	public List<Flight> getAllByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return flightDao.getAllForDays(new Integer(calendar.get(Calendar.DAY_OF_WEEK)));
	}

	@Override
	public boolean isFlightExistByDate(Long flightId, Date date) {
		for (Flight flight : getAllByDate(date)) {
			if (flight.getId() == flightId) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void save(Flight entity) throws InvalidDataException {
		Boolean itNewRecord = (entity.getId() == null);
		super.save(entity);
		if (itNewRecord) {
			FlightDays flightDays = new FlightDays();
			flightDays.setId(entity.getId());
			flightDaysService.save(flightDays);
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		flightDaysService.deleteById(id);
		flightDao.deleteById(id);
	}

	@Override
	public List<Flight> getAllByDateWithoutTeam(Date date) {
		List<Flight> resultList = new ArrayList<Flight>();

		for (Flight flight : getAllByDate(date)) {
			Long id = flight.getId();
			if (flight2TeamService.getByFlightIdAndDate(id, date) == null) {
				resultList.add(flight);
			}
		}
		return resultList;
	}
}
