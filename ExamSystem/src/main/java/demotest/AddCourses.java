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

/**
 *
 * @author Afsana
 */
public class AddCourses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter() ;
         String cName = request.getParameter("coursename");
         int marks =Integer.parseInt( request.getParameter("totalmarks"));
         String time = request.getParameter("time");
         
         try{
             DatabaseClass dbObject = new DatabaseClass();
               dbObject.addNewCourse(cName,marks,time);
                RequestDispatcher req = request.getRequestDispatcher("courses.jsp");
            req.include(request, response);
         
         
         
         
         }catch(ClassNotFoundException e){
              e.printStackTrace();
           
         
         }  catch (SQLException ex) {
                Logger.getLogger(AddCourses.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
         }
           
        }
    

