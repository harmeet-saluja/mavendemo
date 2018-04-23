package com.yash.mavendemo.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yash.mavendemo.daoimpl.UserDAOImpl;
import com.yash.mavendemo.model.User;

public class UserServiceImplTest {

	@Mock
	UserDAOImpl userDAOImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_save_method_return_true_when_user_saved_successfully() {
		when(userDAOImpl.saveUser(any(User.class))).thenReturn(true);
		User user = new User();
		assertEquals(true, userDAOImpl.saveUser(user));
	}

	@Test
	public void test_save_method_return_false_when_user_is_not_saved() {
		when(userDAOImpl.saveUser(any(User.class))).thenReturn(false);
		User user = new User();
		assertEquals(false, userDAOImpl.saveUser(user));
	}

	@Test
	public void test_listUsers_method_return_users_list_present_in_database() {
		when(userDAOImpl.listUsers()).thenReturn(new ArrayList<User>());
		List<User> users = new ArrayList<User>();
		assertEquals(users, userDAOImpl.listUsers());
	}

}
