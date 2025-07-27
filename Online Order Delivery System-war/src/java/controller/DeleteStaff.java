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
@WebServlet(name = "DeleteStaff", urlPatterns = {"/DeleteStaff"})
public class DeleteStaff extends HttpServlet {
    @EJB
    private UsersFacade usersFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String message = null;

        try {
            if (id != null && !id.trim().isEmpty()) {
                UsersInfo staff = usersFacade.findById(id);

                if (staff != null) {
                    // Delete the customer
                    usersFacade.remove(staff);
                    message = "Deleted User Successfully.";
                } else {
                    message = "Customer not found. Unable to delete.";
                }
            } else {
                message = "Invalid Customer ID. Unable to delete.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "An unexpected error occurred while deleting the customer.";
        }

        // Forward back to the same page with a message
        request.setAttribute("deleteMessage", message);
        request.getRequestDispatcher("searchstaff.jsp").forward(request, response);
    }
}
