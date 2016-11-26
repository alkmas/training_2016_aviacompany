package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public interface Flight2TeamService extends BaseService<Flight2Team>{
	Flight2Team getByTeamIdAndDate(Long teamId, Date date);
	Flight2Team getByFlightIdAndDate(Long flightId, Date date);
	List<Flight2Team> getByFlightId(Long flightId);
	List<Flight2Team> getByTeamId(Long teamId);
	List<Flight2Team> getByDeparture(Date date);
	void deleteByTeamId(Long teamId);
	void deleteByFlightId(Long flightId);
	List<Flight2TeamJoin> getAllJoin();
}
			