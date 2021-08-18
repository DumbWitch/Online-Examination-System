
<%@page import="java.time.LocalTime"%>
<%@page import="demotest.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:useBean id="dbObject" class="demotest.DatabaseClass" scope="page"/>
<%
if(request.getParameter("page").toString().equals("login")){
if(dbObject.loginValidate(request.getParameter("username").toString(), request.getParameter("password").toString())){
    session.setAttribute("userStatus", "1");
    session.setAttribute("userId",dbObject.getUserId(request.getParameter("username")));
    String userid = session.getAttribute("userId").toString();
                      
                    
                        if (session.getAttribute("userStatus")!= null) {
                              String loginConfirm = session.getAttribute("userStatus").toString();
                              if (loginConfirm.equals("1")) {

                                    if (dbObject.getUserType(userid).equals("admin")) {
                                       session.setAttribute("uname",request.getParameter("username"));
                                        
                                        RequestDispatcher req = request.getRequestDispatcher("admin.jsp");
                                        req.include(request,response);
                                      
                                    } else if (dbObject.getUserType(userid).equals("student")) {
                                        session.setAttribute("uname",request.getParameter("username"));
                                        RequestDispatcher req = request.getRequestDispatcher("student.jsp");
                                         req.include(request, response);
                                    }

                              } else if (!loginConfirm.equals("1")) {
                                      response.sendRedirect("login.html");
                                     }
                        } else {
                            response.sendRedirect("login.html");
                        }


                    }


}
else if(request.getParameter("page").toString().equals("exams")){
    if(request.getParameter("operation").toString().equals("startexam")){
        String cName=request.getParameter("coursename");
        int userId=Integer.parseInt(session.getAttribute("userId").toString());
        
        int examId=dbObject.startExam(cName,userId);
        session.setAttribute("examId",examId);
        session.setAttribute("examStarted","1");
        response.sendRedirect("studentpage.jsp?pgprt=1&coursename="+cName);
    }else if(request.getParameter("operation").toString().equals("submitted")){
        try{
        String time=LocalTime.now().toString();
        int size=Integer.parseInt(request.getParameter("size"));
        int eId=Integer.parseInt(session.getAttribute("examId").toString());
        int tMarks=Integer.parseInt(request.getParameter("totalmarks"));
        session.removeAttribute("examId");
        session.removeAttribute("examStarted");
        for(int i=0;i<size;i++){
            String question=request.getParameter("question"+i);
            String ans=request.getParameter("ans"+i);
            
            int qid=Integer.parseInt(request.getParameter("qid"+i));
            
            dbObject.insertAnswer(eId,qid,question,ans);
        }
        System.out.println(tMarks+" conn\t Size: "+size);
        dbObject.calculateResult(eId,tMarks,time,size);
        
        response.sendRedirect("studentpage.jsp?pgprt=1&eid="+eId+"&showresult=1");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

}else if(request.getParameter("page").toString().equals("logout")){
    session.setAttribute("userStatus","0");
    session.removeAttribute("examId");
    session.removeAttribute("examStarted");
    session.invalidate();
    response.sendRedirect("login.html");
}

%>