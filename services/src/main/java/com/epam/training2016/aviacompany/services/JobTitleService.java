package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.JobTitle;

public interface JobTitleService {
    void saveAll(List<JobTitle> entities);
    void save(JobTitle entity);
    void deleteById(Long id);
    boolean isDaoExist();
    JobTitle getById(Long id);
    JobTitle getByName(String name);
    List<JobTitle> getAll();
    List<JobTitle> filter(JobTitle entityFilter);
}
