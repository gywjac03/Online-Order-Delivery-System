/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CustomerReceipt", urlPatterns = {"/CustomerReceipt"})
public class CustomerReceipt extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long orderId = Long.parseLong(request.getParameter("orderId"));
        OrderItems order = orderItemsFacade.find(orderId);

        if (order != null) {
            // Set order as a request attribute to be used in the JSP page
            request.setAttribute("order", order);
            // Forward to the receipt page
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerreceipt.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
        }
    }
}
