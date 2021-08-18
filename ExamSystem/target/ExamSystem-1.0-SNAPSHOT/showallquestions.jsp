<%-- 
    Document   : showallquestions
    Created on : Mar 3, 2019, 10:18:37 PM
    Author     : Afsana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="demotest.Questions"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
          <link rel="stylesheet" type="text/css" href="style-backend.css">
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
                                                <a class="active" href="question.jsp"><h2>Questions</h2></a>
						<a href="Account.jsp"><h2>Accounts</h2></a>
					</div>
				</div>
			</div>
            <!-- CONTENT AREA -->
            <div class="content-area" >
                <center>
        <%
           if(request.getParameter("coursename")!=null){
               ArrayList list=dbObject.getAllQuestion(request.getParameter("coursename"));
               for(int i=0;i<list.size();i++){
                   Questions question=(Questions)list.get(i);
                   
                   %>
                   <div class="question-panel">
						<div class="question" >
                                                    <label class="question-label"><%=question.getQuestionId() %></label>
						<%=question.getQuestion() %>	
                                             <a href="DeleteQuestion?qid=<%=question.getQuestionId() %>" 
                                                                             onclick="return confirm('Are you sure you want to delete this ?');" >
    <div class="delete-btn" style="position: absolute;right: 10px;top: -20px;">delete</div></a>
                                                </div>
						<div class="answer">
                                                        <label class="show"><%=question.getOpt1() %></label>
							<label class="show"><%=question.getOpt2() %></label>
							<label class="show"><%=question.getOpt3() %></label>
							<label class="show"><%=question.getOpt4() %></label>
                                                        <label class="show-correct"><%=question.getCorrect() %></label>
						</div>
					</div>
                   
                   <%
               }
          } %>
       </center>
            </div>
    </body>
</html>
