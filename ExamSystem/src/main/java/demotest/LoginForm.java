/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demotest;


import java.io.IOException;
import static java.lang.System.out;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import demotest.User;
/**
 * Servlet implementation class LoginController
 */
public class LoginForm extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                String user = request.getParameter("username");
		String pass =request.getParameter("password");
		
		// Connect to mysql and verify username password
		
		try {
                    DatabaseClass dbObject = new DatabaseClass();
                       HttpSession session=request.getSession();
                       session.setAttribute("userStatus", "1");
                       session.setAttribute("userId",dbObject.getUserId(request.getParameter("username")));
                      
		    if(dbObject.loginValidate(user, pass)){  
                      
                   
                    request.setAttribute("userId",dbObject.getUserId(request.getParameter("username")));
                     
                       String userid = session.getAttribute("userId").toString();
                      
                    
                        if (session.getAttribute("userStatus")!= null) {
                              String loginConfirm = session.getAttribute("userStatus").toString();
                              if (loginConfirm.equals("1")) {

                                    if (dbObject.getUserType(userid).equals("admin")) {
                                        session.setAttribute("uname",user);
                                        
                                        RequestDispatcher req = request.getRequestDispatcher("admin.jsp");
                                        req.include(request,response);
                                      
                                    } else if (dbObject.getUserType(userid).equals("student")) {
                                        session.setAttribute("uname",user);
                                        RequestDispatcher req = request.getRequestDispatcher("student.jsp");
                                         req.include(request, response);
                                    }

                              } else if (!loginConfirm.equals("1")) {
                                      response.sendRedirect("login.html");
                                     }
                        } else {
                            response.sendRedirect("login.html");
                        }


                    }else{
                    
                         request.getSession().setAttribute("userStatus", "-1");
                        response.sendRedirect("login.html");
                    
                    
                    }
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
