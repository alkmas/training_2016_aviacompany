package com.epam.training2016.aviacompany.services;

import java.util.List;

public interface BaseService<T> {
    void saveAll(List<T> entities);
    void save(T entity);
    void deleteById(Long id);
    boolean isDaoExist();
    T get(Long id);
    List<T> getAll();
}
