package com.laurent.musicStore.service;

import java.util.List;

import com.laurent.musicStore.model.Track;


public interface TrackService {
	
	List<Track> getAllTracks();
	
	Track getTrack(int id);
	
	Track addTrack(Track track);
	
	Boolean rate(int trackId, String login, int rating, String comment);

}
