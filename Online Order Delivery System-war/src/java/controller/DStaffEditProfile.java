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
import javax.servlet.http.HttpSession;
import model.UsersFacade;
import model.UsersInfo;

/**
 *
 * @author ganye
 */
@WebServlet(name = "DStaffEditProfile", urlPatterns = {"/DStaffEditProfile"})
public class DStaffEditProfile extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UsersInfo user = (UsersInfo) session.getAttribute("user");

        try (PrintWriter out = response.getWriter()) {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("dStaffEditProfile.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phonenumber = request.getParameter("phonenumber");
                String password = request.getParameter("password");
                String newPassword = request.getParameter("newPassword");

                // Validate the old password matches the one stored in the database
                if (!user.getPassword().equals(password)) {
                    request.setAttribute("errorMessage5", "Old password is incorrect.");
                    request.getRequestDispatcher("dStaffEditProfile.jsp").forward(request, response);
                    return;
                }

                // Email validation
                if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    request.setAttribute("errorMessage5", "Invalid email format.");
                    request.getRequestDispatcher("dStaffEditProfile.jsp").forward(request, response);
                    return;
                }

                // Phone number validation
                if (phonenumber != null && !phonenumber.matches("\\d{10,15}")) {
                    request.setAttribute("errorMessage5", "Phone number must be numeric and contain 10 to 15 digits.");
                    request.getRequestDispatcher("dStaffEditProfile.jsp").forward(request, response);
                    return;
                }

                // Update the user's email, address, and phone number if provided
                user.setEmail(email);
                user.setAddress(address);
                user.setPhonenumber(phonenumber);

                // If a new password is provided, update it
                if (newPassword != null && !newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }

                // Update the user in the database
                usersFacade.edit(user);
                session.setAttribute("user", user);

                // Add success message and redirect to avoid IllegalStateException
                session.setAttribute("message", "Profile updated successfully!");
                response.sendRedirect("dStaffEditProfile.jsp");
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
        return "Delivery Staff Profile Edit Servlet";
    }
}

