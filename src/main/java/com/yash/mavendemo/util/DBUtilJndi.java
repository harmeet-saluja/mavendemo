package com.yash.mavendemo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBUtilJndi {

	private static Logger logger = Logger.getLogger(DBUtilJndi.class);

	private static Connection connection = null;
	private static PreparedStatement pstmt = null;

	static {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			logger.info("Environment Context:" + envCtx);
			DataSource dataSource = (DataSource) envCtx.lookup("jdbc/users");
			logger.info("DataSource created:" + dataSource);
			try {
				connection = dataSource.getConnection();
				logger.info("Connection created" + connection);
			} catch (SQLException e) {
				logger.error("Connection creation failed:" + e.getMessage());
			}
		} catch (NamingException e) {
			logger.error("Naming Exception:" + e.getMessage());
		}
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
