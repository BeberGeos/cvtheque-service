package fr.neosoft.cvtheque.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.neosoft.cvtheque.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	
	@PersistenceContext(unitName = "cvtheque-service")
	private EntityManager entityManager;
	
	private Class<T> type;
	
	public GenericDaoImpl(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public T create(T t) {
		getEntityManager().persist(t);
		return t;
	}

	public void delete(Object id) {
		getEntityManager().remove(this.entityManager.getReference(type, id));
	}

	public T find(Object id) {
		return (T) getEntityManager().find(type, id);
	}

	public T update(T t) {
		return getEntityManager().merge(t);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
