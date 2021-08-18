/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demotest;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        // TODO Auto-generated method stub
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
          
        if (first_name.isEmpty() || last_name.isEmpty() || username.isEmpty()
                || password.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            RequestDispatcher req = request.getRequestDispatcher("Registration_1.html");
            req.include(request, response);
        } else {

            try {
                DatabaseClass dbObject = new DatabaseClass();
               dbObject.addNewStudent( first_name,last_name,username,password,address, contact);
                RequestDispatcher req = request.getRequestDispatcher("Account.jsp");
            req.include(request, response);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
