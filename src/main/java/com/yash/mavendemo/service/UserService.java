package com.yash.mavendemo.service;

import java.util.List;

import com.yash.mavendemo.model.User;

public interface UserService {

	public boolean saveUser(User user);

	public List<User> listUsers();

}
