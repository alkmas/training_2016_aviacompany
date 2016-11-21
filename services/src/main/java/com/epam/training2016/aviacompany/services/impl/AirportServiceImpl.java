package com.epam.training2016.aviacompany.services.impl;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Airport;


@Service
public class AirportServiceImpl extends BaseServiceImpl<Airport> {

	@Override
	public Class<Airport> getGenericTypeClass() {
		return Airport.class;
	}
}
