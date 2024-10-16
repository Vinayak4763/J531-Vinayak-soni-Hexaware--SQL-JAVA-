package com.hexaware.ticketbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	public static Connection getDbConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbooking","root","W7301@jqir#");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}