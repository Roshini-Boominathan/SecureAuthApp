package com.auth.controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.auth.dao.UserDAO;
import com.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet  {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        // Get form values
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        // Hash password using BCrypt
	        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

	        // Create User object
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(hashedPassword);

	        // Call DAO
	        UserDAO dao = new UserDAO();
	        boolean success = dao.register(user);

	        // Redirect based on result
	        if (success) {
	            response.sendRedirect("login.jsp");
	        } else {
	            response.sendRedirect("register.jsp?error=1");
	        }
	    }
}
