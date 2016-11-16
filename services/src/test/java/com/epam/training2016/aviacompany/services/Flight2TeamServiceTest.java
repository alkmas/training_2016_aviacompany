package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.services.utils.SortedByDepartureAndFlight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-test.xml")
public class Flight2TeamServiceTest {
    @Inject
    private Flight2TeamService flight2TeamService;
    @Inject
    private TeamService teamService;
    @Inject
    private FlightService flightService;

    
    
    @Test
    public void getTeamsByDateTest() {
		System.out.println("-----------getTeamsByDateTest-------------");
    	Date flightDate = Date.valueOf("2016-12-18");
    	List<Flight2Team> f2tList =	flight2TeamService.getByDeparture(flightDate);
    	f2tList.sort(new SortedByDepartureAndFlight<Flight2Team>());
    	for(Flight2Team f2e: f2tList) {
    		System.out.println(f2e);
    	}
    }
    
    @Test
    public void createTest() throws InvalidDataException {
		System.out.println("-----------createTest-------------");
    	Date testDate = Date.valueOf("2016-11-13");
    	
   		Flight flight = flightService.getAllByDateWithoutTeam(testDate).get(0);
   	
   		Team teamFree = teamService.getAllTeamFreeByDate(testDate).get(0);
    	
    	
   		Flight2Team newF2T = new Flight2Team();
   		newF2T.setFlightId(flight.getId());
   		newF2T.setDeparture(testDate);
   		newF2T.setTeamId(teamFree.getId());
   	
   		flight2TeamService.save(newF2T);
    	
   		Assert.assertNotNull(flight2TeamService.getById(newF2T.getId()));
    	
    	flight2TeamService.deleteById(newF2T.getId());
    	Assert.assertNull(flight2TeamService.getById(newF2T.getId()));
    	
    }
}
