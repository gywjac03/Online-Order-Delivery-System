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
import javax.servlet.http.HttpSession;
import model.Product;
import model.ProductFacade;

/**
 *
 * @author ganye
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
public class UpdateProduct extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        // Get the product ID from the request
        String productId = request.getParameter("id");

        if (productId == null || productId.trim().isEmpty()) {
            response.sendRedirect("searchproduct.jsp");
            return;
        }

        try {
            // Retrieve the product information using the product ID
            Long id = Long.parseLong(productId);
            Product product = productFacade.find(id);
            if (product == null) {
                response.sendRedirect("searchproduct.jsp");
                return;
            }

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                // Forward the product information to the update page
                request.setAttribute("product", product);
                request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                String productName = request.getParameter("productName");
                String priceStr = request.getParameter("price");
                String quantityStr = request.getParameter("quantity");
                String category = request.getParameter("category");
                String description = request.getParameter("description");

                boolean isUpdated = false; // Track updates

                // Price validation
                double price = product.getPrice();
                if (priceStr != null && !priceStr.trim().isEmpty()) {
                    try {
                        price = Double.parseDouble(priceStr);
                        if (price != product.getPrice()) {
                            product.setPrice(price);
                            isUpdated = true;
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage5", "Invalid price format.");
                        request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                        return;
                    }
                }

                // Quantity validation
                int quantity = product.getQuantity();
                if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                    try {
                        quantity = Integer.parseInt(quantityStr);
                        if (quantity != product.getQuantity()) {
                            product.setQuantity(quantity);
                            isUpdated = true;
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage5", "Invalid quantity format.");
                        request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                        return;
                    }
                }

                // Update product fields
                if (productName != null && !productName.equals(product.getProductName())) {
                    product.setProductName(productName);
                    isUpdated = true;
                }
                if (category != null && !category.equals(product.getCategory())) {
                    product.setCategory(category);
                    isUpdated = true;
                }
                if (description != null && !description.equals(product.getDescription())) {
                    product.setDescription(description);
                    isUpdated = true;
                }

                // Save updates
                if (isUpdated) {
                    productFacade.edit(product);
                    session.setAttribute("message", "Product updated successfully!");
                } else {
                    session.setAttribute("message", "No changes made to the product.");
                }

                // Forward to the same page to show the message
                request.setAttribute("product", product);
                request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage5", "Error while updating the product: " + e.getMessage());
            request.getRequestDispatcher("searchproduct.jsp").forward(request, response);
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
        return "Update product profile servlet";
    }
}


