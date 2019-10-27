package com.capi.service;

import java.util.List;
import java.util.Map;

import com.capi.model.User;

public interface UserService {
	public int addUser(User user);
	
	public int deleteUser(int id);
	
	public User getUser(int id);
	
	public boolean checkUser(String username, String password);
	
	public List<User> getUsers(Map<String, Object> params);
	
	public int updateUser(User user);
}
