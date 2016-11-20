package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.epam.training2016.aviacompany.daoapi.ITeamDao;
import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.Team;

@Repository
public class TeamDaoXmlImpl extends BaseDaoXmlImpl<Team> implements ITeamDao {

	@Inject
	private IBaseDao<Employee> employeeDao;
	
	// находится сотрудник в бригаде ?
	private boolean isEmployeeInTeam(Team team, Long id) {
		if (team.getPilot().equals(id)||team.getNavigator().equals(id)||
				team.getRadioman().equals(id)||(team.getStewardess1().equals(id))||
				team.getStewardess2().equals(id)) return true;
		return false;
	}
	
	// возвращает бригаду в которой сотрудник
	private Team getTeamWhereEmployeeId(Long id) {
		for(Team team: this.getAll()) {
			if (isEmployeeInTeam(team, id)) {
				return team;
			}
		}
		return null;
	}
	
	
	@Override
	public EmployeeWithTeam getEmployeeWithTeamById(Long id) {
		Team team = getTeamWhereEmployeeId(id);
		Employee emp = employeeDao.getById(id);
		if (emp == null) return null;
		EmployeeWithTeam empWithTeam = new EmployeeWithTeam();
		empWithTeam.setEmployee(emp);
		empWithTeam.setTeamId(team==null ? null : team.getId());
		return empWithTeam;
	}

	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeam() {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(Employee emp: employeeDao.getAll()) {
			EmployeeWithTeam empWithTeam = getEmployeeWithTeamById(emp.getId());
			if (empWithTeam != null) {
				resultList.add(empWithTeam);
			}
		}
		return resultList;
	}

	
	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId) {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(Employee emp: employeeDao.getAll()) {
			if (emp.getJobTitleId().equals(jobId)) {
				EmployeeWithTeam empWithTeam = getEmployeeWithTeamById(emp.getId());
				if (empWithTeam != null) {
					resultList.add(empWithTeam);
				}
			}
		}
		return resultList;
	}

	@Override
	public Class<Team> getGenericType() {
		return Team.class;
	}
}
