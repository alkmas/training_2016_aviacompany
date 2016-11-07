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

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.services.utils.SortedByDepartureAndFlight;
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

    
    
	@Test(expected = EmptyResultDataAccessException.class)
    public void getByIdExceptiontest() {
    	Flight2Employee flight2Employee = flight2EmployeeService.getById(1000L);
        Assert.assertNotNull("The object isn't null", flight2Employee);
        Assert.assertEquals(new Long(1000L), flight2Employee.getId());
    }
    
	@Test(expected = InvalidAttributeValueException.class)
	public void insertEmployeeForFlightExceptionTest() throws InvalidAttributeValueException {
		System.out.println("-----------insertEmployeeForFlightExceptionTest-------------");
		Date flightDate = Date.valueOf("2016-12-18");
		Flight2Employee f2e = new Flight2Employee();
		f2e.setFlightId(1L);
		f2e.setEmployeeId(1L);
		f2e.setDeparture(flightDate);
		flight2EmployeeService.save(f2e);
	}

	
	@Test
	public void insertRadiomanForFlightTest() throws InvalidAttributeValueException {
		System.out.println("-----------insertEmployeeForFlightTest-------------");
		Date flightDate = Date.valueOf("2016-12-18");
		// Получить рейсы на дату без Радиста
		List<Flight> flights = flight2EmployeeService.getFlightsWithoutJobtitleByDate("Радист", flightDate);
		Flight2Employee f2e = new Flight2Employee();
		f2e.setFlightId(flights.get(0).getId());
		f2e.setEmployeeId(employeeService.getByJobTitleName("Радист").get(0).getId());
		f2e.setDeparture(flightDate);
		flight2EmployeeService.save(f2e);
		Assert.assertNotNull(flight2EmployeeService.getById(f2e.getId()));
		System.out.println("На рейс " + f2e + " назначен:");
		System.out.println(employeeService.getById(f2e.getEmployeeId()));
	}

    @Test
    public void createTeamTest() throws InvalidAttributeValueException {
		System.out.println("-----------createTeamTest-------------");
		Date flightDate = Date.valueOf("2016-12-18");
		Long flightId = flightService.getAllByDate(flightDate).get(0).getFlight().getId();
		// Удаляем старую бригаду
		flight2EmployeeService.deleteTeam(flightId, flightDate);
		// Назначаем новую бригаду
		flight2EmployeeService.createTeamAndSave(flightId, flightDate, 2, 1, 0, 3);
		// Распечатать новую бригаду
		for(Flight2Employee f2e: flight2EmployeeService.getTeam(flightId, flightDate)) {
			System.out.println(f2e);
		};
    }

    @Test
    public void createTeamsTest() throws InvalidAttributeValueException {
		System.out.println("-----------createTeamsTest-------------");
		Date flightDate = Date.valueOf("2016-12-18");
		for(FlightWithAirport flightWithAirport: flightService.getAllByDate(flightDate)) {
			// Удаляем старую бригаду
			flight2EmployeeService.deleteTeam(flightWithAirport.getFlight().getId(), flightDate);
			// Назначаем новую бригаду
			try{
				flight2EmployeeService.createTeamAndSave(flightWithAirport.getFlight().getId(), flightDate, 2, 1, 0, 3);
			} catch (EmptyResultDataAccessException e) {
				
			}
			
		};
    }
    
    @Test
    public void getTeamsByDateTest() {
		System.out.println("-----------getTeamsByDateTest-------------");
    	Date flightDate = Date.valueOf("2016-12-18");
    	List<Flight2Employee> f2eList =	flight2EmployeeService.getByDeparture(flightDate);
    	f2eList.sort(new SortedByDepartureAndFlight<Flight2Employee>());
    	for(Flight2Employee f2e: f2eList) {
    		System.out.println(f2e);
    	}
    }
}
