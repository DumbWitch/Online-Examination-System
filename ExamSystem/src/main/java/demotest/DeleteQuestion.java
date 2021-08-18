/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demotest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Afsana
 */
public class DeleteQuestion extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int qid = Integer.parseInt(request.getParameter("qid"));
    try {
        DatabaseClass dbObject = new DatabaseClass();
        dbObject.delQuestion(qid);
         RequestDispatcher req = request.getRequestDispatcher("question.jsp");
          req.include(request,response);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    }

    

