package com.epam.training2016.aviacompany.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirportAndDaysWeek;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class Flight2EmployeetServiceTest {
    @Inject
    private BaseService<Flight2Employee> flight2EmployeeService;
    
    @Test
    public void getByIdtest() {
    	Flight2Employee flight2Employee = flight2EmployeeService.get(1L);
        Assert.assertNotNull("", flight2Employee);
        Assert.assertEquals(new Long(1L), flight2Employee.getId());
//      System.out.println(flight2Employee.getName());
    }
    
    
    @Test
    public void insertTest() {
//    	Flight2Employee flight2Employee = new Flight2Employee();
    	Flight2Employee flight2Employee = flight2EmployeeService.get(1L);
    	flight2Employee.setFlightId(2L);
    	flight2Employee.setEmployeeId(6L);
    	flight2Employee.setDeparture(new Date());
      	flight2EmployeeService.save(flight2Employee);
        Assert.assertNotNull("airport for id=1 should not be null", flight2Employee);
        Assert.assertEquals(new Long(1L), flight2Employee.getId());
    }

}
