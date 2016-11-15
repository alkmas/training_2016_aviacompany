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
    @Inject
    private EmployeeService employeeService;

    
    
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
    	FlightWithAirport flight = flightService.getAllByDateWithoutTeam(testDate).get(0);
    	Team team = teamService.getAll().get(0);
    	Flight2Team f2t = new Flight2Team();
    	f2t.setFlightId(flight.getFlight().getId());
    	f2t.setTeamId(team.getId());
    	f2t.setDeparture(testDate);
    	flight2TeamService.save(f2t);
    	
    	Assert.assertNotNull(flight2TeamService.getById(f2t.getId()));
    	
    	flight2TeamService.deleteById(f2t.getId());
    	Assert.assertNull(flight2TeamService.getById(f2t.getId()));
    	
    }
}
