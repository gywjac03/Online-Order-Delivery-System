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

@WebServlet(name = "ViewProducts", urlPatterns = {"/viewproducts"})
public class ViewProducts extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the search parameters (if any)
        String searchName = request.getParameter("searchName");
        String searchCategory = request.getParameter("searchCategory");

        List<Product> productList;

        if (searchName != null || searchCategory != null) {
            // Search for products based on provided search parameters
            productList = productFacade.searchProducts(searchName, searchCategory);
        } else {
            // If no search parameters, retrieve all products
            productList = productFacade.findAll();
        }

        // Set the products as a request attribute
        request.setAttribute("products", productList);

        // Forward to the JSP page to display the products
        request.getRequestDispatcher("viewproducts.jsp").forward(request, response);
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
}
