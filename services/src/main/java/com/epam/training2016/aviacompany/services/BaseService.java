package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

public interface BaseService<T> {
    void saveAll(List<T> entities) throws InvalidDataException;
    void save(T entity) throws InvalidDataException;
    void deleteById(Long id);
    boolean isDaoExist();
    T getById(Long id);
    T getByName(String name);
    List<T> getAll();
}
