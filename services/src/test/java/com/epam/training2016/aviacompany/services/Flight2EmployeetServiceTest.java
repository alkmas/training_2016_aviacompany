package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class Flight2EmployeetServiceTest {
    @Inject
    private Flight2EmployeeService flight2EmployeeService;
    @Inject
    private FlightService flightService;
    @Inject
    private EmployeeService employeeService;
	@Inject
	private JobTitleDaoImpl jobtitleDao;
    
    
	@Test(expected = EmptyResultDataAccessException.class)
    public void getByIdExceptiontest() {
    	Flight2Employee flight2Employee = flight2EmployeeService.getById(1000L);
        Assert.assertNotNull("The object isn't null", flight2Employee);
        Assert.assertEquals(new Long(1000L), flight2Employee.getId());
    }
    
	
    @Test
    @Ignore
    public void createTeamTest() throws InvalidAttributeValueException {
		Date flightDate = Date.valueOf("2016-12-18");
		Long flightId = flightService.getAllByDate(flightDate).get(0).getFlight().getId();
		// Удаляем старую бригаду
		flight2EmployeeService.deleteTeam(flightId, flightDate);
		// Назначаем новую бригаду
		flight2EmployeeService.createTeam(flightId, flightDate, 2, 1, 1, 3);
		// Распечатать новую бригаду
		for(Flight2Employee f2e: flight2EmployeeService.getTeam(flightId, flightDate)) {
			System.out.println(f2e);
		};
    }

    @Test
    public void createTeamsTest() throws InvalidAttributeValueException {
		Date flightDate = Date.valueOf("2016-12-18");
		for(FlightWithAirport flightWithAirport: flightService.getAllByDate(flightDate)) {
			// Удаляем старую бригаду
			flight2EmployeeService.deleteTeam(flightWithAirport.getFlight().getId(), flightDate);
			// Назначаем новую бригаду
			try{
				flight2EmployeeService.createTeam(flightWithAirport.getFlight().getId(), flightDate, 2, 1, 1, 3);
			} catch (EmptyResultDataAccessException e) {
				
			}
			
		};
    }
}
