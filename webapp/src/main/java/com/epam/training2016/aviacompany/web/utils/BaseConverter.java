package com.epam.training2016.aviacompany.web.utils;

import java.util.List;

public class BaseConverter<E, M> implements IConverter<E, M> {

	private Class<E> typeE;
	private Class<M> typeM;
	
	public BaseConverter(Class<E> typeE, Class<M> typeM) {
		this.typeE = typeE;
		this.typeM = typeM;
	}

	@Override
	public E model2entity(M model) {
		typeM.getDeclaredFields();
		return null;
	}

	
	@Override
	public M entity2model(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> model2entity(List<M> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> entity2model(List<E> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
