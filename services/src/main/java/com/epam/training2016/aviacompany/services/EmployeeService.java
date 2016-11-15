package com.epam.training2016.aviacompany.services;

import java.util.List;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

public interface EmployeeService extends BaseService<Employee>{
	boolean haveJobTitle(Long id, Long jobId);
}
