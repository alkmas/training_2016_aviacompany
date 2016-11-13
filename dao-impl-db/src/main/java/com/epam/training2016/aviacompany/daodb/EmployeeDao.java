package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

public interface EmployeeDao extends BaseDao<Employee> {
	EmployeeWithTeam getWithTeamById(Long id);
	List<EmployeeWithTeam> getAllWithTeam();
	List<EmployeeWithTeam> getAllWithTeamByJobId(Long jobId);

}
