package com.epam.training2016.aviacompany.services;

import java.util.List;
import java.util.Map;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

public interface TeamService extends BaseService<Team> {
	
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobName(String nameJob);
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId);
	List<EmployeeWithTeam> getAllEmployeeWithTeam();
	/**
	 * Все сотрудники не входящие в бригады 
	 * @return
	 */
	List<EmployeeWithTeam> getAllEmployeeWithTeamFree();
	List<EmployeeWithTeam> getAllEmployeeWithTeamFreeByJobId(Long jobId);
	List<EmployeeWithTeam> getAllEmployeeWithTeamFreeByJobName(String name);
}
