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
import model.OrderItems;
import model.OrderItemsFacade;
import model.UsersFacade;
import model.UsersInfo;

/**
 * A servlet to retrieve and display all orders.
 */
@WebServlet(name = "AllOrders", urlPatterns = {"/allorders"})
public class AllOrders extends HttpServlet {

    @EJB
    private OrderItemsFacade orderItemsFacade;
    
    @EJB
    private UsersFacade usersInfoFacade; // Assuming there is a facade for UsersInfo

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get all orders from the facade
            List<OrderItems> allOrders = orderItemsFacade.findAll();
            List<UsersInfo> deliveryStaff = usersInfoFacade.findByRole("delivery_staff"); // Fetch delivery staff based on role

            // Set the orders and delivery staff as request attributes to pass to the JSP
            request.setAttribute("allOrders", allOrders);
            request.setAttribute("deliveryStaff", deliveryStaff);

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("allorders.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching all orders");
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
        return "Servlet that displays all orders";
    }
}


