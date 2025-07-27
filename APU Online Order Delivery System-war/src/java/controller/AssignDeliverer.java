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
@WebServlet(name = "AssignDeliverer", urlPatterns = {"/assignDeliverer"})
public class AssignDeliverer extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Retrieve the selected deliverer and order ID from the form submission
            String delivererId = request.getParameter("deliverer");
            Long orderId = Long.parseLong(request.getParameter("orderId"));

            // Get the order item by ID
            OrderItems order = orderItemsFacade.find(orderId);
            if (order != null && order.getDeliveryStatus().equals("Pending")) {
                // Assign the selected deliverer to the order
                order.setDeliverer(delivererId);
                order.setDeliveryStatus("Shipped"); // Assuming the status changes when a deliverer is assigned
                orderItemsFacade.edit(order);
            }

            // Redirect to the all orders page after assignment
            response.sendRedirect("allorders");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error assigning deliverer");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that assigns a deliverer to an order";
    }
}

