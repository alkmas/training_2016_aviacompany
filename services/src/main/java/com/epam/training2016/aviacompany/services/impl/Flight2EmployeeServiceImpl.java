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

import com.epam.training2016.aviacompany.daodb.impl.Flight2EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.Flight2EmployeeService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.training2016.aviacompany.services.JobTitleService;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@Service
public class Flight2EmployeeServiceImpl implements Flight2EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Flight2EmployeeServiceImpl.class);

	@Inject
	private Flight2EmployeeDaoImpl flight2EmployeeDao;
	@Inject
	private FlightService flightService;
	@Inject
	private EmployeeService employeeService;
	@Inject
	private JobTitleService jobtitleService;

	@Override
	public void saveAll(List<Flight2Employee> entities) throws InvalidAttributeValueException {
		for(Flight2Employee entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(Flight2Employee entity) throws InvalidAttributeValueException {
		if (!flightService.isFlightExistByDate(entity.getFlightId(), entity.getDeparture())) {
			throw new InvalidAttributeValueException(
					String.format("Not exist flight(%d) by date(%s)", 
							entity.getFlightId(), entity.getDeparture()));
		};
		if (entity.getId() == null) {
			entity.setId(flight2EmployeeDao.insert(entity));
		} else {
			flight2EmployeeDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return flight2EmployeeDao != null;
	}

	@Override
	public Flight2Employee getById(Long id) {
		return flight2EmployeeDao.getById(id);
	}

	@Override
	public Flight2Employee getByEmployeeIdAndDate(Long id, Date date) {
		for(Flight2Employee f2e: flight2EmployeeDao.getByEmployeeId(id)) {
			if (f2e.getDeparture().getTime() == date.getTime()) return f2e;
		}
		return null;
	}

	@Override
	public List<Flight2Employee> getAll() {
		return flight2EmployeeDao.getAll();
	}

	@Override
	public List<Flight2Employee> getByFlightId(Long id) {
		return flight2EmployeeDao.getByFlightId(id);
	}

	@Override
	public List<Flight2Employee> getByEmployeeId(Long id) {
		return flight2EmployeeDao.getByEmployeeId(id);
	}

	@Override
	public List<Flight2Employee> getByDeparture(Date dt) {
		List<Flight2Employee> resultList = new ArrayList<Flight2Employee>();
		for(Flight2Employee f2e: flight2EmployeeDao.getAll()){
			if (f2e.getDeparture().getTime() == dt.getTime()) {
				resultList.add(f2e);
			}
		}
		return resultList;
	}

	@Override
	public List<Flight2Employee> filter(Flight2Employee entityFilter) {
		List<Flight2Employee> resultList = new ArrayList<Flight2Employee>(); 
		for(Flight2Employee f2e: flight2EmployeeDao.getAll()) {
			if (f2e.filter(entityFilter)) {
				resultList.add(f2e);
			}
		}
		return resultList;
	}

	@Override
	public void deleteById(Long id) {
		String f2eString = getById(id).toString();
		flight2EmployeeDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Flight2Employee", f2eString));
	}

	
	@Override
	@Transactional
	public void deleteByEmployeeId(Long employeeId) {
		for(Flight2Employee f2e: flight2EmployeeDao.getByEmployeeId(employeeId)) {
			deleteById(f2e.getId());
		}
	}

	@Override
	@Transactional
	public void deleteByFlightId(Long flightId) {
		for(Flight2Employee f2e: flight2EmployeeDao.getByFlightId(flightId)) {
			deleteById(f2e.getId());
		}
	}
	
	@Override	
	public boolean addInListFreeEmployeesForDate(List<Flight2Employee> team, String nameJobTitle, 
			Long flightId, Date date, int count) {
		if (count==0) return false;
		for(Employee emp: employeeService.getByJobTitleName(nameJobTitle)) {
			if (this.getByEmployeeIdAndDate(emp.getId(), date) == null) {
				// Сотрудник emp свободен на дату date
				Flight2Employee newMember = new Flight2Employee();
				newMember.setFlightId(flightId);
				newMember.setEmployeeId(emp.getId());
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
	public void createTeam(Long flightId, Date date, 
			int countPilot, int countNavigator, int countRadioman, int countStewardess) throws InvalidAttributeValueException {
		List<Flight2Employee> team = new ArrayList<Flight2Employee>();
		addInListFreeEmployeesForDate(team, "Пилот", flightId, date, countPilot);
		addInListFreeEmployeesForDate(team, "Штурман", flightId, date, countNavigator);
		addInListFreeEmployeesForDate(team, "Радист", flightId, date, countRadioman);
		addInListFreeEmployeesForDate(team, "Стюардесса", flightId, date, countStewardess);
		saveAll(team);
	}

	@Override
	public void deleteTeam(Long flightId, Date date) {
		flight2EmployeeDao.deleteByFlightIdAndDate(flightId, date);
	}

	@Override
	public List<Flight2Employee> getTeam(Long flightId, Date date) {
		List<Flight2Employee> result = new ArrayList<Flight2Employee>();
		for(Flight2Employee f2e: getByFlightId(flightId)) {
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
		for(Flight2Employee f2e: getTeam(flightId, date)) {
			if (employeeService.getById(f2e.getEmployeeId()).getJobTitleId() == jobtitle.getId()) {
				return true;
			};
		}
		return false;
	}



}
