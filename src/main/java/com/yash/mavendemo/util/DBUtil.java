package com.yash.mavendemo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBUtil {

	private static Logger logger = Logger.getLogger(DBUtil.class);

	private static Properties properties = null;
	private static PreparedStatement pstmt = null;
	private static Connection connection = null;

	static {
		properties = new Properties();
		try {
			properties.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			logger.info("jdbc.properties loaded");
			Class.forName(properties.getProperty("jdbc.driverName"));
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			String url = properties.getProperty("jdbc.url");
			connection = DriverManager.getConnection(url, username, password);
			logger.info("Connection created");
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	private DBUtil() {

	}

	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			pstmt = connection.prepareStatement(sql);
			logger.info("PreparedStatement created");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return pstmt;
	}

	public static void close() {
		try {
			pstmt.close();
			logger.info("pstmt closed");
			connection.close();
			logger.info("Connection closed");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}
}
