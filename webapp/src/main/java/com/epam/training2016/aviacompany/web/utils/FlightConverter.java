package com.epam.training2016.aviacompany.web.utils;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.web.model.AirportModel;
import com.epam.training2016.aviacompany.web.model.EmployeeModel;
import com.epam.training2016.aviacompany.web.model.FlightModel;

@Service
public class FlightConverter extends BaseConverter<Flight, FlightModel>{

	@Override
	public FlightModel entity2model(Flight entity) {
		FlightModel model = new FlightModel();
		model.setName(entity.getName());
		model.setDepartureTime(entity.getDepartureTime());
		model.setArrivalTime(entity.getArrivalTime());
		model.setAirportSrcId(entity.getAirportSrcId());
		model.setAirportDstId(entity.getAirportDstId());
		return model;
	}

	@Override
	public Flight model2entity(FlightModel model) {
		Flight entity = new Flight();
		entity.setName(model.getName());
		entity.setDepartureTime(model.getDepartureTime());
		entity.setArrivalTime(model.getArrivalTime());
		entity.setAirportSrcId(model.getAirportSrcId());
		entity.setAirportDstId(model.getAirportDstId());
		return entity;
	}
}
