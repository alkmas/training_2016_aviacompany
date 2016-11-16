package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirport;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;

public final class FlightWithEmployeeMapper implements
        RowMapper<FlightWithAirport> {
    @Override
    public FlightWithAirport mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Flight flight = new Flight();
    	flight.setId(rs.getLong(1));
    	flight.setName(rs.getString(2));
    	flight.setAirportSrcId(rs.getLong(3));
    	flight.setAirportDstId(rs.getLong(4));
    	flight.setDepartureTime(rs.getTime(5));
    	flight.setArrivalTime(rs.getTime(6));
    	
    	Airport airportSrc = new Airport();
    	airportSrc.setId(rs.getLong(7));
    	airportSrc.setName(rs.getString(8));

        Airport airportDst = new Airport();
        airportDst.setId(rs.getLong(9));
        airportDst.setName(rs.getString(10));
        
        FlightWithAirport flightWithAirport = new FlightWithAirport();
        flightWithAirport.setFlight(flight);
        flightWithAirport.setAirportSrc(airportSrc);
        flightWithAirport.setAirportDst(airportDst);
        return flightWithAirport;
    }
}