package com.epam.training2016.aviacompany.daoapi;

import java.util.List;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Team;

public interface ITeamDao extends IBaseDao<Team> {
	EmployeeWithTeam getEmployeeWithTeamById(Long id);
	List<EmployeeWithTeam> getAllEmployeeWithTeam();
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId);
}
