package com.epam.training2016.aviacompany.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


public abstract class BaseConverter<E, M> implements IConverter<E, M>{

	public abstract M entity2model(E entity);
	public abstract E model2entity(M model);
	
	public List<M> entity2model(List<E> list) {
		List<M> listResult = new ArrayList<M>();
		for(E entity: list) {
			listResult.add(entity2model(entity));
		}
		return listResult;
	}
	
	public List<E> model2entity(List<M> list) {
		List<E> listResult = new ArrayList<E>();
		for(M model: list) {
			listResult.add(model2entity(model));
		}
		return listResult;
	}
	
}
