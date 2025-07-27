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
import model.OrderItems;
import model.OrderItemsFacade;

/**
 *
 * @author ganye
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the order ID from the request parameter
            Long orderId = Long.parseLong(request.getParameter("orderId"));

            // Find the order by ID
            OrderItems order = orderItemsFacade.find(orderId);

            if (order != null) {
                // Delete the order if it exists
                orderItemsFacade.remove(order);

                // Redirect back to the orders page after deletion
                response.sendRedirect("ViewOrders");
            } else {
                // If the order is not found, redirect to error page
                response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that handles deleting orders";
    }
}
