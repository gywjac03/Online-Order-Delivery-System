package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/DStaffAssignedOrders")
public class DStaffAssignedOrders extends HttpServlet {

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

            // Forward to the dStafflinks.jsp to display the orders
            RequestDispatcher dispatcher = request.getRequestDispatcher("dStafflinks.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching orders");
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
