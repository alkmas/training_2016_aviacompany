package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-test.xml")
public class AirportServiceTest {
	private Airport airport;
    @Inject
    private BaseService<Airport> airportService;

	@Before
	public void init() throws InvalidAttributeValueException, InvalidDataException {
		airport = new Airport();
		airport.setName("Варшава");
		airportService.save(airport);
	}
	
	@After
	public void close() {
		airportService.deleteById(airport.getId());
	}

	
	@Test
    public void airportNotExistTest() {
		Airport airport = airportService.getByName("ВАРШАВА");
		Assert.assertNull(airport);
    }

    @Test
    public void getByNameTest() {
    	Airport airport = airportService.getById(1L);
    	System.out.println(airport);
        Assert.assertNotNull("airport for id=1L should not be null", airport);
        Assert.assertEquals(new Long(1L), airport.getId());
        System.out.println(airport.getName());
    }
    
    
    
}
