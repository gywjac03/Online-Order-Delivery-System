/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsersInfo;
import model.UsersFacade;

/**
 *
 * @author ganye
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @EJB
    private UsersFacade userInfoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form parameters including IC and gender
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String ic = request.getParameter("ic"); // New IC parameter
        String gender = request.getParameter("gender"); // New gender parameter
        String roles = "customer";  // Default role for users
        
        try (PrintWriter out = response.getWriter()) {
            try {
                // Check if all parameters are provided
                if (username == null || username.trim().isEmpty()) {
                    out.println("Username is required.");
                    throw new Exception("Missing username");
                }
                if (password == null || password.trim().isEmpty()) {
                    out.println("Password is required.");
                    throw new Exception("Missing password");
                }
                if (phonenumber == null || phonenumber.trim().isEmpty()) {
                    out.println("Phone number is required.");
                    throw new Exception("Missing phone number");
                }
                if (email == null || email.trim().isEmpty()) {
                    out.println("Email is required.");
                    throw new Exception("Missing email");
                }
                if (address == null || address.trim().isEmpty()) {
                    out.println("Address is required.");
                    throw new Exception("Missing address");
                }
                if (ic == null || ic.trim().isEmpty()) {
                    out.println("IC is required.");
                    throw new Exception("Missing IC");
                }
                if (gender == null || gender.trim().isEmpty()) {
                    out.println("Gender is required.");
                    throw new Exception("Missing gender");
                }

                // Check if username already exists
                UsersInfo found = userInfoFacade.find(username);
                if (found != null) {
                    out.println("Username already exists!");
                    throw new Exception("Username already exists");
                }

                // Password validation: minimum 8 characters, must include letters and numbers
                if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[A-Za-z].*")) {
                    out.println("Password must be at least 8 characters long and include both letters and numbers.");
                    throw new Exception("Invalid password");
                }

                // Phone number validation: must be numeric and between 10-15 digits
                if (!phonenumber.matches("\\d{10,15}")) {
                    out.println("Phone number must be numeric and 10 to 15 digits long.");
                    throw new Exception("Invalid phone number");
                }

                // Email validation: basic format check
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    out.println("Invalid email format.");
                    throw new Exception("Invalid email");
                }

                // Create new customer if all validations pass (remove balance, add ic and gender)
                userInfoFacade.create(new UsersInfo(username, password, ic, gender, email, address, phonenumber, roles));
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.println("<br><br><br>Registration successful for user: " + username);
            } catch (Exception e) {
                request.getRequestDispatcher("register.jsp").include(request, response);
                out.println("<br><br><br>Sorry, invalid input: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

