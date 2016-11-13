package com.epam.training2016.aviacompany.services.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.TeamDaoImpl;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.TeamService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

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
	public Map<Long, Boolean> getFreeEmployeesByJobtitle(Long jobId) {
		employeeService.getAllWithTeamFree()
		return null;
	}

}
