package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.AirportDao;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl extends BaseDaoImpl<Airport> implements AirportDao{
	
	AirportDaoImpl() {
		super(Airport.class);
	}

	@Override
	public Airport getFlightWithAirport(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args) {
		BaseDaoImpl<Airport> airport = new AirportDaoImpl();
		airport.get(1L);
		
	}

}
