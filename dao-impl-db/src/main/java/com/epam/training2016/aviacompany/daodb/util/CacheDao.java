package com.epam.training2016.aviacompany.daodb.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.io.SerializationDao;

/**
 * Класс кеширования данных
 * 
 * @param <T>
 */
@Repository
public class CacheDao<T> {
	private Long DEFAULT_TIME_LIVE = 30L;
	private Map<String, Object> cache;
	@Value("${cache.path}")
	private String cachePath;
	@Value("${cache.enable}")
	private boolean cacheEnable;
	
	@Inject
	private SerializationDao serialize;

	public CacheDao() {
		this.cache = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
//		cachePath = cachePath + "//objects.cache";
		
		Object savedMap = serialize.load(cachePath);
		if (savedMap != null) {
			cache = (Map<String, Object>) savedMap;
		}
	}
	
	@PreDestroy
	private void destroy() {
		for(String key: cache.keySet()) {
			serialize.save(cache.get(key), cachePath + "//" + key);
		}
//		serialize.save(cache, cachePath);
	}
	
	public void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}
	
	public Map<String, Object> getCache() {
		return cache;
	}
	
	public List<T> cacheList(IGetList<T> fromBase, String name) {
		if (!cacheEnable) return fromBase.get();
		List<T> list = get(name);
		if (list == null) {
			list = fromBase.get();
			put(name, list);
		} else {
			
		}
		return list;
	}

	public T cacheEntity(IGetEntity<T> fromBase, String name, Long id) {
		if (!cacheEnable) return fromBase.get();
		T entity = get(name, id);
		if (entity == null) {
			entity = fromBase.get();
			put(name, id, entity);
		}
		return entity;
	}
	
	/**
	 * Сохранить объект класса по id в HashMap
	 * 
	 * @param name
	 * @param id
	 * @param entity
	 * @param timeLive
	 */
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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
	 * Сохранить список по имени
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

	/**
	 * Получить список по имени
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
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
