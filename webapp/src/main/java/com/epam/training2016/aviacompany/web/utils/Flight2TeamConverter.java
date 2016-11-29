package com.epam.training2016.aviacompany.web.utils;

import java.sql.Date;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.web.model.Flight2TeamModel;

@Service
public class Flight2TeamConverter extends BaseConverter<Flight2Team, Flight2TeamModel>{

	@Override
	public Flight2TeamModel entity2model(Flight2Team entity) {
		Flight2TeamModel model = new Flight2TeamModel();
		model.setDeparture(entity.getDeparture().toString());
		model.setFlightId(entity.getFlightId());
		model.setTeamId(entity.getTeamId());

		return model;
	}

	@Override
	public Flight2Team model2entity(Flight2TeamModel model) {
		Flight2Team entity = new Flight2Team();
		entity.setDeparture(Date.valueOf(model.getDeparture()));
		entity.setFlightId(model.getFlightId());
		entity.setTeamId(model.getTeamId());
		return entity;
	}

}
