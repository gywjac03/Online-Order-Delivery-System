/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Ratings;
import model.RatingsFacade;
import model.UsersFacade;
import model.UsersInfo;

/**
 *
 * @author ganye
 */
@WebServlet(name = "SubmitReview", urlPatterns = {"/SubmitReview"})
public class SubmitReview extends HttpServlet {

    @EJB
    private RatingsFacade ratingsFacade;  // EJB to interact with Ratings entity
    
    @EJB
    private UsersFacade usersFacade;  // EJB to check user session (if needed)

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        
        // Retrieve the logged-in user's information from session
        UsersInfo user = (UsersInfo) session.getAttribute("user");
                // Check if user is logged in
        if (user == null) {
            response.sendRedirect("login.jsp");  // Redirect to login if no user is found
            return;
        }

        String username = user.getId();
        String ratingStr = request.getParameter("rating");
        String comments = request.getParameter("comments");

        try (PrintWriter out = response.getWriter()) {
            // Validate input
            if (username == null || username.trim().isEmpty()) {
                out.println("Username is required.");
                throw new Exception("Missing username");
            }
            if (ratingStr == null || ratingStr.trim().isEmpty()) {
                out.println("Rating is required.");
                throw new Exception("Missing rating");
            }
            if (comments == null || comments.trim().isEmpty()) {
                out.println("Comment is required.");
                throw new Exception("Missing comment");
            }

            int rating = Integer.parseInt(ratingStr);

            if (rating < 1 || rating > 5) {
                out.println("Rating must be between 1 and 5.");
                throw new Exception("Invalid rating");
            }

            // Create a new review object
            Ratings review = new Ratings();
            review.setUsername(username);
            review.setRating(rating);
            review.setComments(comments);

            // Save the review to the database
            ratingsFacade.create(review);

            // Redirect to a confirmation page or display a success message
            out.println("Review submitted successfully!");
            request.getRequestDispatcher("review.jsp").include(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("review.jsp").include(request, response);
            out.println("Sorry, invalid input: " + e.getMessage());
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
        return "Submit Review Servlet";
    }
}

