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
	Flight2Employee getByEmployeeIdAndDate(Long employeeId, Date date);
    List<Flight2Employee> getAll();
	List<Flight2Employee> getByFlightId(Long id);
	List<Flight2Employee> getByEmployeeId(Long id);
	List<Flight2Employee> getByDeparture(Date dt);
	/**
	 * Составление бригады на рейс
	 * @param team
	 * @param nameJobTitle
	 * @param flightId
	 * @param date
	 * @param count
	 * @return false - в список не надо добавлять
	 * 		   true - в список добавлено нужное кол-во сотрудников
	 */
	boolean addInListFreeEmployeesForDate(List<Flight2Employee> team, String nameJobTitle, Long flightId, Date date, int count);
	void deleteByEmployeeId(Long employeeId);
	void deleteByFlightId(Long flightId);
	void createTeamAndSave(Long flightId, Date date, 
			int countPilot, int countNavigator, int countRadioman, int countStewardess) throws InvalidAttributeValueException;
	void deleteTeam(Long flightId, Date date);
	List<Flight2Employee> getTeam(Long flightId, Date date);
	List<Flight> getFlightsWithoutJobtitleByDate(String nameJobTitle, Date date);
	boolean isJobTitleInFlightByDate(Long flightId, String nameJobTitle, Date date);
}
			