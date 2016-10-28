package com.epam.training2016.aviacompany.services.impl;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.BaseDao;
import com.epam.training2016.aviacompany.daodb.impl.AirportDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.services.AirportService;
import com.epam.training2016.aviacompany.services.BaseService;


@Service
public class AirportServiceImpl implements BaseService<Airport> {
    
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
        	airportDao.insert(airport);
        } else {
        	airportDao.updateAllField(airport);
        }
    }

	@Override
	public Airport get(Long id) {
		return airportDao.get(id);
	}

	@Override
	public Long insert(Airport airport) {
		return airportDao.insert(airport);
	}
}
