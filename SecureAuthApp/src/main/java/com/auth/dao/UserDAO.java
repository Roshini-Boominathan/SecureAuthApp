package com.auth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.auth.model.User;
import com.auth.util.DBUtil;

public class UserDAO {
	
	 // Method 1: Register a new user
    public boolean register(User user) {
        boolean status = false;

        try {
            // Get DB connection
            Connection con = DBUtil.getConnection();

            // SQL query
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

            // Prepare statement
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            // Execute query
            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Method 2: Validate login
    public boolean validate(User user) {
        boolean status = false;

        try {
            // Get DB connection
            Connection con = DBUtil.getConnection();

            // SQL query
            String sql = "SELECT password FROM users WHERE username=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                // BCrypt password check
                status = org.mindrot.jbcrypt.BCrypt
                        .checkpw(user.getPassword(), dbPassword);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
