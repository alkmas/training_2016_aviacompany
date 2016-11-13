package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.FlightDaoImpl;
import com.epam.training2016.aviacompany.daodb.impl.FlightDaysDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;


@Service
public class FlightServiceImpl extends BaseServiceImpl<Flight> implements FlightService {
	@Inject
	private FlightDaoImpl flightDao;
	@Inject
	private BaseService<FlightDays> flightDaysService;
	@Inject
	private Flight2TeamService flight2TeamService;
	
	@Override
	public List<FlightWithAirport> getAllByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return flightDao.getAllForDays(
				new Long(calendar.get(Calendar.DAY_OF_WEEK)));
	}


	@Override
	public boolean isFlightExistByDate(Long flightId, Date date) {
		for(FlightWithAirport flightWithAirport: getAllByDate(date)) {
			if (flightWithAirport.getFlight().getId() == flightId) {
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
//			flightDaysDao.insert(flightDays);
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		flightDaysService.deleteById(id);
		flightDao.deleteById(id);
	}

	
	@Override
	public List<FlightWithAirport> getAllByDateWithoutTeam(Date date) {
		List<FlightWithAirport> resultList = new ArrayList<FlightWithAirport>();
		for(FlightWithAirport flight: getAllByDate(date)) {
			if (flight2TeamService.getByFlightIdAndDate(flight.getFlight().getId(), date) == null) {
				resultList.add(flight);
			}
		}
		return resultList;
	}
}
