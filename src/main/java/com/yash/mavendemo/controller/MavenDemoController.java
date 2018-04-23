package com.yash.mavendemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yash.mavendemo.model.User;
import com.yash.mavendemo.service.UserService;

@RestController
@RequestMapping("/demo")
public class MavenDemoController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getData() {
		return "Hello";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@RequestBody User user) {
		boolean result = userService.saveUser(user);
		if (result)
			return "User saved successfully";
		else
			return "User not saved";
	}

	@RequestMapping("listUsers")
	public List<User> getAllUsers() {
		return userService.listUsers();
	}

}
