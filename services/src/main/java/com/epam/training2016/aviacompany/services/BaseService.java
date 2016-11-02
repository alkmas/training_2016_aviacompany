package com.epam.training2016.aviacompany.services;

import java.util.List;

public interface BaseService<T> {
    void saveAll(List<T> entities);
    void save(T entity);
    void deleteById(Long id);
    boolean isDaoExist();
    T getById(Long id);
    List<T> getByName(String name);
    List<T> getAll();
    List<T> filter(T entityFilter);
}
