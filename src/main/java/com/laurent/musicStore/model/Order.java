package com.laurent.musicStore.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stop;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "TRACK_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private Track track;
	
	public Order() {
		super();
	};
	
	public Order(User user, Track track) {
		super();
		this.user = user;
		this.track = track;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
	
	public int getRemainingSeconds() {
		int seconds = (int) (this.stop.getTime() - new GregorianCalendar().getTimeInMillis()) / 1000;
		return seconds > 0 ? seconds : 0;
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
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
