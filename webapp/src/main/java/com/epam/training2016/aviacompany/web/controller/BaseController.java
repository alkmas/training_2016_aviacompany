package com.epam.training2016.aviacompany.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.AbstractModel;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.web.utils.IConverter;


@RestController
public abstract class BaseController<E, M> {
    @Inject
    private BaseService<E> service;
    @Inject
    private IConverter<E, M> converter;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<M>> getAll() {
        List<M> converted = converter.entity2model(service.getAll());
        return new ResponseEntity<List<M>>(converted,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
    public ResponseEntity<M> getById(@PathVariable Long entityId) {
    	E entity = service.getById(entityId);
        return new ResponseEntity<M>(converter.entity2model(entity),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createNewEntity(@RequestBody M model) throws InvalidDataException {
        service.save(converter.model2entity(model));
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateEntity(
            @RequestBody M model,
            @PathVariable Long entityId) {
    	E entity = converter.model2entity(model);
    	((AbstractModel)entity).setId(entityId);
    	HttpStatus status;
        try {
			service.save(entity);
			status = HttpStatus.OK;
		} catch (InvalidDataException e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}
        return new ResponseEntity<Void>(status);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long entityId) {
        service.deleteById(entityId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
