package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl extends BaseDaoImpl<Airport>{
	
	AirportDaoImpl() {
		super(Airport.class);
	}

	public static void main(String[] args) {
		BaseDaoImpl<Airport> airport = new AirportDaoImpl();
		airport.get(1L);
		
	}
}
