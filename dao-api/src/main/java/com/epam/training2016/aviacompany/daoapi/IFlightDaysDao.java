package com.epam.training2016.aviacompany.daoapi;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.datamodel.Team;

public interface IFlightDaysDao extends IBaseDao<FlightDays> {

	List<FlightDays> getAllByDay(Integer day);
}
