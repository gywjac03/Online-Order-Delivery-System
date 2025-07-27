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
import model.Product;
import model.ProductFacade;

@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
public class DeleteProduct extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idStr = request.getParameter("id");
        String message = null;

        try {
            if (idStr != null && !idStr.trim().isEmpty()) {
                // Parse ID as Long
                Long id = Long.parseLong(idStr);

                // Find and delete the product
                Product product = productFacade.find(id);
                if (product != null) {
                    productFacade.remove(product);
                    message = "Deleted Product Successfully.";
                } else {
                    message = "Product not found. Unable to delete.";
                }
            } else {
                message = "Invalid Product ID. Unable to delete.";
            }
        } catch (NumberFormatException e) {
            message = "Invalid Product ID format.";
            e.printStackTrace(); // Log the exception
        } catch (Exception e) {
            message = "An unexpected error occurred while deleting the product.";
            e.printStackTrace(); // Log the exception
        }

        // Send message back to JSP
        request.setAttribute("deleteMessage", message);
        request.getRequestDispatcher("searchproduct.jsp").forward(request, response);
    }
}


