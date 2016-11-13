package com.epam.training2016.aviacompany.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training.library.daoapi.IAuthorDao;
import com.epam.training.library.datamodel.Author;
import com.epam.training.library.datamodel.AuthorWithCountry;

@Repository
public class AuthorDaoXmlImpl implements IAuthorDao {

    @Override
    public AuthorWithCountry getWithCountry(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Author entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Author> getAll() {
        throw new UnsupportedOperationException();
    }

}
