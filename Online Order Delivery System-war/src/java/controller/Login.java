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
import model.UsersInfo;
import model.UsersFacade;

/**
 *
 * @author ganye
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private UsersFacade usersInfoFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        
        try (PrintWriter out = response.getWriter()) {
            try{
                UsersInfo found = usersInfoFacade.find(x);
                if(found == null){
                    out.println("Incorrect Username!");
                    throw new Exception("Incorrect Username");  //logical error 1
                }
                if (y == null || !y.equals(found.getPassword())){  
                    out.println("Incorrect Password!");
                    throw new Exception("Incorrect password"); 
                }
                
                HttpSession a = request.getSession();
                a.setAttribute("user", found);
                String role = found.getRoles(); 

                // Redirect based on role
                switch (role) {
                    case "customer":
                        request.getRequestDispatcher("customerlinks.jsp").include(request, response);
                        break;
                    case "admin":
                        request.getRequestDispatcher("mStafflinks.jsp").include(request, response);
                        break;
                    case "delivery_staff":
                        request.getRequestDispatcher("DStaffAssignedOrders").include(request, response);
                        break;
                    default:
                        out.println("<br><br><br>Unknown role!");
                        return;
                }

                out.println("<br><br><br>Hello "+x+", welcome to APU Online Convenience Store!");
            }catch(Exception e){
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.println("<br><br><br>Sorry "+x+", invalid input!");
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
