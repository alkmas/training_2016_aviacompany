package com.epam.training2016.aviacompany.daodb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * Класс кеширования запросов
 * 
 * @param <T>
 */
@Repository
public class CacheDao<T> {
	private Long DEFAULT_TIME_LIVE = 30L;
	private Map<String, Object> cache;

	public CacheDao() {
		this.cache = new HashMap<>();
	}

	/**
	 * Сохранить объект класса по id в HashMap
	 * 
	 * @param name
	 * @param id
	 * @param entity
	 * @param timeLive
	 */
	public void put(String name, Long id, T entity, Long timeLive) {
		if (entity != null) {
			HashMap<Long, CacheElement<T>> hash;
			if (!cache.containsKey(name)) {
				hash = new HashMap<Long, CacheElement<T>>();
			} else {
				hash = (HashMap<Long, CacheElement<T>>) cache.get(name);
			}
			hash.put(id, new CacheElement<T>(entity, timeLive));
		}
	}

	public void put(String name, Long id, T entity) {
		this.put(name, id, entity, DEFAULT_TIME_LIVE);
	}

	/**
	 * Получить объект по id из HashMap
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	public T get(String name, Long id) {
		// deleteExpired(name);
		Map<Long, CacheElement<T>> hash = (HashMap<Long, CacheElement<T>>) cache.get(name);
		if ((hash != null) && (hash.containsKey(id))) {
			CacheElement<T> element = hash.get(id);
			if (element.isFinished()) {
				hash.remove(id);
				return null;
			} else {
				return element.getEntity();
			}
		}
		return null;
	}

	/**
	 * Сохранить список
	 * @param name
	 * @param entity
	 * @param timeLive
	 */
	public void put(String name, List<T> entity, Long timeLive) {
		if (entity != null) {
			CacheElement<List<T>> element;
			element = new CacheElement<List<T>>(entity, timeLive);
			cache.put(name, element);
		}
	}

	public void put(String name, List<T> entity) {
		this.put(name, entity, DEFAULT_TIME_LIVE);
	}

	public List<T> get(String name) {
		CacheElement<List<T>> element = (CacheElement<List<T>>) cache.get(name);
		if ((element != null) && (!element.isFinished())) {
			return element.getEntity();
		} else {
			cache.remove(name);
			return null;
		}
	}

	/**
	 * Очисть кеш
	 */
	public void clearAll() {
		cache.clear();
	}

}
