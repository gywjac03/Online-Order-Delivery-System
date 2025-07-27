/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Product;
import model.ProductFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // Create a new product object
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setDescription(description);

        try {
            // Persist the product to the database
            productFacade.create(product);

            // Add success message to session
            session.setAttribute("message", "Product added successfully!");
            response.sendRedirect("addproduct.jsp");
        } catch (Exception e) {
            // Add error message to session if there is an exception
            session.setAttribute("errorMessage", "Error adding product: " + e.getMessage());
            response.sendRedirect("addproduct.jsp");
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
        return "Servlet to handle adding products";
    }
}

