package com.epam.training2016.aviacompany.daodb.util;

import java.util.Date;

public class CacheElement<T> {
	private T entity;
	private Date finishTime; 

	/**
	 * 
	 * @param entity
	 * @param timeLive - время жизни элемента в секундах, если 0 - вечно
	 */
	public CacheElement(T entity, Long timeLive) {
		this.entity = entity;
		if (timeLive != 0) {
			finishTime = new Date((new Date()).getTime()+ timeLive * 1000);
		} else {
			finishTime = null;
		}
	}
	
	public CacheElement(T entity) {
		this(entity, 0L);
	}
	
	public T getEntity() {
		return entity;
	}
	
	/**
	 * 
	 * @return true - если время жизни истекло 
	 */
	public boolean isFinished() {
		if (finishTime == null) return false;
		return (new Date()).getTime() < finishTime.getTime();
	}
}
