package com.epam.training2016.aviacompany.services.utils;

import java.util.Comparator;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public class SortedByDeparture<T extends Flight2Employee> implements Comparator<T> {

	@Override
	public int compare(Flight2Employee o1, Flight2Employee o2) {
		if (o1.getDeparture().getTime() > o2.getDeparture().getTime()) {
			return 1;
		}
		if (o1.getDeparture().getTime() < o2.getDeparture().getTime()) {
			return -1;
		}
		return 0;
	}

}
