<%-- 
    Document   : courses
    Created on : Mar 1, 2019, 10:35:54 PM
    Author     : Afsana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>
 <link rel="stylesheet" type="text/css" href="style-backend.css">
<!DOCTYPE html>
<!-- SIDEBAR -->
			<div class="sidebar" style="background-image: url(sidebar-1.jpg)">
				<div class="sidebar-background" >
					<h2 class="logo-text">
						Online Examination System
					</h2>

					<div class="left-menu">
						<a  href="admin.jsp"><h2>Profile</h2></a>
						<a class="active" href="courses.jsp"><h2>Courses</h2></a>
                                                <a href="question.jsp"><h2>Questions</h2></a>
						<a href="Account.jsp"><h2>Accounts</h2></a>
					</div>
				</div>
			</div>
  <!-- CONTENT AREA -->
			<div class="content-area">
                            
                            <div class="panel" style="max-width: 420px;float:right">
                                <div class="title">
                                   All Courses
                                </div>
                                <table id="one-column-emphasis" style="min-width: 400px;margin: 5px; margin-top: 35px" >
    <colgroup>
    	<col class="oce-first" />
    </colgroup>
    <thead>
    	<tr>
        	<th scope="col">Courses</th>
                <th scope="col">Total Marks</th>
                <th scope="col">Action</th>
            
        </tr>
    </thead>
    
                                       
        <% 
            ArrayList list=dbObject.getAllCourses();
            for(int i=0;i<list.size();i=i+2){
        %>
        <tr>
        <td><%=list.get(i)%></td>
        <td><%=list.get(i+1)%></td>
        <td ><a  href="DeleteCourse?name=<%=list.get(i)%>" 
                onclick="return confirm('Are you sure you want to delete this ?');" class="del">
                <div class="delete-btn" style="max-width: 40px;font-size: 17px; padding: 3px">X</div>
            </a></td>
    </tr>
            <%
            }
            %>
            </table>
        </div>
        <div  class="panel form-style-6" style="max-width: 450px" >
           <div class="title">
                                   Add New Course
                                </div>
            <center>
               <form action="AddCourses" method="post" >
                   <br>
                   <table >
                       <tr>
                           <td><label>Course Name</label></td>
                           <td> <input type="text" name="coursename" class="text" placeholder="Course Name"  style="width: 230px;"></td>
                       </tr>
                       <tr>
                           <td><label>Total Marks</label></td>
                           <td><input type="text" name="totalmarks" class="text" placeholder="Total Marks" style="width: 230px;" ></td>
                       </tr>
                       <tr>
                           <td><label>Exam Time</label></td>
                           <td>
                                <input type="text" name="time" class="text" placeholder="MM" style="width: 230px;" >
                           </td>
                           
                       </tr>
                       <tr>
                           <td colspan="2"><input type="hidden" name="page" value="courses">
                    <input type="hidden" name="operation" value="addnew">
                       <center><input type="submit" class="form-button" value="Add" name="submit"></center></td>
                       </tr>
                   </table>

                </form>
            </center>
           
    </div>
     </div>
                       