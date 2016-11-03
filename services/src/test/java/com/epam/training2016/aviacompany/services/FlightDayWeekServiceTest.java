package com.epam.training2016.aviacompany.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightDayWeekServiceTest {
	private FlightDayWeek flightDayWeek;
	@Inject
	private FlightDayWeekService flightDayWeekService;

	@Before
	public void init() {
		flightDayWeek = new FlightDayWeek();
		flightDayWeek.setFlightId(1L);
		flightDayWeek.setDayWeek(0L);
		flightDayWeekService.save(flightDayWeek);
	}

	@After
	public void close() {
		flightDayWeekService.delete(flightDayWeek);
	}

	@Test(expected = DuplicateKeyException.class)
	public void insertDuplicateRecordTest() {
		flightDayWeekService.save(flightDayWeek);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getByNameExceptionTest() {
		List<FlightDayWeek> fDW = flightDayWeekService.getByFlightId(500L);
		fDW.get(0);
	}
}
