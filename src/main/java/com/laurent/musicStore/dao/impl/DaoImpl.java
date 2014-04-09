package com.laurent.musicStore.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.laurent.musicStore.dao.Dao;

@SuppressWarnings({ "serial", "unchecked" })
public abstract class DaoImpl<K, E> implements Dao<K, E> {

	private Class<E> entityClass;

	@Resource(name = "mySessionFactory")
	private SessionFactory sessionFactory;

	public DaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public E getById(K id) {
		return (E) sessionFactory.getCurrentSession().get(entityClass, (Serializable) id);
	}

	public E persist(E entity) {
		sessionFactory.getCurrentSession().persist(entity);
		return entity;
	}

	public void update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void delete(K id) {
		E entity = getById(id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	public List<E> list() {
		return list("SELECT x FROM " + entityClass.getSimpleName() + " x");
	}
	
	public E detach(E entity) {
		sessionFactory.getCurrentSession().evict(entity);
		return entity;
	}

	protected List<E> list(String queryString, Object... params) {
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}

	protected E get(String queryString, Object... params) {
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (E) query.uniqueResult();
	}

}