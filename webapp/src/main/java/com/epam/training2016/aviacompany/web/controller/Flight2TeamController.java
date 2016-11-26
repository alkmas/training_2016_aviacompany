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

import com.epam.training2016.aviacompany.daoapi.customentity.Flight2TeamJoin;
import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.epam.training2016.aviacompany.web.model.Flight2TeamModel;
import com.epam.training2016.aviacompany.web.utils.IConverter;

@RestController
@RequestMapping("/flight2team")
public class Flight2TeamController extends BaseController<Flight2Team, Flight2TeamModel> {
    
	@Inject
    private Flight2TeamService service;
    @Inject
    private IConverter<Flight2Team, Flight2TeamModel> converter;

    
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public ResponseEntity<List<Flight2TeamJoin>> getAllJoin() {
        List<Flight2TeamJoin> all = service.getAllJoin();
        return new ResponseEntity<List<Flight2TeamJoin>>(all, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<Flight2TeamModel>> getAllBydate(@PathVariable String date) {
        List<Flight2TeamModel> converted = 
        		converter.entity2model(service.getByDeparture(Date.valueOf(date)));
        return new ResponseEntity<List<Flight2TeamModel>>(converted, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/team/{teamId}", method = RequestMethod.GET)
    public ResponseEntity<List<Flight2TeamModel>> getAllByTeamId(@PathVariable Long teamId) {
        List<Flight2TeamModel> converted = 
        		converter.entity2model(service.getByTeamId(teamId));
        return new ResponseEntity<List<Flight2TeamModel>>(converted, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/flight/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<List<Flight2TeamModel>> getAllByFlightId(@PathVariable Long flightId) {
        List<Flight2TeamModel> converted = 
        		converter.entity2model(service.getByFlightId(flightId));
        return new ResponseEntity<List<Flight2TeamModel>>(converted, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/team/{teamId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteByTeamId(@PathVariable Long teamId) {
        service.deleteByTeamId(teamId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    
    @RequestMapping(value = "/flight/{flightId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteByFlightId(@PathVariable Long flightId) {
        service.deleteByFlightId(flightId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
