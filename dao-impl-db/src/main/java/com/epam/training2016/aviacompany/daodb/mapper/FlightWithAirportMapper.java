package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

public final class FlightWithAirportMapper implements
        RowMapper<FlightWithAirport> {
    @Override
    public FlightWithAirport mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Flight flight = new Flight();
    	flight.setId(rs.getLong("id"));
    	flight.setName(rs.getString("name"));
    	flight.setAirportSrcId(rs.getLong("airport_src_id"));
    	flight.setAirportDstId(rs.getLong("airport_dst_id"));
    	flight.setDepartureTime(rs.getTime("departure_time"));
    	flight.setArrivalTime(rs.getTime("arrival_time"));
    	
    	Airport airportSrc = new Airport();
    	airportSrc.setId(rs.getLong("airport_src_id"));
    	airportSrc.setName(rs.getString("a_src_name"));

        Airport airportDst = new Airport();
        airportDst.setId(rs.getLong("airport_dst_id"));
        airportDst.setName(rs.getString("a_dst_name"));
        
        FlightWithAirport flightWithAirport = new FlightWithAirport();
        flightWithAirport.setFlight(flight);
        flightWithAirport.setAirportSrc(airportSrc);
        flightWithAirport.setAirportDst(airportDst);
        return flightWithAirport;
    }
}