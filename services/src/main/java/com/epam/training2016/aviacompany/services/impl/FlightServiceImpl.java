package com.epam.training2016.aviacompany.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.FlightDaoImpl;
import com.epam.training2016.aviacompany.daodb.impl.FlightDaysDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;


@Service
public class FlightServiceImpl extends BaseServiceImpl<Flight> implements FlightService {
	@Inject
	private FlightDaoImpl flightDao;
	@Inject
	private FlightDaysDaoImpl flightDaysDao;
//	@Inject
//	private FlightDays flightDays;
	
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
	public void save(Flight entity) {
		Boolean itNewRecord = (entity.getId() == null);
		super.save(entity);
		if (itNewRecord) {
			FlightDays flightDays = new FlightDays();
			flightDays.setId(entity.getId());
			flightDaysDao.insert(flightDays);
		}
	}

}
