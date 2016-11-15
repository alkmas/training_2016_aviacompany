package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.TeamDaoImpl;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.TeamService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService {
	
	@Inject
	private TeamDaoImpl teamDao;
	@Inject
	private EmployeeService employeeService;
	@Inject
	private BaseService<JobTitle> jobtitleService;
	
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
	public List<EmployeeWithTeam> getAllEmployeeWithTeamFree() {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(EmployeeWithTeam emp: getAllEmployeeWithTeam()) {
			if (emp.getTeamId() == null) {
				resultList.add(emp);
			}
		}
		return resultList;
	}
	
	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamFreeByJobId(Long jobId) {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(EmployeeWithTeam emp: getAllEmployeeWithTeamFree()) {
			if (emp.getEmployee().getJobTitleId() == jobId) {
				resultList.add(emp);
			}
		}
		return resultList;
	}

	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamFreeByJobName(String name) {
		return getAllEmployeeWithTeamFreeByJobId(jobtitleService.getByName(name).getId());
	}

}
