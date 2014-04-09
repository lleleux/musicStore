package com.laurent.musicStore.dao.impl;

import org.springframework.stereotype.Repository;

import com.laurent.musicStore.dao.RatingDao;
import com.laurent.musicStore.model.Rating;

@Repository
public class RatingDaoImpl extends DaoImpl<Integer, Rating> implements RatingDao {
	
}
