package com.epam.training2016.aviacompany.web.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.web.components.UserDataStorage;

public abstract class BaseConverter<E, M> implements IConverter<E, M> {
	private UserDataStorage userDataStorage;
	private ResourceBundle resource;
	private String propFile;
	
	public abstract M entity2model(E entity);
	public abstract E model2entity(M model);

	
	public BaseConverter(String propFile) {
		this.propFile = propFile;
	}

	@Inject
	private ResourceBundle getResource(UserDataStorage userDataStorage) {
		this.userDataStorage = userDataStorage;
		return resource = ResourceBundle.getBundle(propFile, new Locale(userDataStorage.getLocale()));
	}

	
	public List<M> entity2model(List<E> list) {
		List<M> listResult = new ArrayList<M>();
		for (E entity : list) {
			listResult.add(entity2model(entity));
		}
		return listResult;
	}

	public List<E> model2entity(List<M> list) {
		List<E> listResult = new ArrayList<E>();
		for (M model : list) {
			listResult.add(model2entity(model));
		}
		return listResult;
	}

}
