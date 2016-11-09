package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.FlightDays;

public interface FlightDayWeekService {
    void saveAll(List<FlightDays> entities);
    void save(FlightDays entity);
    void delete(FlightDays entity);
    void deleteByFlightId(Long flightId);
    boolean isDaoExist();
    List<FlightDays> getByFlightId(Long flightId);
    void saveDaysWeekForFlight(Long flightId, List<Long> days);
    List<Long> getDaysFromList(List<FlightDays> flightDaysWeek);
}
