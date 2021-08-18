<%-- 
    Document   : admin
    Created on : Feb 25, 2019, 9:22:34 PM
    Author     : Afsana
--%>
<%@page import="demotest.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
        <link rel="stylesheet" type="text/css" href="style-backend.css">
 

    </head>
    <body>
      
        
         <%
      if (session != null) {
          
         if (session.getAttribute("uname") != null) {
            String name = (String) session.getAttribute("uname");
            int userId =dbObject.getUserId(name);
            
            User user=dbObject.getUserDetails(session.getAttribute("uname").toString());
           %>
          <div class="top-area">
		<center><h2>Student Panel</h2></center> <a href="controller.jsp?page=logout" class="button" style="float: right;background:crimson;color:white">Logout</a>
	</div>
            <!-- SIDEBAR -->
            <div class="sidebar" style="background-image: url(sidebar-1.jpg)">
				<div class="sidebar-background" >
					<h2 class="logo-text">
						Online Examination System
					</h2>
					<div class="left-menu">
						<a class="active" href="student.jsp"><h2>Profile</h2></a>
						<a href="exam.jsp"><h2>Exams</h2></a>
                                                <a href="results.jsp"><h2>Results</h2></a>
						
					</div>
				</div>
		
                       </div>
              <div class="content-area">
                            <div class="panel" style="float: left;max-width: 600px">
		<div class="title">Profile</div>
                <div class="profile ">
           
               
              <table>
                
              
              <tr> <td><h2><span class="tag">Your Name</span></td><td><span class="val"><h2><%=user.getFirstName()+" " %><%=user.getLastName() %></span></h2><td></tr>
              
               <tr><td><h2><span class="tag">Contact No </span></td><td><span class="val"><h2><%=user.getContact() %></span></h2></td></tr>
               
               <tr><td><h2><span class="tag">Address </span></td><td><span class="val"><h2><%=user.getAddress() %></span></h2></td><tr>
          
           </table>
               
           
                      </div>
             
                            </div>
                   </div>

       <%  } else {
            response.sendRedirect("login.html");
         }
      }
      %>
    </body>
</html>
