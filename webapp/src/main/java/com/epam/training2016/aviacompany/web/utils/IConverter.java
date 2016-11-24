package com.epam.training2016.aviacompany.web.utils;

import java.util.List;

public interface IConverter<E, M> {
	E model2entity(M model);
	M entity2model(E entity);
	List<E> model2entity(List<M> models);
	List<M> entity2model(List<E> entities);
}
