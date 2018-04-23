package com.yash.mavendemo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.mavendemo.dao.UserDAO;
import com.yash.mavendemo.model.User;
import com.yash.mavendemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	public boolean saveUser(User user) {
		return userDAO.saveUser(user);
	}

	@Override
	public List<User> listUsers() {
		return userDAO.listUsers();
	}

}
