package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

public interface FlightDayWeekService {
    void saveAll(List<FlightDayWeek> entities);
    void save(FlightDayWeek entity);
    void delete(FlightDayWeek entity);
    boolean isDaoExist();
    List<FlightDayWeek> getByFlightId(Long id);
    void insert(FlightDayWeek entity);
}
