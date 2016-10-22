package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.epam.training2016.aviacompany.daodb.CommonDao;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl implements CommonDao<Airport> {

	@Override
	public Airport get(Long id) {
        return null; // dbConnectionProvider.getConnection().execute('select *
        // from...')
	}

	@Override
	public void insert(Airport entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Airport entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Airport> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
