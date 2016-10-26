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
    private AirportService airportService;

    @Test
    public void getByIdtest() {
    	Airport airport = airportService.get(1L);
        Assert.assertNotNull("airport for id=1 should not be null", airport);
        Assert.assertEquals(new Long(1L), airport.getId());
        System.out.println(airport.getName());
    }

}
