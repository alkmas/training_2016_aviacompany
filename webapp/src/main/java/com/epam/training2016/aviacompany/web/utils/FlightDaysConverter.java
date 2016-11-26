package com.epam.training2016.aviacompany.web.utils;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.web.model.FlightDaysModel;

@Service
public class FlightDaysConverter extends BaseConverter<FlightDays, FlightDaysModel>{

	@Override
	public FlightDaysModel entity2model(FlightDays entity) {
		FlightDaysModel model = new FlightDaysModel();
		model.setDays(entity.getDays());
		return model;
	}

	@Override
	public FlightDays model2entity(FlightDaysModel model) {
		FlightDays entity = new FlightDays();
		entity.setDays(model.getDays());
		return entity;
	}

}
