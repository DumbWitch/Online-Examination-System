<%-- 
    Document   : editProfile
    Created on : Mar 4, 2019, 12:09:54 AM
    Author     : Afsana
--%>

<%@page import="demotest.User"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>

<%
           User user=dbObject.getUserDetails(session.getAttribute("uname").toString());
          
        %>
        <link rel="stylesheet" type="text/css" href="style-backend.css">
         <!-- SIDEBAR -->
			<div class="sidebar" style="background-image: url(sidebar-1.jpg)">
				<div class="sidebar-background" >
					<h2 class="logo-text">
						Online Examination System
					</h2>

					<div class="left-menu">
						<a class="active" href="admin.jsp"><h2>Profile</h2></a>
						<a href="courses.jsp"><h2>Courses</h2></a>
                                                <a href="question.jsp"><h2>Questions</h2></a>
						<a href="Account.jsp"><h2>Accounts</h2></a>
					</div>
				</div>
			</div>
            <!-- CONTENT AREA -->
			<div class="content-area">
                            <div class="panel" style="float: left;max-width: 600px">
        
                                
       
               <!-- Start of Edit Form --->
               <div class="title">Edit Profile</div>
               <div class="central-div form-style-6" style="position:inherit;margin-top: 70px;" >
                    <form action="EditAdmin" method="post">
                        
                        <input type="hidden" name="utype" value="<%=user.getType()%>">
				<table>
					
					<tr>
						<td>
							<label>First Name</label>
						</td>
						<td>
                                                    <input type="text" name="fname" value="<%=user.getFirstName() %>" class="text" placeholder="First Name">
						</td>
					</tr>
					<tr>
						<td>
							<label>Last Name</label>
						</td>
						<td>
                                                    <input type="text"  name="lname" value="<%=user.getLastName() %>" class="text" placeholder="Last Name">
						</td>
					</tr>
					
					<tr>
						<td>
							<label>User Name</label>
						</td>
						<td>
                                                    <input type="text" name="uname" value="<%=user.getUserName() %>" class="text" placeholder="User Name">
						</td>
					</tr>
					
					
					<tr>
						<td>
							<label>Password</label>
						</td>
						<td>
                                                    <input type="password" value="<%=user.getPassword() %>" name="pass" class="text" placeholder="Password">
						</td>
					</tr>
                                         <tr>
						<td>
							<label>Address</label>
						</td>
						<td>
                                                    <input type="text" name="address" value="<%=user.getAddress() %>" class="text" placeholder="Address">
						</td>
					</tr>
                                        <tr>
						<td>
							<label>Contact No</label>
						</td>
						<td>
                                                    <input type="text" name="contactno" value="<%=user.getContact() %>" class="text" placeholder="Contact No">
						</td>
					</tr>
                                        
                                       
					<tr>
						<td>
						</td>
						<td>
							<center>
							
							<input type="submit" value="Done" class="button">
							</center>
						</td>
					</tr>
				</table>
			</form>
		</div>

                
               <%
           }
           %>
    
        </div>
    </div>