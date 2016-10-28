package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.training2016.aviacompany.datamodel.Airport;

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
    	String name= "Гродно";
    	airport.setName(name);
    	Long id = airportService.insert(airport);
    	Assert.assertNotNull("id should not be 0", id);
    	Assert.assertEquals(name, airportService.get(id).getName());
    }

    
    @Test
    public void saveAirport() {
    	Long id = 1L;
    	String name= "Москва";
    	Airport airport = airportService.get(id);
    	airport.setName(name);
    	airportService.save(airport);
    	Assert.assertNotNull("airport for id=1 should not be null", airport);
    	Assert.assertEquals(name, airportService.get(id).getName());
    }

}
