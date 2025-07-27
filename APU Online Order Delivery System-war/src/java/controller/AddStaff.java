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

@WebServlet(name = "AddStaff", urlPatterns = {"/AddStaff"})
public class AddStaff extends HttpServlet {

    @EJB
    private UsersFacade userInfoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String role = request.getParameter("role");
        String ic = request.getParameter("ic"); // Added IC parameter
        String gender = request.getParameter("gender"); // Added gender parameter

        try (PrintWriter out = response.getWriter()) {
            try {
                // Input validation
                if (id == null || id.trim().isEmpty()) {
                    throw new Exception("ID is required.");
                }
                if (password == null || password.trim().isEmpty()) {
                    throw new Exception("Password is required.");
                }
                if (email == null || email.trim().isEmpty()) {
                    throw new Exception("Email is required.");
                }
                if (address == null || address.trim().isEmpty()) {
                    throw new Exception("Address is required.");
                }
                if (phonenumber == null || phonenumber.trim().isEmpty()) {
                    throw new Exception("Phone number is required.");
                }
                if (ic == null || ic.trim().isEmpty()) {
                    throw new Exception("IC is required.");
                }
                if (gender == null || gender.trim().isEmpty()) {
                    throw new Exception("Gender is required.");
                }

                // Check if ID already exists
                UsersInfo existingUser = userInfoFacade.find(id);
                if (existingUser != null) {
                    throw new Exception("Staff ID already exists.");
                }

                // Password validation
                if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[A-Za-z].*")) {
                    throw new Exception("Password must be at least 8 characters long and include both letters and numbers.");
                }

                // Phone number validation
                if (!phonenumber.matches("\\d{10,15}")) {
                    throw new Exception("Phone number must be numeric and 10 to 15 digits long.");
                }

                // Email validation
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    throw new Exception("Invalid email format.");
                }

                // Validate role
                if (role == null || (!role.equals("admin") && !role.equals("delivery_staff"))) {
                    throw new Exception("Invalid role selected.");
                }

                // Create new staff member with selected role, IC, and gender
                // Create new staff member with selected role, IC, and gender
                UsersInfo newStaff = new UsersInfo(id, password, ic, gender, email, address, phonenumber, role);
                userInfoFacade.create(newStaff);


                // Redirect to success page or display success message
                request.getRequestDispatcher("addstaff.jsp").include(request, response);
                out.println("<br><br>Staff added successfully with ID: " + id + " and Role: " + role);
            } catch (Exception e) {
                // Redirect back with error message
                request.getRequestDispatcher("addstaff.jsp").include(request, response);
                out.println("<br><br>Error adding staff: " + e.getMessage());
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
        return "AddStaff servlet to add staff details with role selection";
    }
}

