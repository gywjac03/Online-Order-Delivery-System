/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ratings;
import model.RatingsFacade;

/**
 *
 * @author ganye
 */
@WebServlet(name = "dsRatings", urlPatterns = {"/dsRatings"})
public class dsRatings extends HttpServlet {


    @EJB
    private RatingsFacade ratingsFacade;  // EJB to interact with the Ratings entity

    /**
     * Handles the HTTP <code>GET</code> method to display all ratings and comments.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve all ratings from the database
        List<Ratings> dsRatings = ratingsFacade.findAll();
        
        // Set the ratings as a request attribute
        request.setAttribute("dsRatings", dsRatings);
        
        // Forward the request to a JSP page to display the results
        request.getRequestDispatcher("dsrating.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method, if needed.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // Forwards POST requests to doGet
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Servlet that displays all ratings and comments.";
    }
}
