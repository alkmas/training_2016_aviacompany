package com.epam.training2016.aviacompany.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.web.model.EmployeeModel;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController<Employee, EmployeeModel> {

}
