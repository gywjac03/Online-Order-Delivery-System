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
import model.UsersFacade;
import model.UsersInfo;

@WebServlet(name = "DeleteCustomer", urlPatterns = {"/DeleteCustomer"})
public class DeleteCustomer extends HttpServlet {
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
                UsersInfo customer = usersFacade.findById(id);

                if (customer != null) {
                    // Delete the customer
                    usersFacade.remove(customer);
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
        request.getRequestDispatcher("searchcustomer.jsp").forward(request, response);
    }
}


