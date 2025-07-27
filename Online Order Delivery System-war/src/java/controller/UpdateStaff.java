/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsersFacade;
import model.UsersInfo;

@WebServlet(name = "UpdateStaff", urlPatterns = {"/UpdateStaff"})
public class UpdateStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String staffId = request.getParameter("id");

        if (staffId == null || staffId.trim().isEmpty()) {
            response.sendRedirect("searchstaff.jsp");
            return;
        }

        try {
            UsersInfo staff = usersFacade.findById(staffId);
            if (staff == null) {
                response.sendRedirect("searchstaff.jsp");
                return;
            }

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                request.setAttribute("staff", staff);
                request.getRequestDispatcher("updatestaff.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phonenumber = request.getParameter("phonenumber");
                String newPassword = request.getParameter("password");
                String newId = request.getParameter("id");

                boolean isUpdated = false;

                // Handle staff ID update logic
                if (newId != null && !newId.equals(staffId)) {
                    UsersInfo existingStaff = usersFacade.findById(newId);
                    if (existingStaff != null) {
                        request.setAttribute("errorMessage5", "The new Staff ID already exists.");
                        request.getRequestDispatcher("updatestaff.jsp").forward(request, response);
                        return;
                    } else {
                        staff.setId(newId);
                        isUpdated = true;
                    }
                }

                // Validate email format
                if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    request.setAttribute("errorMessage5", "Invalid email format.");
                    request.getRequestDispatcher("updatestaff.jsp").forward(request, response);
                    return;
                }

                // Validate phone number format
                if (phonenumber != null && !phonenumber.matches("\\d{10,15}")) {
                    request.setAttribute("errorMessage5", "Phone number must be numeric and contain 10 to 15 digits.");
                    request.getRequestDispatcher("updatestaff.jsp").forward(request, response);
                    return;
                }

                // Update the password if necessary
                if (newPassword != null && !newPassword.trim().isEmpty() && !newPassword.equals(staff.getPassword())) {
                    staff.setPassword(newPassword);
                    isUpdated = true;
                }

                // Update other fields if necessary
                if (email != null && !email.equals(staff.getEmail())) {
                    staff.setEmail(email);
                    isUpdated = true;
                }
                if (address != null && !address.equals(staff.getAddress())) {
                    staff.setAddress(address);
                    isUpdated = true;
                }
                if (phonenumber != null && !phonenumber.equals(staff.getPhonenumber())) {
                    staff.setPhonenumber(phonenumber);
                    isUpdated = true;
                }

                if (isUpdated) {
                    usersFacade.edit(staff);
                    session.setAttribute("message", "Staff profile updated successfully!");
                } else {
                    session.setAttribute("message", "No changes made to the profile.");
                }

                request.setAttribute("staff", staff);
                request.getRequestDispatcher("updatestaff.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error while updating the staff: " + e.getMessage());
            request.getRequestDispatcher("searchstaff.jsp").forward(request, response);
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
        return "Update staff profile servlet";
    }
}

