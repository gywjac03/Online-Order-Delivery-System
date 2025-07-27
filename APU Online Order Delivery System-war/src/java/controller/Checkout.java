/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderItems;
import model.OrderItemsFacade;
import model.Product;
import model.ProductFacade;
import model.UsersFacade;
import model.UsersInfo;

/**
 *
 * @author ganye
 */

    
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    @EJB
    private OrderItemsFacade orderFacade;

    @EJB
    private ProductFacade productFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Retrieve product ID and adjusted quantity from the request
            Long productId = Long.parseLong(request.getParameter("productId"));
            int adjustedQuantity = Integer.parseInt(request.getParameter("adjustedQuantity"));

            // Debugging logs
            System.out.println("Product ID: " + productId);
            System.out.println("Adjusted Quantity: " + adjustedQuantity);

            // Find the product by its ID
            Product product = productFacade.find(productId);
            if (product == null) {
                // Handle the case where the product is not found
                System.err.println("Product not found with ID: " + productId); // Logging for debugging
                response.sendRedirect("error.jsp");
                return;
            }

            // Retrieve the logged-in user's info from the session
            HttpSession session = request.getSession();
            UsersInfo user = (UsersInfo) session.getAttribute("user");
            if (user == null) {
                // Handle the case where the user is not logged in
                System.err.println("User session not found"); // Logging for debugging
                response.sendRedirect("login.jsp");
                return;
            }

            // Create a new order with product, adjusted quantity, price, shipping address, and user
            OrderItems order = new OrderItems(product, adjustedQuantity, product.getPrice(), user.getAddress(), user);

            // Save the order
            orderFacade.create(order);

            // Reduce the product's quantity
            product.setQuantity(product.getQuantity() - adjustedQuantity);
            productFacade.edit(product);

            // Set the order as a request attribute to pass it to the JSP page
            request.setAttribute("order", order);

            // Forward the request to the payment page
            request.getRequestDispatcher("payment.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid product ID or quantity format
            System.err.println("Error parsing productId or adjustedQuantity: " + e.getMessage()); // Logging
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            // Log the exception and redirect to the error page
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

