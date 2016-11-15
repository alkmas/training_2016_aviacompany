package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

public interface TeamDao extends BaseDao<Team> {
	EmployeeWithTeam getEmployeeWithTeamById(Long id);
	List<EmployeeWithTeam> getAllEmployeeWithTeam();
	List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId);

}
