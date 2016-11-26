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

import com.epam.training2016.aviacompany.daoapi.customentity.FlightWithAirportsAndDaysWeek;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.services.FlightService;
import com.epam.training2016.aviacompany.web.model.FlightModel;
import com.epam.training2016.aviacompany.web.utils.IConverter;

@RestController
@RequestMapping("/flight")
public class FlightController extends BaseController<Flight, FlightModel> {

    @Inject
    private FlightService service;
    @Inject
    private IConverter<Flight, FlightModel> converter;


    @RequestMapping(value = "/fullinfo", method = RequestMethod.GET)
    public ResponseEntity<List<FlightWithAirportsAndDaysWeek>> getFullInfo() {
        List<FlightWithAirportsAndDaysWeek> all = service.getAllWithFullInfo();
        return new ResponseEntity<List<FlightWithAirportsAndDaysWeek>>(all,
                HttpStatus.OK);
    }

    
    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<FlightModel>> getAllByDate(@PathVariable String date) {
        List<FlightModel> converted = converter.entity2model(service.getAllByDate(Date.valueOf(date)));
        return new ResponseEntity<List<FlightModel>>(converted, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/emptyteam/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<FlightModel>> getAllWihEmptyTeamByDate(@PathVariable String date) {
        List<FlightModel> converted = 
        		converter.entity2model(service.getAllByDateWithoutTeam(Date.valueOf(date)));
        return new ResponseEntity<List<FlightModel>>(converted, HttpStatus.OK);
    }

}
