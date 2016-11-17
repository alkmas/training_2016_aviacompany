package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Team;

public interface TeamService extends BaseService<Team> {
	
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobName(String nameJob);
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId);
	List<EmployeeWithTeam> getAllEmployeeWithTeam();
	/**
	 * Все сотрудники не входящие в бригады 
	 * @return
	 */
	List<EmployeeWithTeam> getAllFreeEmployeeWithTeam();
	List<EmployeeWithTeam> getAllFreeEmployeeWithTeamByJobId(Long jobId);
	List<EmployeeWithTeam> getAllFreeEmployeeWithTeamByJobName(String name);
	
	List<Team> getAllFreeTeamByDate(Date date);
}
