package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public interface Flight2EmployeeService{
    void saveAll(List<Flight2Team> entities) throws InvalidAttributeValueException;
    void save(Flight2Team entity) throws InvalidAttributeValueException;
    void deleteById(Long id);
    boolean isDaoExist();
    Flight2Team getById(Long id);
	Flight2Team getByEmployeeIdAndDate(Long employeeId, Date date);
    List<Flight2Team> getAll();
	List<Flight2Team> getByFlightId(Long id);
	List<Flight2Team> getByEmployeeId(Long id);
	List<Flight2Team> getByDeparture(Date dt);
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
	boolean addInListFreeEmployeesForDate(List<Flight2Team> team, String nameJobTitle, Long flightId, Date date, int count);
	void deleteByEmployeeId(Long employeeId);
	void deleteByFlightId(Long flightId);
	void createTeamAndSave(Long flightId, Date date, 
			int countPilot, int countNavigator, int countRadioman, int countStewardess) throws InvalidAttributeValueException;
	void deleteTeam(Long flightId, Date date);
	List<Flight2Team> getTeam(Long flightId, Date date);
	List<Flight> getFlightsWithoutJobtitleByDate(String nameJobTitle, Date date);
	boolean isJobTitleInFlightByDate(Long flightId, String nameJobTitle, Date date);
}
			