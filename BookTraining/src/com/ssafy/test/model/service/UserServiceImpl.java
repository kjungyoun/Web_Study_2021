package com.ssafy.test.model.service;

import java.sql.SQLException;

import com.ssafy.test.model.dao.UserDao;
import com.ssafy.test.model.dao.UserDaoImpl;
import com.ssafy.test.model.dto.User;

public class UserServiceImpl implements UserService {
	private UserDao dao = UserDaoImpl.getInstance();
	private static UserService instance = new UserServiceImpl();
	private UserServiceImpl() {};
	public static UserService getInstance() {
		return instance;
	}
	
	@Override
	public User select(String id, String pass) throws SQLException {
		return dao.select(id, pass);
	}

}
