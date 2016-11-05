package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public interface Flight2EmployeeService{
    void saveAll(List<Flight2Employee> entities) throws InvalidAttributeValueException;
    void save(Flight2Employee entity) throws InvalidAttributeValueException;
    void deleteById(Long id);
    boolean isDaoExist();
    Flight2Employee getById(Long id);
	Flight2Employee getByEmployeeIdAndDate(Long id, Date date);
    List<Flight2Employee> getAll();
    List<Flight2Employee> filter(Flight2Employee entityFilter);
	List<Flight2Employee> getByFlightId(Long id);
	List<Flight2Employee> getByEmployeeId(Long id);
	List<Flight2Employee> getByDeparture(Date dt);
	List<Flight2Employee> getFreeEmployeesForDate(String nameJobTitle, Long flightId, Date date, int count);
	void deleteByEmployeeId(Long employeeId);
	void deleteByFlightId(Long flightId);
	void createTeam(Long flightId, Date date, 
			int countPilot, int countNavigator, int countRadioman, int countStewardess) throws InvalidAttributeValueException;
	void deleteTeam(Long flightId, Date date);
	List<Flight2Employee> getTeam(Long flightId, Date date);
}
			