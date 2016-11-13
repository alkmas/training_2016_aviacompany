package com.epam.training2016.aviacompany.services;

import java.util.List;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

public interface EmployeeService extends BaseService<Employee>{
	List<EmployeeWithTeam> getAllWithTeamByJobName(String nameJob);
	List<EmployeeWithTeam> getAllWithTeamByJobId(Long jobId);
	List<EmployeeWithTeam> getAllWithTeam();
	/**
	 * Все сотрудники не входящие в бригады 
	 * @return
	 */
	List<EmployeeWithTeam> getAllWithTeamFree();
	void deleteByJobId(Long jobId);
	boolean haveJobTitle(Long id, Long jobId);
}
