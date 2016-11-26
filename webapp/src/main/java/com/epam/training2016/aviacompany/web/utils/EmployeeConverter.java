package com.epam.training2016.aviacompany.web.utils;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.web.model.EmployeeModel;


@Service
public class EmployeeConverter extends BaseConverter<Employee, EmployeeModel>{

	@Override
	public EmployeeModel entity2model(Employee entity) {
		EmployeeModel model = new EmployeeModel();
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		model.setJobTitleId(entity.getJobTitleId());
		model.setBirthday(entity.getBirthday().toString());
		return model;
	}

	@Override
	public Employee model2entity(EmployeeModel model) {
		Employee entity = new Employee();
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		entity.setJobTitleId(model.getJobTitleId());
		entity.setBirthday(Date.valueOf(model.getBirthday()));
		return entity;
	}

}
