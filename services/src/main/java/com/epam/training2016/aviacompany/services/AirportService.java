package com.epam.training2016.aviacompany.services;

import java.util.List;
import com.epam.training2016.aviacompany.datamodel.Airport;

public interface AirportService {
    void saveAll(List<Airport> books);

    void save(Airport book);

    boolean isDaoExist();

}
