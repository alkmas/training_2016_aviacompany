package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.datamodel.Team;


public final class Flight2TeamJoinMapper implements
        RowMapper<Flight2TeamJoin> {
    @Override
    public Flight2TeamJoin mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Flight flight = new Flight();
    	flight.setId(rs.getLong("flight_id"));
    	flight.setName(rs.getString("name"));
    	flight.setAirportSrcId(rs.getLong("airport_src_id"));
    	flight.setAirportDstId(rs.getLong("airport_dst_id"));
    	flight.setDepartureTime(rs.getTime("departure_time"));
    	flight.setArrivalTime(rs.getTime("arrival_time"));

    	Team team = new Team();
    	team.setId(rs.getLong("team_id"));
    	team.setPilot(rs.getLong("pilot"));
    	team.setNavigator(rs.getLong("navigator"));
    	team.setRadioman(rs.getLong("radioman"));
    	team.setStewardess1(rs.getLong("stewardess1"));
    	team.setStewardess2(rs.getLong("stewardess2"));
		
		Flight2TeamJoin f2tJoin = new Flight2TeamJoin();
		f2tJoin.setId(rs.getLong("id"));
		f2tJoin.setFlight(flight);
		f2tJoin.setTeam(team);
		f2tJoin.setDeparture(rs.getDate("departure"));
   	
        return f2tJoin;
    }
}