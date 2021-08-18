/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demotest;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import demotest.LoginForm;
import demotest.User;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Afsana
 */
public class AdminProfile extends HttpServlet {

 
    protected void doGet (HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException{  
      
        response.setContentType("text/html;charset=UTF-8");
      try{  PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
        
             DatabaseClass dbObject ;
             dbObject = new DatabaseClass();
            
             out.println(
                   "<html><title>Welcome Admin</title>\n"
                        +"<head>\n"+
	           "<link rel='stylesheet' type='text/css' href='style-backend.css'>\n" +
                    "</head>\n"+
                   "<body>\n"+
	                "<div class='top-area'>\n" +
	                    "<center><h2>Admin Panel</h2></center> \n"
                              + "<a href='#' class='button' style='float: right;background:crimson;color:white'>Logout</a>\n"+
	                "</div>\n"+
                        "<div class='sidebar'style='background-image: url(sidebar-1.jpg)'>\n" +
                            "<div class='sidebar-background'>\n" +
                                       "<h2 class='logo-text'>\n" +
                                            "Online Examination System\n" +
                                        "</h2>\n" +
                              "\n" +
                                               "<div class='left-menu'>\n" +
                                               "<a class='active' href='AdminProfile'><h2>Profile</h2></a>\n" +
                                               "<a href='#'><h2>Courses</h2></a>\n" +
                                               "<a href='#'><h2>Questions</h2></a>\n" +
                                               "<a href='#'><h2>Accounts</h2></a>\n" +
                                               "</div>\n" +
"				</div>\n" +
"			</div>\n"+
                        "</body>\n"+
       "</html>"
               );
                HttpSession session=request.getSession();
                String name=(String)session.getAttribute("uname");
		
		out.print("Hello, "+name+" Welcome to Profile");
                User user= dbObject.getUserDetails((String) request.getAttribute("uname"));
                out.println( "<div class='title'>Profile</div>");
                out.println(" <div class='profile'>\n" );
               out.println( " <h2><span class='tag'>Your Name</span><span class='val'>"+user.getFirstName()+" "+user.getLastName()+"></span><br/>\n" +
                             "<span class='tag'>Contact No</span><span class='val'>" +user.getContact()+ "</span><br/>\n"+
                              "<span class='tag'>City</span><span class='val'>"+user.getAddress()+"</span><br/>\n" +
   
                               " </div>");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
          
         
           
     
        
    }
 }
   

