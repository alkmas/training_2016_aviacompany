package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Airport;

public interface AirportService {
    void saveAll(List<Airport> entities);
    void save(Airport entity);
    void deleteById(Long id);
    boolean isDaoExist();
    Airport getById(Long id);
    Airport getByName(String name);
    List<Airport> getAll();
    List<Airport> filter(Airport entityFilter);

}
