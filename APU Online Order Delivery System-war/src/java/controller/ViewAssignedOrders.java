package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderItems;
import model.OrderItemsFacade;
import model.UsersInfo;

@WebServlet("/ViewAssignedOrders")
public class ViewAssignedOrders extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;

    // Centralized request processing method for both GET and POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        // Retrieve the logged-in user's information from session
        UsersInfo user = (UsersInfo) session.getAttribute("user");

        if (user == null) {
            // If no user is logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        String delivererId = user.getId();  // Assuming 'id' is the username or deliverer's unique ID

        try {
            // Fetch orders assigned to this delivery staff using the deliverer's ID
            List<OrderItems> orders = orderItemsFacade.getOrdersByDeliverer(delivererId);

            // Set the orders as a request attribute to be accessed in the JSP page
            request.setAttribute("orders", orders);

            // Handle payment status update
            String paymentOrderId = request.getParameter("paymentOrderId");
            if (paymentOrderId != null) {
                updatePaymentStatus(paymentOrderId);
            }

            // Handle delivery status update
            String deliveredOrderId = request.getParameter("deliveredOrderId");
            if (deliveredOrderId != null) {
                updateDeliveryStatus(deliveredOrderId);
            }

            // Forward to the JSP page to display the orders
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewassignedorders.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching orders");
        }
    }

    private void updatePaymentStatus(String orderId) {
        OrderItems order = orderItemsFacade.find(Long.parseLong(orderId));
        if (order != null && "Pending".equals(order.getPaymentStatus())) {
            order.setPaymentStatus("Done");
            orderItemsFacade.edit(order);
        }
    }

    private void updateDeliveryStatus(String orderId) {
        OrderItems order = orderItemsFacade.find(Long.parseLong(orderId));
        if (order != null && "Shipped".equals(order.getDeliveryStatus())) {
            order.setDeliveryStatus("Delivered");
            order.setDeliveryDate(new java.util.Date()); // Set delivery date when marked as delivered
            orderItemsFacade.edit(order);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Call processRequest from doGet
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Call processRequest from doPost
    }

    @Override
    public String getServletInfo() {
        return "Servlet that handles viewing orders assigned to a delivery staff";
    }
}
