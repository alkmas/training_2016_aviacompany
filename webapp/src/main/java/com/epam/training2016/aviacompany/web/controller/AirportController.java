package com.epam.training2016.aviacompany.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.web.model.AirportModel;

@RestController
@RequestMapping("/airport")
public class AirportController extends BaseController<Airport, AirportModel> {

}
