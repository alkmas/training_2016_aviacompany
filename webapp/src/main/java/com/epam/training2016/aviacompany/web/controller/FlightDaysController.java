package com.epam.training2016.aviacompany.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.web.model.FlightModel;

@RestController
@RequestMapping("/flightdays")
public class FlightDaysController extends BaseController<Flight, FlightModel> {

}
