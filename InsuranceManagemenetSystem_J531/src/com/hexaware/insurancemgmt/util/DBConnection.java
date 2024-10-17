package com.hexaware.insurancemgmt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String driver = DBPropertyUtil.getPropertyString("driver.classname");
                String url = DBPropertyUtil.getPropertyString("url");
                String username = DBPropertyUtil.getPropertyString("username");
                String password = DBPropertyUtil.getPropertyString("password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
