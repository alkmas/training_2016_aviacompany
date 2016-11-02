package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
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
    
    @Test
    public void getByIdtest() {
    	Flight2Employee flight2Employee = flight2EmployeeService.getById(1L);
        Assert.assertNotNull("", flight2Employee);
        Assert.assertEquals(new Long(1L), flight2Employee.getId());
    }
    
    
    @Test
    public void createTeamTest() {
    	//--------ВЫБИРАЕМ РЕЙСЫ ПО РАСПИСАНИЮ НА ДАТУ------
    	Date dateFlight = Date.valueOf("2016-11-31");
    	List<FlightWithAirport> flights = flightService.getAllByAway(dateFlight);
    	System.out.println(String.format("Рейсы на дату: %s. Количество: %d", dateFlight, flights.size()));
    	for(FlightWithAirport flight: flights) {
        	System.out.println(flight);
    	}
    	
    	//-------ВЫБИРАЕМ СВОБОДНОГО ПИЛОТА--------
    	System.out.println("Список пилотов");
    	Flight2Employee filterF2E = new Flight2Employee();
    	List<Employee> pilots = employeeService.getByJobTitleName("Пилот");
    	Employee pilotFree = null;
    	for(Employee pilot: pilots) {
    		filterF2E.setEmployeeId(pilot.getId());
    		filterF2E.setDeparture(dateFlight);
    		if (flight2EmployeeService.filter(filterF2E).size()==0) {
            	pilotFree = pilot;
            	break;
    		}
    	}
    	System.out.println(pilotFree);  
    	
    	//-------НАЗНАЧАЕМ ПИЛОТА НА РЕЙС--------
    	Flight2Employee newF2E = new Flight2Employee();
    	newF2E.setFlightId(flights.get(0).getFlight().getId());
    	newF2E.setEmployeeId(pilotFree.getId());
    	newF2E.setDeparture(dateFlight);
    	flight2EmployeeService.save(newF2E);
    }

}
