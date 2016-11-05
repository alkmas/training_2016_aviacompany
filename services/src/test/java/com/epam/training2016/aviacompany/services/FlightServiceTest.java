package com.epam.training2016.aviacompany.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightServiceTest {
    @Inject
    private FlightService flightService;
    
    @Test
    public void getByIdtest() {
    	Flight flight = flightService.getById(1L);
        Assert.assertNotNull("flight for id=1 should not be null", flight);
        Assert.assertEquals(new Long(1L), flight.getId());
        System.out.println(flight.getName());
    }

    
    @Test
    public void insertIntoFlight() throws InvalidAttributeValueException {
    	Flight flight = new Flight();
    	flight.setName("D11111");
    	flightService.save(flight);
    	Assert.assertNotNull("id should not be 0", flight.getId());
   }


    @Test
    public void getFlight() {
    	// ------------ПОЛУЧИТЬ ВСЕ РЕЙСЫ НА СЕГОДНЯ----------------
    	Date date = new Date();
    	List<FlightWithAirport> flights = 
    			flightService.getAllByDate(date);
    	Assert.assertNotNull(flights);
    	System.out.println(date);
    	for(FlightWithAirport flight: flights) {
        	System.out.println(flight);    		
    	}

    }
}
