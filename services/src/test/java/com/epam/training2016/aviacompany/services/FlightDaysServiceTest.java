package com.epam.training2016.aviacompany.services;

import java.util.Calendar;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightDaysServiceTest {
	@Inject
	private BaseService<FlightDays> flightDaysService;

	@Test
	public void getDayTest() throws InvalidDataException {
		FlightDays flightDays = new FlightDays();
		flightDays.setId(1L);
		flightDays.setDay(Calendar.SUNDAY, true);
		flightDaysService.save(flightDays);
		Assert.assertEquals(flightDaysService.getById(1L).getDay(Calendar.SUNDAY), true);
	}

	@Test
	public void deleteDayTest() throws InvalidDataException {
		flightDaysService.deleteById(1L);
		FlightDays flightDays = flightDaysService.getById(1L);
		Assert.assertNull(flightDays);
	}

}
