package com.laurent.musicStore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String lastName;
	private String email;
	
	private String login;
	private String password;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Rating> ratings;
	
	public User() {
		super();
	}

	public User(String name, String lastName, String email, String login, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.password = password;
		this.ratings = new ArrayList<Rating>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void addRate(Rating rating) {
		this.ratings.add(rating);
	}
	
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
