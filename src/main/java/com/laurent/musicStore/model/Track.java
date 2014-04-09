package com.laurent.musicStore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tracks")
@XmlRootElement
public class Track {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String title;
	private String artist;
	private String album;
	private double price;
	
	private String path;
	
	@XmlElement(name = "ratings")
	@OneToMany(mappedBy = "track", fetch = FetchType.EAGER)
	private List<Rating> ratings;
	
	public Track() {
		super();
		this.ratings = new ArrayList<Rating>();
	}

	public Track(String title, String artist, String album, double price, String path) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.price = price;
		this.path = path;
		this.ratings = new ArrayList<Rating>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@XmlElement(name = "rating")
	public double getRating() {
		double rating = 0;
		for (Rating r : this.ratings) {
			rating += r.getRating();
		}
		return rating == 0 ? 0 : rating / this.ratings.size();
	}
	
	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void addRating(Rating rating) {
		this.ratings.add(rating);
	}
	
	@XmlElement(name = "numberOfRatings")
	public int getNumberOfRatings() {
		return this.ratings.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
