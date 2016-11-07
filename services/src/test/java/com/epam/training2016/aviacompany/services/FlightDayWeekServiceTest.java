package com.epam.training2016.aviacompany.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightDayWeekServiceTest {
	private FlightDayWeek flightDayWeek;
	@Inject
	private FlightDayWeekService flightDayWeekService;

	private void init(Long flightId, Long dayWeek) {
		flightDayWeek = new FlightDayWeek();
		flightDayWeek.setFlightId(flightId);
		flightDayWeek.setDayWeek(dayWeek);
		flightDayWeekService.save(flightDayWeek);
	}

	private void close() {
		flightDayWeekService.delete(flightDayWeek);
	}

	@Test(expected = DuplicateKeyException.class)
	public void insertDuplicateRecordTest() {
		init(1L, 0L);
		flightDayWeekService.save(flightDayWeek);
		close();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getByNameExceptionTest() {
		List<FlightDayWeek> fDW = flightDayWeekService.getByFlightId(500L);
		fDW.get(0);
	}
	
	@Test
	public void insertForFlightListDaysWeekTest() {
		Long flightId = 100L;
		flightDayWeekService.deleteByFlightId(flightId);
		
		List<Long> daysWeek = new ArrayList<Long>();
		Collections.addAll(daysWeek, 0L,2L,4L,6L);
		flightDayWeekService.saveDaysWeekForFlight(flightId, daysWeek);
		
		List<FlightDayWeek> flightDaysWeekFromBase = flightDayWeekService.getByFlightId(flightId);
		Assert.assertEquals(flightDayWeekService.getDaysFromList(flightDaysWeekFromBase), daysWeek);
	}
}
