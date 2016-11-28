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
	private Map<Long, CacheElement<T>> cache;

	public CacheDao() {
		this.cache = new HashMap<>();
	}

	public void set(T entity, Long timeLive) {
		if (entity != null) {
			cache.put(((AbstractModel) entity).getId(), new CacheElement<T>(entity, timeLive));
		}
	}

	public void set(T entity) {
		this.set(entity, 0L);
	}

	public T get(Long id) {
		deleteExpired(id);
		CacheElement<T> element = cache.get(id);
		if (element == null)
			return null;
		return element.getEntity();
	}

	public void delete(Long id) {
		cache.remove(id);
	}

	public List<T> getAll() {
		deleteAllExpired();
		List<T> resultList = new ArrayList<>();

		for (Long key : cache.keySet()) {
			resultList.add(cache.get(key).getEntity());
		}
		return resultList;
	}

	public List<T> cacheList(List<T> list) {
		if (list == null) return null;
		if ((list != null) && (list.size() != cache.size())) {
			for (T entity : list) {
				this.set(entity);
			}
		}
		return getAll();

	}

	private void deleteExpired(Long id) {
		CacheElement<T> element = cache.get(id);
		if ((element != null) && (element.checkFinished())) {
			cache.remove(id);
		}
	}

	private void deleteAllExpired() {
		for (Long key : cache.keySet()) {
			deleteExpired(key);
		}
	}

}
