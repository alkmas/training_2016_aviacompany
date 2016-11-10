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

import com.epam.training2016.aviacompany.datamodel.FlightDays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class FlightDaysServiceTest {
	private FlightDays flightDayWeek;
	@Inject
	private BaseService<FlightDays> flightDaysService;

	private void init(Long flightId, Long dayWeek) {
		flightDayWeek = new FlightDays();
		flightDayWeek.setFlightId(flightId);
		flightDayWeek.setDayWeek(dayWeek);
		flightDaysService.save(flightDayWeek);
	}

	private void close() {
		flightDaysService.delete(flightDayWeek);
	}

	@Test(expected = DuplicateKeyException.class)
	public void insertDuplicateRecordTest() {
		init(1L, 0L);
		flightDaysService.save(flightDayWeek);
		close();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getByNameExceptionTest() {
		List<FlightDays> fDW = flightDaysService.getByFlightId(500L);
		fDW.get(0);
	}
	
	@Test
	public void insertForFlightListDaysWeekTest() {
		Long flightId = 100L;
		flightDaysService.deleteByFlightId(flightId);
		
		List<Long> daysWeek = new ArrayList<Long>();
		Collections.addAll(daysWeek, 0L,2L,4L,6L);
		flightDaysService.saveDaysWeekForFlight(flightId, daysWeek);
		
		List<FlightDays> flightDaysWeekFromBase = flightDaysService.getByFlightId(flightId);
		Assert.assertEquals(flightDaysService.getDaysFromList(flightDaysWeekFromBase), daysWeek);
	}
}
