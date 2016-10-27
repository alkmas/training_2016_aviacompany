package com.epam.training2016.aviacompany.services;

import java.util.List;
import com.epam.training2016.aviacompany.datamodel.Airport;

public interface AirportService {
    void saveAll(List<Airport> airports);
    void save(Airport airport);
    boolean isDaoExist();
    Airport get(Long id);
    Long insert(Airport airport);
}
