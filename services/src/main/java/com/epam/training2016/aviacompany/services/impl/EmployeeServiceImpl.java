package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {
	@Inject
	private EmployeeDaoImpl employeeDao;
	@Inject
	private JobTitleDaoImpl jobtitleDao;


	@Override
	public List<EmployeeWithTeam> getAllWithTeamByJobId(Long jobtitleId) {
		return employeeDao.getAllWithTeamByJobId(jobtitleId);
	}
	
	@Override
	public List<EmployeeWithTeam> getAllWithTeamByJobName(String name) {
		Long jobId = jobtitleDao.getByName(name).getId();
		return getAllWithTeamByJobId(jobId);
	}
	
	@Override
	@Transactional
	public void deleteByJobId(Long jobtitleId) {
		for(EmployeeWithTeam emp: employeeDao.getAllWithTeamByJobId(jobtitleId)) {
			this.deleteById(emp.getEmployee().getId());
		}
	}

	@Override
	public boolean haveJobTitle(Long id, Long jobtitleId) {
		return (getById(jobtitleId).getJobTitleId() == jobtitleId);
	}

	@Override
	public List<EmployeeWithTeam> getAllWithTeam() {
		return employeeDao.getAllWithTeam();
	}

	@Override
	public List<EmployeeWithTeam> getAllWithTeamFree() {
		List<EmployeeWithTeam> resultList = new ArrayList<EmployeeWithTeam>();
		for(EmployeeWithTeam emp: getAllWithTeam()) {
			if (emp.getTeamId() == null) {
				resultList.add(emp);
			}
		}
		return resultList;
	}
	
}
