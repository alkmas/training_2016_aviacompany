package com.epam.training2016.aviacompany.web.controller;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.TeamService;
import com.epam.training2016.aviacompany.web.model.TeamModel;
import com.epam.training2016.aviacompany.web.utils.IConverter;

@RestController
@RequestMapping("/team")
public class TeamController extends BaseController<Team, TeamModel> {

    @Inject
    private TeamService service;
    @Inject
    private IConverter<Team, TeamModel> converter;

    
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeWithTeam>> getAllEmployee() {
        List<EmployeeWithTeam> all = service.getAllEmployeeWithTeam();
        return new ResponseEntity<List<EmployeeWithTeam>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{nameJob}", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeWithTeam>> getAllEmployeeByJobName(@PathVariable String nameJob) {
    	List<EmployeeWithTeam> all = service.getAllEmployeeWithTeamByJobName(nameJob);
        return new ResponseEntity<List<EmployeeWithTeam>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/employeefree", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeWithTeam>> getAllFreeEmployee() {
        List<EmployeeWithTeam> all = service.getAllFreeEmployeeWithTeam();
        return new ResponseEntity<List<EmployeeWithTeam>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/employeefree/{nameJob}", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeWithTeam>> getAllFreeEmployeeByJobName(@PathVariable String nameJob) {
    	List<EmployeeWithTeam> all = service.getAllEmployeeWithTeamByJobName(nameJob);
        return new ResponseEntity<List<EmployeeWithTeam>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/free/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<TeamModel>> getAllFreeByDate(@PathVariable String date) {
    	List<TeamModel> converted = converter.entity2model(service.getAllFreeTeamByDate(Date.valueOf(date)));
        return new ResponseEntity<List<TeamModel>>(converted, HttpStatus.OK);
    }


}
