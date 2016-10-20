package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight;

public interface FlightDao {
	Flight get(Long id);
    void insert(Flight entity);
    void update(Flight entity);
    void delete(Long id);
    List<Flight> getAll();

}
