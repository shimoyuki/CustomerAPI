package com.capi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capi.dao.UserMapper;
import com.capi.model.User;
import com.capi.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDAO;
	
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return this.userDAO.insertSelective(user);
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return this.userDAO.deleteByPrimaryKey(id);
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return this.userDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean checkUser(String username, String password) {
		// TODO Auto-generated method stub
		if (username == null || password == null) {
			return false;
		}
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		params.put("password", password);
		if (!this.userDAO.query(params).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getUsers(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.userDAO.query(params);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return this.userDAO.updateByPrimaryKeySelective(user);
	}

}
