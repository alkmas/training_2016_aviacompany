package com.epam.training2016.aviacompany.web.utils;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.web.model.AirportModel;

public class AirportConverter extends BaseConverter<Airport, AirportModel>{

	public AirportConverter(Class<Airport> typeE, Class<AirportModel> typeM) {
		super(typeE, typeM);
	}

}
