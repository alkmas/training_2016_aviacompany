package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public class FlightDaoImpl extends BaseDaoImpl<Flight> {

	FlightDaoImpl() {
		super(Flight.class);
	}
}
