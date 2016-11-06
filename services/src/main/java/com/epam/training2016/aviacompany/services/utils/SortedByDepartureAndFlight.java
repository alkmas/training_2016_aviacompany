package com.epam.training2016.aviacompany.services.utils;

import java.util.Comparator;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public class SortedByDepartureAndFlight<T extends Flight2Employee> implements Comparator<T>{

	@Override
	public int compare(Flight2Employee o1, Flight2Employee o2) {
		int sortByDeparture = new SortedByDeparture<Flight2Employee>().compare(o1, o2);
		if (sortByDeparture == 0) {
			return new SortByFlight<>().compare(o1, o2);
		}
		return sortByDeparture;
	}

}
