package com.yash.mavendemo.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.yash.mavendemo.dao.UserDAO;
import com.yash.mavendemo.model.User;
import com.yash.mavendemo.util.DBUtilJndi;

@Repository
public class UserDAOImpl implements UserDAO {

	Logger logger = Logger.getLogger(this.getClass().getName());

	public boolean saveUser(User user) {
		PreparedStatement pstmt = DBUtilJndi.getPreparedStatement("INSERT INTO users(name,email) VALUES (?,?)");
		logger.info("PreparedStatment created.");
		int result = 0;
		try {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			result = pstmt.executeUpdate();
			logger.info("User saved successfully.");
			DBUtilJndi.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result == 1;
	}

	public List<User> listUsers() {
		PreparedStatement pstmt = DBUtilJndi.getPreparedStatement("SELECT * FROM users");
		logger.info("PreparedStatemenet created");
		List<User> userList = new ArrayList<>();
		User user = null;
		try (ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				userList.add(user);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DBUtilJndi.close();
		}
		return userList;
	}

}
