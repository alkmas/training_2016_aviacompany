package com.epam.training2016.aviacompany.web.utils;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.web.model.AirportModel;

@Service
public class AirportConverter extends BaseConverter<Airport, AirportModel>{

	@Override
	public AirportModel entity2model(Airport airport) {
		AirportModel airModel = new AirportModel();
		airModel.setId(airport.getId());
		airModel.setName(airport.getName());
		return airModel;
	}

	@Override
	public Airport model2entity(AirportModel model) {
		Airport air = new Airport();
		air.setId(model.getId());
		air.setName(model.getName());
		return air;
	}

}
