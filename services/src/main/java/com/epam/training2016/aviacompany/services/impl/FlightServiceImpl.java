package com.epam.training2016.aviacompany.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.daodb.impl.FlightDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;


@Service
public class FlightServiceImpl extends BaseServiceImpl<Flight> implements FlightService {
	@Inject
	private FlightDaoImpl flightDao;

	
	@Override
	public List<FlightWithAirport> getAllByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return flightDao.getAllForDayWeek(
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

}
