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
@WebServlet(name = "SearchStaff", urlPatterns = {"/SearchStaff"})
public class SearchStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the staff ID and role from the request
        String staffId = request.getParameter("id");
        String role = request.getParameter("role");

        try (PrintWriter out = response.getWriter()) {
            // Check if the staff ID is provided
            if (staffId == null || staffId.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Please enter a staff ID to search.");
                request.getRequestDispatcher("searchstaff.jsp").forward(request, response);
                return;
            }

            try {
                // Search for the staff by ID
                UsersInfo staff = usersFacade.findById(staffId);

                if (staff == null) {
                    // If no staff found, set an error message
                    request.setAttribute("errorMessage", "No staff found with the provided ID.");
                } else {
                    // Check if the staff has the requested role
                    if (!staff.getRoles().contains(role)) {
                        request.setAttribute("errorMessage", "User does not have the requested role: " + role);
                    } else {
                        // If staff found and has the correct role, set the staff as a result
                        request.setAttribute("staff", staff);
                    }
                }

                // Forward the request to the searchstaff.jsp page
                request.getRequestDispatcher("searchstaff.jsp").forward(request, response);

            } catch (Exception e) {
                // Catch any exception and display an error message
                request.setAttribute("errorMessage", "Error while searching for the staff: " + e.getMessage());
                request.getRequestDispatcher("searchstaff.jsp").forward(request, response);
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
        return "Servlet for searching staff by ID and role";
    }
}

