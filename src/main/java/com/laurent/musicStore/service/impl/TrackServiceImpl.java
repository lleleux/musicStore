package com.laurent.musicStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurent.musicStore.dao.NotificationDao;
import com.laurent.musicStore.dao.RatingDao;
import com.laurent.musicStore.dao.TrackDao;
import com.laurent.musicStore.dao.UserDao;
import com.laurent.musicStore.model.Notification;
import com.laurent.musicStore.model.Rating;
import com.laurent.musicStore.model.Track;
import com.laurent.musicStore.model.User;
import com.laurent.musicStore.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {
	
	@Autowired
	private TrackDao trackDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RatingDao ratingDao;
	@Autowired
	private NotificationDao notificationDao;

	@Override
	public List<Track> getAllTracks() {
		List<Track> tracks = this.trackDao.list();
		for (Track t : tracks) {
			this.trackDao.detach(t);
		}
		return tracks;
	}

	@Override
	public Track getTrack(int id) {
		return this.trackDao.detach(this.trackDao.getById(id));
	}

	@Override
	public Track addTrack(Track track) {
		return this.trackDao.detach(this.trackDao.persist(track));
	}

	@Override
	public Boolean rate(int trackId, String login, int rate, String comment) {
		if (comment == null) {
			comment = "";
		}
		Track track = this.trackDao.getById(trackId);
		User user = this.userDao.getByLogin(login);
		for (Rating r : user.getRatings()) {
			if (r.getTrack().equals(track)) {
				return false;
			}
		}
		Rating rating = new Rating("", rate, user, track);
		track.addRating(rating);
		user.addRate(rating);
		// Add rating
		this.ratingDao.persist(rating);
		// Notify users
		for (User u : this.userDao.getByTrackOrder(trackId)) {
			String text = user.getName() + " " + user.getLastName() + " rated " + track.getTitle() + " - " + track.getArtist() + " and give it " + rate + " stars !";
			this.notificationDao.persist(new Notification(text, u));
		}
		return true;
	}
	
}
