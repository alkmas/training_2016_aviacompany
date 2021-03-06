package com.epam.training2016.aviacompany.daoxml.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IFlight2TeamDao;
import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.daoxml.utils.BaseXML;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

@Repository
public class Flight2TeamDaoXmlImpl extends BaseDaoXmlImpl<Flight2Team> implements IFlight2TeamDao{
	
	// Получить список объектов 
	private List<Flight2Team> getByFieldLong(String fieldName, Long value) {
		return getFromListByFieldLong(this.getAll(), fieldName, value);
	}

	
	// Получить список объектов из списка
	private List<Flight2Team> getFromListByFieldLong(List<Flight2Team> inList, String fieldName, Long value) {
		List<Flight2Team> listResult = new ArrayList<>();
		for(Flight2Team f2t: inList) {
			if ((Long)baseXML.getValueField(f2t, fieldName) == value) {
				listResult.add(f2t);
			}
		}
		return listResult;
	}
	
	
	
	@Override
	public List<Flight2Team> getByFlightId(Long flightId) {
		return getByFieldLong("flightId", flightId);
	}

	@Override
	public List<Flight2Team> getByTeamId(Long teamId) {
		return getByFieldLong("teamId", teamId);
	}

	@Override
	public List<Flight2Team> getByDeparture(Date date) {
		return getByFieldLong("departure", date.getTime());
	}

	@Override
	public Flight2Team getByTeamIdAndDate(Long teamId, Date date) {
		List<Flight2Team> f2tList = getFromListByFieldLong(getByTeamId(teamId), "departure", date.getTime());
		if (f2tList.isEmpty()) return null;
		return f2tList.get(0);
	}

	@Override
	public int deleteByFlightIdAndDate(Long flightId, Date date) {
		int countDeleted = 0;
		for(Flight2Team f2t: getFromListByFieldLong(getByTeamId(flightId), "departure", date.getTime())) {
			countDeleted += this.deleteById(f2t.getId());
		}
		return countDeleted;
	}

	@Override
	public int deleteByTeamId(Long teamId) {
		return baseXML.deleteByField("teamId", teamId);
	}


	@Override
	public Class<Flight2Team> getGenericType() {
		return Flight2Team.class;
	}


	@Override
	public List<Flight2TeamJoin> getAllJoin() {
		throw new UnsupportedOperationException();
	}


}
