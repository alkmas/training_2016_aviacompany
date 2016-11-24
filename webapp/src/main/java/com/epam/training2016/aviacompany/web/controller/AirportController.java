package com.epam.training2016.aviacompany.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.web.model.AirportModel;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Inject
    private BaseService<Airport> airportService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AirportModel>> getAll() {
        List<Airport> all = airportService.getAll();

        List<AirportModel> converted = new ArrayList<>();
        for (Airport airport : all) {
            converted.add(entity2model(airport));
        }

        return new ResponseEntity<List<AirportModel>>(converted,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{airportId}", method = RequestMethod.GET)
    public ResponseEntity<AirportModel> getById(
            @PathVariable Long airportId) {
    	Airport airport = airportService.getById(airportId);
        return new ResponseEntity<AirportModel>(entity2model(airport),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createNewAirport(
            @RequestBody AirportModel airportModel) throws InvalidDataException {
        airportService.save(model2entity(airportModel));
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{airportId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateAirport(
            @RequestBody AirportModel airportModel,
            @PathVariable Long airportId) {
    	Airport airport = model2entity(airportModel);
    	airport.setId(airportId);
        try {
			airportService.save(airport);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @RequestMapping(value = "/{airportId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long airportId) {
        airportService.deleteById(airportId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    private AirportModel entity2model(Airport airport) {
        AirportModel airportModel = new AirportModel();
        airportModel.setName(airport.getName());
        airportModel.setId(airport.getId());
        return airportModel;
    }

    private Airport model2entity(AirportModel airportModel) {
    	Airport airport = new Airport();
        airport.setName(airportModel.getName());
        airport.setId(airportModel.getId());
        return airport;
    }

}
