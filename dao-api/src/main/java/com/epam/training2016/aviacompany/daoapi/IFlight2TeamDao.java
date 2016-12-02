package com.epam.training2016.aviacompany.daoapi;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public interface IFlight2TeamDao extends IBaseDao<Flight2Team> {
	List<Flight2Team> getByFlightId(Long flightId);
	List<Flight2Team> getByTeamId(Long teamId);
	List<Flight2Team> getByDeparture(Date date);
	Flight2Team getByTeamIdAndDate(Long teamId, Date date);
	int deleteByFlightIdAndDate(Long flightId, Date date);
	int deleteByTeamId(Long teamId);
	List<Flight2TeamJoin> getAllJoin();
}
