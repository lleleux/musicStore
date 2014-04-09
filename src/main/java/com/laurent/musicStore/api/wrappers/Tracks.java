package com.laurent.musicStore.api.wrappers;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.laurent.musicStore.model.Track;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tracks {

	@XmlElement(name = "track")
	List<Track> tracks;
	
	public Tracks() {
		super();
	}

	public Tracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
}
