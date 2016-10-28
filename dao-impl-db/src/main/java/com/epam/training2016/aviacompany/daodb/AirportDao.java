package com.epam.training2016.aviacompany.daodb;

import com.epam.training2016.aviacompany.datamodel.Airport;

public interface AirportDao extends BaseDao<Airport> {
	Airport getFlightWithAirport(Long id);
}
