<%-- 
    Document   : Account
    Created on : Feb 27, 2019, 9:24:33 PM
    Author     : Afsana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="demotest.User"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="style-backend.css">
        <title>Accounts</title>
    </head>
    <body>
        <!-- SIDEBAR -->
			<div class="sidebar" style="background-image: url(sidebar-1.jpg)">
				<div class="sidebar-background" >
					<h2 class="logo-text">
						Online Examination System
					</h2>

					<div class="left-menu">
						<a  href="admin.jsp"><h2>Profile</h2></a>
						<a href="courses.jsp"><h2>Courses</h2></a>
                                                <a href="question.jsp"><h2>Questions</h2></a>
						<a class="active" href="Account.jsp"><h2>Accounts</h2></a>
					</div>
				</div>
			</div>
          <!-- CONTENT AREA -->
			<div class="content-area">
                            <div class="inner" style="margin-top: 50px">
                                <div class="title" style="margin-top: -30px">List of All Registered Persons</div>
       
                                <br>
                                <br>
                                <br/>
                                <a href="Registration_1.html" class="button"><span class="add-btn" style="margin-left: 80px;">Add New Person</span></a>
           <br>
           
           
                       <table id="one-column-emphasis" >
    <colgroup>
    	<col class="oce-first" />
    </colgroup>
    <thead>
    	<tr>
        	<th scope="col">Name</th>
                
            <th scope="col">Contact No</th>
            <th scope="col">Address</th>
            <th scope="col">Action</th>
            
        </tr>
    </thead>
    <tbody>
           <%
              ArrayList list=dbObject.getAllUsers();
              User user;
              for(int i=0;i<list.size();i++){
                  user=(User)list.get(i);
                  if(user.getUserId()!= Integer.parseInt(session.getAttribute("userId").toString())){
               %>
   
    	<tr>
        	<td><%=user.getFirstName()+" "+user.getLastName() %></td>
                <td><%=user.getContact() %></td>
                <td><%=user.getAddress() %></td>
                   <td>
                 <a href="DeleteAccount?id=<%=user.getUserId() %>" 
                  onclick="return confirm('Are you sure you want to delete this ?');">
                <div class="delete-btn" name ="del" style="max-width: 40px;font-size: 17px; padding: 3px">X</div>
                  
                </a></td>
            
        </tr>
         
               
               
               <%
                  }
                  } %>
               
                </tbody>
</table>
           
    
                            </div>
                        </div>
    </body>
</html>
