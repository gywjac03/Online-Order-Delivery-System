/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsersFacade;
import model.UsersInfo;

/**
 *
 * @author ganye
 */
@WebServlet(name = "CustomerEditProfile", urlPatterns = {"/CustomerEditProfile"})
public class CustomerEditProfile extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UsersInfo user = (UsersInfo) session.getAttribute("user");
        
        try (PrintWriter out = response.getWriter()) {
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phonenumber = request.getParameter("phonenumber");
                String password = request.getParameter("password");
                String newPassword = request.getParameter("newPassword");
                
                // Validate the old password matches the one stored in the database
                if (!user.getPassword().equals(password)) {  
                    request.setAttribute("errorMessage5", "Old password is incorrect.");
                    request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
                    return;
                }

                // Email validation
                if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    request.setAttribute("errorMessage5", "Invalid email format.");
                    request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
                    return;
                }

                // Phone number validation
                if (phonenumber != null && !phonenumber.matches("\\d{10,15}")) {
                    request.setAttribute("errorMessage5", "Phone number must be numeric and contain 10 to 15 digits.");
                    request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
                    return;
                }

                // Update the user's email, address, and phone number if provided
                user.setEmail(email);
                user.setAddress(address);
                user.setPhonenumber(phonenumber);
                
                // If a new password is provided, update it
                if (newPassword != null && !newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }

                // Update the user in the database
                usersFacade.edit(user);
                session.setAttribute("user", user);

                // Add success message and redirect to avoid IllegalStateException
                session.setAttribute("message", "Profile updated successfully!");
                response.sendRedirect("customerEditProfile.jsp");
            }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
