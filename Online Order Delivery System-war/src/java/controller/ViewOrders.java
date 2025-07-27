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

@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;

    // Centralized request processing method for both GET and POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        // Retrieve the logged-in user's username from session
        UsersInfo user = (UsersInfo) session.getAttribute("user");

        if (user == null) {
            // If no user is logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        String username = user.getId();  // Assuming 'id' is the username
        String searchQuery = request.getParameter("searchQuery"); // Get the search query from the request

        try {
            List<OrderItems> orders;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                // If there's a search query, search by product name or order ID
                try {
                    Long orderId = Long.parseLong(searchQuery); // Try to parse it as order ID
                    orders = orderItemsFacade.getOrdersByOrderIdAndUser(orderId, username);
                } catch (NumberFormatException e) {
                    // If it's not a valid order ID, search by product name
                    orders = orderItemsFacade.getOrdersByProductNameAndUser(searchQuery, username);
                }
            } else {
                // If there's no search query, show all orders
                orders = orderItemsFacade.getOrdersForUser(username);
            }
            
            // Set the orders as a request attribute to be accessed in the JSP page
            request.setAttribute("orders", orders);
            
            // Forward to the JSP page to display the orders
            RequestDispatcher dispatcher = request.getRequestDispatcher("vieworders.jsp");
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
        return "Servlet that handles viewing orders for a user";
    }
}

