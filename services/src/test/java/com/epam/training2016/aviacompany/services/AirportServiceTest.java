package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Airport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AirportServiceTest {
	private Airport airport;
    @Inject
    private BaseService<Airport> airportService;

	@Before
	public void init() {
		airport = new Airport();
		airport.setName("Варшава");
		airportService.save(airport);
	}
	
	@After
	public void close() {
		airportService.deleteById(airport.getId());
	}

	@Test(expected = EmptyResultDataAccessException.class)
    public void getByNameExceptionTest() {
		airport = airportService.getByName("ВАРШАВА");
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
