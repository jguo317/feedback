package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.entities.User;

public interface UserDao {
	
	public void add(User user);
	
	public void delete(User user);
	
	public void Update(User user);
	
	public User getUserById(int id);
	
	public User getUserByUserName(String username, String password);
	
	public List<User> getAllUsers();
	
	public List<User> getUserByTeamId(int teamId);

}
