package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Airport;

public interface AirportDao {
	Airport get(Long id);

    void insert(Airport entity);

    void update(Airport entity);

    void delete(Long id);

    List<Airport> getAll();

}
