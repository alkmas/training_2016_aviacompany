package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.services.utils.IdNullException;

public interface BaseService<T> {
    void saveAll(List<T> entities);
    void save(T entity);
    void deleteById(Long id) throws IdNullException;
    void deleteCascadeById(Long id) throws IdNullException;
    boolean isDaoExist();
    T getById(Long id) throws IdNullException;
    T getByName(String name);
    List<T> getAll();
    List<T> filter(T entityFilter);
}
