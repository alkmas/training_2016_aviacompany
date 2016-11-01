package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl extends BaseDaoImpl<Airport> {
	
	AirportDaoImpl() {
		super(Airport.class, "airport");
	}

	public static void main(String[] args) {
		AirportDaoImpl airport = new AirportDaoImpl();
		airport.getById(1L);
		
	}
}
