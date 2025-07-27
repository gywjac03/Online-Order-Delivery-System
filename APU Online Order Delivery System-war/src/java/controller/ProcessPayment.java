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

@WebServlet(name = "ProcessPayment", urlPatterns = {"/ProcessPayment"})
public class ProcessPayment extends HttpServlet {

    @EJB
    private OrderItemsFacade orderFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the order ID and payment method
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        String paymentMethod = request.getParameter("paymentMethod");

        // Retrieve the order from the database
        OrderItems order = orderFacade.find(orderId);

        if (order != null) {
            if (paymentMethod.equals("card")) {
                // If card payment, validate the card details
                String cardNumber = request.getParameter("cardNumber");
                String cardName = request.getParameter("cardName");
                String cvv = request.getParameter("cvv");

                if (cardNumber != null && cardNumber.length() == 16 && 
                    cardName != null && !cardName.isEmpty() && 
                    cvv != null && cvv.length() == 3) {
                    // Set payment status to 'Done'
                    order.setPaymentStatus("Done");
                    orderFacade.edit(order); // Save the payment status

                    request.setAttribute("order", order);
                    request.getRequestDispatcher("checkoutConfirmation.jsp").forward(request, response);
                } else {
                    // Invalid card details, redirect to error page
                    response.sendRedirect("error.jsp");
                }
            } else if (paymentMethod.equals("cash")) {
                // For cash, set payment status to 'Pending'
                order.setPaymentStatus("Pending");
                orderFacade.edit(order); // Save the payment status

                request.setAttribute("order", order);
                request.getRequestDispatcher("checkoutConfirmation.jsp").forward(request, response);
            }
        } else {
            // Order not found, handle error
            response.sendRedirect("error.jsp");
        }
    }
}
