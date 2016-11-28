package com.epam.training2016.aviacompany.daodb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.AbstractModel;

@Repository
public class CacheDao<T> {
	private Map<String, CacheElement<T>> cache;

	public CacheDao() {
		this.cache = new HashMap<>();
	}

	public void set(String name, T entity, Long timeLive) {
		if (entity != null) {
			cache.put(name, new CacheElement<T>(entity, timeLive));
		}
	}

	public void set(String name, T entity) {
		this.set(name, entity, 0L);
	}

	public T get(String name) {
		deleteExpired(name);
		CacheElement<T> element = cache.get(name);
		if (element == null)
			return null;
		return element.getEntity();
	}

	public void delete(String name) {
		cache.remove(name);
	}

	private void deleteExpired(String name) {
		CacheElement<T> element = cache.get(name);
		if ((element != null) && (element.checkFinished())) {
			delete(name);
		}
	}

}
