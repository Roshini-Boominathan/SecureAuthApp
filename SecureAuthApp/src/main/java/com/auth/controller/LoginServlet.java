package com.auth.controller;

import java.io.IOException;

import com.auth.dao.UserDAO;
import com.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        // Get login form values
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        // Create User object
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(password);

	        // Validate user
	        UserDAO dao = new UserDAO();
	        boolean validUser = dao.validate(user);

	        if (validUser) {
	            // Create session
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);

	            // Redirect to dashboard
	            response.sendRedirect("dashboard.jsp");
	        } else {
	            // Redirect back to login with error
	            response.sendRedirect("login.jsp?error=1");
	        }
	    }
}
