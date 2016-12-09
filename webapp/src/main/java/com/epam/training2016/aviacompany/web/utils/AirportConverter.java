package com.epam.training2016.aviacompany.web.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.services.impl.AirportServiceImpl;
import com.epam.training2016.aviacompany.web.components.UserDataStorage;
import com.epam.training2016.aviacompany.web.model.AirportModel;

@Service
public class AirportConverter extends BaseConverter<Airport, AirportModel>{
    
	public AirportConverter() {
		super("airport");
	}
	

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

	
	private String convert(String value) {
		return resource.getString(value);
	}
}
