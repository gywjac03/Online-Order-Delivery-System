/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductFacade;

/**
 *
 * @author ganye
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");
        String category = request.getParameter("category");
        List<Product> results;

        if ("All".equals(category)) {
            // Return all products if "All Categories" is selected
            results = productFacade.findAll();
        } else if (category != null && !category.isEmpty()) {
            // Search by category
            results = productFacade.findProductsByCategory(category);
        } else if (query != null && !query.trim().isEmpty()) {
            // Search by name
            results = productFacade.searchProducts(query);
        } else {
            results = new ArrayList<>(); // Return an empty list if no query or category is provided
        }

        // Set results as a request attribute and forward to the JSP
        request.setAttribute("results", results);
        request.getRequestDispatcher("searchproduct.jsp").forward(request, response);
    }
}





