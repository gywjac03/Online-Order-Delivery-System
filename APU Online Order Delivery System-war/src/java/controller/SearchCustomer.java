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
import model.UsersFacade;
import model.UsersInfo;

/**
 *
 * @author ganye
 */
@WebServlet(name = "SearchCustomer", urlPatterns = {"/SearchCustomer"})
public class SearchCustomer extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the customer ID (name) from the request
        String customerId = request.getParameter("id");

        try (PrintWriter out = response.getWriter()) {
            // Check if the customer ID is provided
            if (customerId == null || customerId.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Please enter a customer ID to search.");
                request.getRequestDispatcher("searchcustomer.jsp").forward(request, response);
                return;
            }

            try {
                // Search for the customer by ID (assuming you have a method to find by ID)
                UsersInfo customer = usersFacade.findById(customerId);

                if (customer == null) {
                    // If no customer found, set an error message
                    request.setAttribute("errorMessage", "No customer found with the provided ID.");
                } else {
                    // Check if the customer has the "customer" role
                    if (!customer.getRoles().contains("customer")) {
                        request.setAttribute("errorMessage", "User is not a customer.");
                    } else {
                        // If customer found and has "customer" role, set the customer as a result
                        request.setAttribute("customer", customer);
                    }
                }

                // Forward the request to the searchcustomer.jsp page
                request.getRequestDispatcher("searchcustomer.jsp").forward(request, response);

            } catch (Exception e) {
                // Catch any exception and display an error message
                request.setAttribute("errorMessage", "Error while searching for the customer: " + e.getMessage());
                request.getRequestDispatcher("searchcustomer.jsp").forward(request, response);
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



   