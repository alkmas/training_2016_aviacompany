package com.epam.training2016.aviacompany.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirportAndDaysWeek;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightServiceTest {
    @Inject
    private FlightService flightService;
    
    @Test
    public void getByIdtest() {
    	Flight flight = flightService.get(1L);
        Assert.assertNotNull("flight for id=1 should not be null", flight);
        Assert.assertEquals(new Long(1L), flight.getId());
        System.out.println(flight.getName());
    }

    
    @Test
    public void insertIntoAirport() {
    	Flight flight = new Flight();
    	flight.setName("D11111");
    	Long id = flightService.insert(flight);
    	Assert.assertNotNull("id should not be 0", id);
   }



    @Test
    public void selectAllFlight() {
    	List<FlightWithAirportAndDaysWeek> flights =
    			flightService.getAllFlight();
    	Assert.assertNotNull(flights);
    	System.out.println(flights);
    }
}
