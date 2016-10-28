package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Airport;

public interface BaseService<T> {
    void saveAll(List<T> airports);
    void save(T airport);
    boolean isDaoExist();
    Airport get(Long id);
    Long insert(T airport);

}
