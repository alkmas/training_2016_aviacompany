package com.epam.training2016.aviacompany.daodb;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public interface Flight2TeamDao extends BaseDao<Flight2Team> {
	List<Flight2Team> getByFlightId(Long flightId);
	List<Flight2Team> getByTeamId(Long teamId);
	Flight2Team getByTeamIdAndDate(Long teamId, Date date);
	void deleteByFlightIdAndDate(Long flightId, Date date);
	void deleteByTeamId(Long teamId);
}
