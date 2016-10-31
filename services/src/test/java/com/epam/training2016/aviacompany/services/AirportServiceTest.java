package com.epam.training2016.aviacompany.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirportAndDaysWeek;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AirportServiceTest {
    @Inject
    private BaseService<Airport> airportService;
    
    @Test
    public void getByIdtest() {
    	Airport airport = airportService.get(1L);
        Assert.assertNotNull("airport for id=1 should not be null", airport);
        Assert.assertEquals(new Long(1L), airport.getId());
        System.out.println(airport.getName());
    }

    
    @Test
    public void insertIntoAirport() {
    	Airport airport = new Airport();
    	airport.setName("Вильнюс");
    	Long id = airportService.save(airport);
    	Assert.assertNotNull("id should not be 0", id);
    	Assert.assertEquals(airport.getName(), airportService.get(id).getName());
    }

    
}
