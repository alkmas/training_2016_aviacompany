package com.epam.training2016.aviacompany.services;

import java.util.List;

public interface BaseService<T> {
    void saveAll(List<T> entities);
    Long save(T entity);
    boolean isDaoExist();
    T get(Long id);
    List<T> getAll();
}
