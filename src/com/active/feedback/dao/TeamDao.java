package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.entities.Team;
import com.active.feedback.entities.Team2Admin;
import com.active.feedback.entities.Team2Member;
import com.active.feedback.entities.User;

public interface TeamDao {
	
	public void add(Team team);
	
	public void delete(Team team);
	
	public void update(Team team);
	
	public Team getTeamById(int id);
	
	public List<Team> getAllTeamByUserId(int userId);
	
	public List<User> getMembersByTeamId(int id);
	
	public void addTeam2Member(Team2Member t2m);
	
	public void addTeam2Admin(Team2Admin t2a);

}
