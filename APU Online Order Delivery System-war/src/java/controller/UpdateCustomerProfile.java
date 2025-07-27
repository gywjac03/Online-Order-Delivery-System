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

@WebServlet(name = "UpdateCustomerProfile", urlPatterns = {"/UpdateCustomerProfile"})
public class UpdateCustomerProfile extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        // Get the customer ID from the request
        String customerId = request.getParameter("id");

        if (customerId == null || customerId.trim().isEmpty()) {
            response.sendRedirect("searchcustomer.jsp");
            return;
        }

        try {
            // Retrieve the customer information using the customer ID
            UsersInfo customer = usersFacade.findById(customerId);
            if (customer == null) {
                response.sendRedirect("searchcustomer.jsp");
                return;
            }

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                // Forward the customer information to the update page
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phonenumber = request.getParameter("phonenumber");
                String newPassword = request.getParameter("password");
                String newId = request.getParameter("id");  // Get the new ID from the form

                boolean isUpdated = false; // Flag to check if any update happened

                // Check if the new ID already exists in the system
                if (newId != null && !newId.equals(customerId)) {
                    UsersInfo existingCustomer = usersFacade.findById(newId);
                    if (existingCustomer != null) {
                        request.setAttribute("errorMessage5", "The new username (ID) already exists.");
                        request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
                        return;
                    } else {
                        // If ID is unique, update it
                        customer.setId(newId);
                        isUpdated = true;
                    }
                }

                // Email validation
                if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    request.setAttribute("errorMessage5", "Invalid email format.");
                    request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
                    return;
                }

                // Phone number validation
                if (phonenumber != null && !phonenumber.matches("\\d{10,15}")) {
                    request.setAttribute("errorMessage5", "Phone number must be numeric and contain 10 to 15 digits.");
                    request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
                    return;
                }

                // Directly update the password if newPassword is provided
                if (newPassword != null && !newPassword.trim().isEmpty() && !newPassword.equals(customer.getPassword())) {
                    customer.setPassword(newPassword); // Assumes there’s a setPassword method
                    isUpdated = true;
                }

                // Update the customer's email, address, and phone number if provided
                if (email != null && !email.equals(customer.getEmail())) {
                    customer.setEmail(email);
                    isUpdated = true;
                }
                if (address != null && !address.equals(customer.getAddress())) {
                    customer.setAddress(address);
                    isUpdated = true;
                }
                if (phonenumber != null && !phonenumber.equals(customer.getPhonenumber())) {
                    customer.setPhonenumber(phonenumber);
                    isUpdated = true;
                }

                // If any update has happened, save the changes
                if (isUpdated) {
                    usersFacade.edit(customer);
                    session.setAttribute("message", "Profile updated successfully!");
                } else {
                    session.setAttribute("message", "No changes made to the profile.");
                }

                // Forward to the same page to show the message
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Add a more detailed error message for debugging
            request.setAttribute("errorMessage", "Error while updating the customer: " + e.getMessage());
            request.getRequestDispatcher("searchcustomer.jsp").forward(request, response);
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
        return "Update customer profile servlet";
    }
}



