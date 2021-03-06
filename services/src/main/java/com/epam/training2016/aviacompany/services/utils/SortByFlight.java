package com.epam.training2016.aviacompany.services.utils;

import java.util.Comparator;

import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public class SortByFlight<T extends Flight2Team> implements Comparator<T> {

	@Override
	public int compare(Flight2Team o1, Flight2Team o2) {
		if (o1.getFlightId() > o1.getFlightId()) {
			return 1;
		}
		if (o1.getFlightId() < o1.getFlightId()) {
			return -1;
		}
		return 0;
	}

}
