package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.AirportDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.services.AirportService;


@Service
public class AirportServiceImpl implements AirportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportServiceImpl.class);
    @Inject
	private AirportDaoImpl airportDao;

    
    @Override
    public boolean isDaoExist() {
        return airportDao != null;
    }

    @Override
    public void saveAll(List<Airport> airports) {
        for (Airport airport : airports) {
            save(airport);
        }
    }

    @Override
    public void save(Airport airport) {
        if (airport.getId() == null) {
        	airport.setId(airportDao.insert(airport));
        } else {
        	airportDao.update(airport);
        }
    }

	@Override
	public Airport getById(Long id) {
		return airportDao.getById(id);
	}

	@Override
	public List<Airport> getAll() {
		return airportDao.getAll();
	}

	@Override
	public void deleteById(Long id) {
		String airString = getById(id).toString();
		airportDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Airport", airString));
	}
	

	@Override
	public Airport getByName(String name) {
		Airport airport = airportDao.getByName(name);
		return airport;
	}

	@Override
	public List<Airport> filter(Airport entityFilter) {
		List<Airport> resultList = new ArrayList<Airport>(); 
		for(Airport airport: airportDao.getAll()) {
			if (airport.filter(entityFilter)) {
				resultList.add(airport);
			}
		}
		return resultList;
	}

}
