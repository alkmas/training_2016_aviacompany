package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;


public final class FlightWithFullInfoMapper implements
        RowMapper<FlightWithAirportsAndDaysWeek> {
    @Override
    public FlightWithAirportsAndDaysWeek mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Flight flight = new Flight();
    	flight.setId(rs.getLong("id"));
    	flight.setName(rs.getString("name"));
    	flight.setAirportSrcId(rs.getLong("airport_src_id"));
    	flight.setAirportDstId(rs.getLong("airport_dst_id"));
    	flight.setDepartureTime(rs.getTime("departure_time"));
    	flight.setArrivalTime(rs.getTime("arrival_time"));

    	FlightDays days = new FlightDays();
    	days.setId(rs.getLong("id"));
		for(int i=1; i<=7; i++) {
			days.setDay(i, rs.getBoolean("day"+i));
		}

		Airport srcAirport = new Airport();
		srcAirport.setId(rs.getLong("airport_src_id"));
		srcAirport.setName(rs.getString("src_name"));
		
		Airport dstAirport = new Airport();
		dstAirport.setId(rs.getLong("airport_dst_id"));
		dstAirport.setName(rs.getString("dst_name"));
		
    	FlightWithAirportsAndDaysWeek flightWithFullInfo = new FlightWithAirportsAndDaysWeek();
    	flightWithFullInfo.setFlight(flight);
    	flightWithFullInfo.setAirportSrc(srcAirport);
    	flightWithFullInfo.setAirportDst(dstAirport);
    	flightWithFullInfo.setDays(days);
    	
        return flightWithFullInfo;
    }
}