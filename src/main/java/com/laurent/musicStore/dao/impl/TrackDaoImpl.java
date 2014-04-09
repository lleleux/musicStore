package com.laurent.musicStore.dao.impl;

import org.springframework.stereotype.Repository;

import com.laurent.musicStore.dao.TrackDao;
import com.laurent.musicStore.model.Track;

@Repository
public class TrackDaoImpl extends DaoImpl<Integer, Track> implements TrackDao {
	
}
