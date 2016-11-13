package com.epam.training2016.aviacompany.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.epam.training.library.daoapi.IBookDao;
import com.epam.training.library.datamodel.Book;
import com.thoughtworks.xstream.XStream;

@Repository
public class BookDaoXmlImpl implements IBookDao {
    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void intialize() throws IOException {
        // TODO move to the parent class
        // TODO refactoring: use classname instead of hardcoded filename
        xstream = new XStream();
        xstream.alias("book", Book.class);

        file = new File(basePath + "/books.xml");
        if (!file.exists()) {
            file.createNewFile();
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    @Override
    public Book get(Long id) {
        List<Book> allBooks = readCollection();

        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        List<Book> allBooks = readCollection();

        List<Book> newList = new ArrayList<>();
        // TODO: don't iterate whole collection
        for (Book book : allBooks) {
            if (!book.getId().equals(id)) {
                newList.add(book);
            }
        }
        writeCollection(newList);
    }

    @Override
    public List<Book> getAll() {
        return readCollection();
    }

    @Override
    public Long insert(Book entity) {
        List<Book> allBooks = readCollection();
        Long id = getNextId(allBooks);

        allBooks.add(entity);

        entity.setId(new Long(id));

        writeCollection(allBooks);
        return id;
    }

    @Override
    public void update(Book entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    private List<Book> readCollection() {
        return (List<Book>) xstream.fromXML(file);
    }

    private void writeCollection(List<Book> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Book> allBooks) {
        return allBooks.isEmpty() ? 1l : allBooks.get(
                allBooks.size() - 1).getId() + 1;
    }

}
