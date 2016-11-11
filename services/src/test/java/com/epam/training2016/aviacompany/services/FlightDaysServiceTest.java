package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.FlightDays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightDaysServiceTest {
	@Inject
	private BaseService<FlightDays> flightDaysService;

	@Test
	public void insertDuplicateRecordTest() {
		FlightDays flightDays = new FlightDays();
		flightDays.setId(1L);
		flightDays.setDay(1, true);
		flightDaysService.save(flightDays);
	}

}
