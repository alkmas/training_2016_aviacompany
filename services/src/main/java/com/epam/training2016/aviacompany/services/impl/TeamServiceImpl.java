package com.epam.training2016.aviacompany.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daoapi.ITeamDao;
import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.training2016.aviacompany.services.TeamService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService {
	
	@Inject
	private ITeamDao teamDao;
	@Inject
	private EmployeeService employeeService;
	@Inject
	private BaseService<JobTitle> jobtitleService;
	@Inject
	private FlightService flightService;
	@Inject
	private Flight2TeamService flight2TeamService;
	
	// Проверка перед сохранением в базу
	private boolean checkTeam(Team entity) {
		Set<Long> teamSet = new HashSet<Long>();
		teamSet.add(entity.getPilot());
		teamSet.add(entity.getNavigator());
		teamSet.add(entity.getRadioman());
		teamSet.add(entity.getStewardess1());
		teamSet.add(entity.getStewardess2());
		//Сотрудники не должны повторяться и не быть null
		if ((teamSet.size() != 5)||(teamSet.contains(null))) return false;
		//Должность соответствовать полю записи
		if ((!employeeService.haveJobTitle(entity.getPilot(), jobtitleService.getByName("Пилот").getId()))
			||(!employeeService.haveJobTitle(entity.getNavigator(), jobtitleService.getByName("Штурман").getId()))
			||(!employeeService.haveJobTitle(entity.getRadioman(), jobtitleService.getByName("Радист").getId()))
			||(!employeeService.haveJobTitle(entity.getStewardess1(), jobtitleService.getByName("Стюардесса").getId()))
			||(!employeeService.haveJobTitle(entity.getStewardess2(), jobtitleService.getByName("Стюардесса").getId()))){
			return false;
		}
		return true;
	}
	
	@Override
	public void save(Team team) throws InvalidDataException {
		if (!checkTeam(team)) {
			throw new InvalidDataException("Check data for Team");
		}
		if (team.getId() == null) {
			teamDao.insert(team);
		} else {
			teamDao.update(team);
		}
	}

	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobtitleId) {
		return teamDao.getAllEmployeeWithTeamByJobId(jobtitleId);
	}
	
	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamByJobName(String name) {
		Long jobId = jobtitleService.getByName(name).getId();
		return getAllEmployeeWithTeamByJobId(jobId);
	}
	

	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeam() {
		return teamDao.getAllEmployeeWithTeam();
	}

	
	@Override
	public List<EmployeeWithTeam> getAllFreeEmployeeWithTeam() {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(EmployeeWithTeam emp: getAllEmployeeWithTeam()) {
			if (emp.getTeamId() == null) {
				resultList.add(emp);
			}
		}
		if (resultList.isEmpty()) return null;
		return resultList;
	}
	
	
	@Override
	public List<EmployeeWithTeam> getAllFreeEmployeeWithTeamByJobId(Long jobId) {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(EmployeeWithTeam emp: getAllFreeEmployeeWithTeam()) {
			if (emp.getEmployee().getJobTitleId().equals(jobId)) {
				resultList.add(emp);
			}
		}
		if (resultList.isEmpty()) return null;
		return resultList;
	}

	
	@Override
	public List<EmployeeWithTeam> getAllFreeEmployeeWithTeamByJobName(String name) {
		return getAllFreeEmployeeWithTeamByJobId(jobtitleService.getByName(name).getId());
	}

	@Override
	public List<Team> getAllFreeTeamByDate(Date date) {
		List<Team> allTeam = getAll();
		List<Flight2Team> f2tBusy =	flight2TeamService.getByDeparture(date);
		if (f2tBusy.isEmpty()) return allTeam;
		List<Team> resultList = new ArrayList<Team>();
		int sizeListBusy = f2tBusy.size();
		int index;
		for(Team team: allTeam) {
			for(index=0; index < sizeListBusy; index++) {
				if (f2tBusy.get(index).getTeamId().equals(team.getId())) {
					break;
				}
			}
			if (index == sizeListBusy) {
				resultList.add(team);
			}
		}
		if (resultList.isEmpty()) return null;
		return resultList;
	}

}
