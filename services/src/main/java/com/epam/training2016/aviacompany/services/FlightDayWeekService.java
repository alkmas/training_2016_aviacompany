package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

public interface FlightDayWeekService {
    void saveAll(List<FlightDayWeek> entities);
    void save(FlightDayWeek entity);
    void delete(FlightDayWeek entity);
    void deleteByFlightId(Long flightId);
    boolean isDaoExist();
    List<FlightDayWeek> getByFlightId(Long flightId);
    void saveDaysWeekForFlight(Long flightId, List<Long> days);
    List<Long> getDaysFromList(List<FlightDayWeek> flightDaysWeek);
}
