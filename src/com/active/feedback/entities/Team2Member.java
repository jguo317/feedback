package com.active.feedback.entities;

public class Team2Member {
	
	private int id;
	private User user;
	private Team team;
	
	public Team2Member() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setId(int id) {
		this.id = id;
	}

}
