package com.epam.training2016.aviacompany.services.impl;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.daodb.impl.FlightDaysDaoImpl;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Service
public class FlightDaysServiceImpl extends BaseServiceImpl<FlightDays> {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightDaysServiceImpl.class);
	@Inject
	private FlightDaysDaoImpl flightDaysDao;
	
	@Override
	public void save(FlightDays entity) {
		if (entity.getId() == null) {
			LOGGER.error("Operation: save. Id for FlightDays should not be null");
		}
		else {
			if (flightDaysDao.getById(entity.getId()) != null) {
				flightDaysDao.update(entity);
			}
			else {
				flightDaysDao.insert(entity);
			}
		}
	}

}
