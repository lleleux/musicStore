package com.laurent.musicStore.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface Dao<K, E> extends Serializable {

	@Transactional
	E getById(K id);

	@Transactional
	E persist(E entity);

	@Transactional
	void update(E entity);

	@Transactional
	void delete(K id);
	
	@Transactional
	List<E> list();
	
	@Transactional
	E detach(E entity);

}