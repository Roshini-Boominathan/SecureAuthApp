package com.auth.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	public static Connection getConnection() {
        Connection con = null;
        try {
            // 1. Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create Connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/authdb",
                "root",
                "root"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
