package com.epam.training2016.aviacompany.services.utils;

import java.util.Comparator;

import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public class SortedByDepartureAndFlight<T extends Flight2Team> implements Comparator<T>{

	@Override
	public int compare(Flight2Team o1, Flight2Team o2) {
		int sortByDeparture = new SortedByDeparture<Flight2Team>().compare(o1, o2);
		if (sortByDeparture == 0) {
			return new SortByFlight<>().compare(o1, o2);
		}
		return sortByDeparture;
	}

}
