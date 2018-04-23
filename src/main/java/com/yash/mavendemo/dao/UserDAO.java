package com.yash.mavendemo.dao;

import java.util.List;

import com.yash.mavendemo.model.User;

public interface UserDAO {

	public boolean saveUser(User user);

	public List<User> listUsers();
}
