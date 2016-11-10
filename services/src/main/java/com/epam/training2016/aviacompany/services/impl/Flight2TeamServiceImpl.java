package com.epam.training2016.aviacompany.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.Flight2TeamDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@Service
public class Flight2TeamServiceImpl extends BaseServiceImpl<Flight2Team> implements Flight2TeamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Flight2TeamServiceImpl.class);

	@Inject
	private Flight2TeamDaoImpl flight2TeamDao;
	@Inject
	private FlightService flightService;
	@Inject
	private EmployeeService employeeService;
	@Inject
	private BaseServiceImpl<JobTitle> jobtitleService;

	@Override
	public void save(Flight2Team entity) throws InvalidAttributeValueException {
		// Проверка на существование на дату
		// т.к. сам рейс может существовать (связь по ключу в БД), но в данный день недели отсутствовать
		if (!flightService.isFlightExistByDate(entity.getFlightId(), entity.getDeparture())) {
			throw new InvalidAttributeValueException(
					String.format("Not exist flight(%d) by date(%s)", 
							entity.getFlightId(), entity.getDeparture()));
		};
		// ---
		if (entity.getId() == null) {
			entity.setId(flight2TeamDao.insert(entity));
		} else {
			flight2TeamDao.update(entity);
		}
	}

	
	@Override
	public Flight2Team getByEmployeeIdAndDate(Long employeeId, Date date) {
		try {
			return flight2TeamDao.getByTeamIdAndDate(employeeId, date);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Flight2Team> getByFlightId(Long id) {
		return flight2TeamDao.getByFlightId(id);
	}

	@Override
	public List<Flight2Team> getByEmployeeId(Long id) {
		return flight2TeamDao.getByTeamId(id);
	}

	@Override
	public List<Flight2Team> getByDeparture(Date dt) {
		List<Flight2Team> resultList = new ArrayList<Flight2Team>();
		for(Flight2Team f2e: flight2TeamDao.getAll()){
			if (f2e.getDeparture().getTime() == dt.getTime()) {
				resultList.add(f2e);
			}
		}
		return resultList;
	}

	
	@Override
	public void deleteByEmployeeId(Long employeeId) {
		flight2TeamDao.deleteByTeamId(employeeId);
		LOGGER.info(String.format("Deleted (%s) from table Flight2Employee", employeeId));
	}

	@Override
	@Transactional
	public void deleteByFlightId(Long flightId) {
		for(Flight2Team f2e: flight2TeamDao.getByFlightId(flightId)) {
			deleteById(f2e.getId());
		}
	}
	
	@Override	
	public boolean addInListFreeEmployeesForDate(List<Flight2Team> team, String nameJobTitle, 
			Long flightId, Date date, int count) {
		if (count==0) return false;
		for(Employee emp: employeeService.getByJobTitleName(nameJobTitle)) {
			if (this.getByEmployeeIdAndDate(emp.getId(), date) == null) {
				// Сотрудник emp свободен на дату date
				Flight2Team newMember = new Flight2Team();
				newMember.setFlightId(flightId);
				newMember.setTeamId(emp.getId());
				newMember.setDeparture(date);
				team.add(newMember);
				if (--count == 0) break;
			}
		};
		if (count != 0) {
			LOGGER.error(String.format(
					"Team for flight(id=%s) and date(%s) not forming, not enough people. Not enough jobtitle(%s) ",
					flightId, date, nameJobTitle));
			throw new EmptyResultDataAccessException(1);
		}
		return true;
	}
	
	@Override
	@Transactional
	public void createTeamAndSave(Long flightId, Date date, 
			int countPilot, int countNavigator, int countRadioman, int countStewardess) throws InvalidAttributeValueException {
		List<Flight2Team> team = new ArrayList<Flight2Team>();
		addInListFreeEmployeesForDate(team, "Пилот", flightId, date, countPilot);
		addInListFreeEmployeesForDate(team, "Штурман", flightId, date, countNavigator);
		addInListFreeEmployeesForDate(team, "Радист", flightId, date, countRadioman);
		addInListFreeEmployeesForDate(team, "Стюардесса", flightId, date, countStewardess);
		saveAll(team);
	}

	@Override
	public void deleteTeam(Long flightId, Date date) {
		flight2TeamDao.deleteByFlightIdAndDate(flightId, date);
	}

	@Override
	public List<Flight2Team> getTeam(Long flightId, Date date) {
		List<Flight2Team> result = new ArrayList<Flight2Team>();
		for(Flight2Team f2e: getByFlightId(flightId)) {
			if (f2e.getDeparture().getTime() == date.getTime()) result.add(f2e);
		}
		return result;
	}

	@Override
	public List<Flight> getFlightsWithoutJobtitleByDate(String nameJobTitle, Date date) {
		List<Flight> flights = new ArrayList<Flight>();
		for(FlightWithAirport flight: flightService.getAllByDate(date)) {
			if (isJobTitleInFlightByDate(flight.getFlight().getId(), nameJobTitle, date)) {
				continue;
			} else {
				flights.add(flight.getFlight());
			}
		}
		return flights;
	}

	@Override
	public boolean isJobTitleInFlightByDate(Long flightId, String nameJobTitle, Date date) {
		JobTitle jobtitle = jobtitleService.getByName(nameJobTitle);
		for(Flight2Team f2e: getTeam(flightId, date)) {
			if (employeeService.getById(f2e.getTeamId()).getJobTitleId() == jobtitle.getId()) {
				return true;
			};
		}
		return false;
	}



}
