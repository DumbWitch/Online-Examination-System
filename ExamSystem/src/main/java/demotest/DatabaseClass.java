/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demotest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import java.sql.ResultSet;
import demotest.User;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Afsana
 */
public class DatabaseClass {

    Connection conn;

    public DatabaseClass() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
    }

    ///for registration
    public void addNewStudent(String first_name, String last_name, String username, String password,
            String address, String contact) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            String sql = "INSERT INTO users(firstname , lastname , username , password , address , contact_no, user_type)" + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, first_name);
            pstm.setString(2, last_name);
            pstm.setString(3, username);
            pstm.setString(4, password);
            pstm.setString(5, address);
            pstm.setString(6, contact);
            pstm.setString(7, "student");
            pstm.executeUpdate();

            pstm.close();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //for login

    public boolean loginValidate(String userName, String userPass) throws SQLException, ClassNotFoundException {

        boolean status = false;

        String sql = "SELECT * FROM users\n"
                + "WHERE username=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        String uname;
        String pass;
        while (rs.next()) {
            uname = rs.getString("username");
            pass = rs.getString("password");

            if (pass.equals(userPass)) {
                return true;
            }
        }//end while
        return false;

    }

    public int getUserId(String userName) throws ClassNotFoundException {
        int str = 0;
        PreparedStatement pstm;
        try {

            pstm = conn.prepareStatement("Select * from users where username=?");
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                str = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return str;
    }

    public String getUserType(String userId) throws ClassNotFoundException {
        String str = "";
        PreparedStatement pstm;
        try {

            pstm = conn.prepareStatement("Select * from users where user_id=?");
            pstm.setInt(1, Integer.parseInt(userId));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                str = rs.getString("user_type");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            str = "error";
        }
        return str;
    }

    /// for profile
    public User getUserDetails(String username) {
        User userDetails = null;

        try {
            String sql = "SELECT * from users where username=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                userDetails = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                         rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userDetails;
    }
    ///for all accounts

    public ArrayList getAllUsers() {
        ArrayList list = new ArrayList();
        User user = null;
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement("Select * from users");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return list;
    }
    ///for account deletion

    public void delUser(int uid) {
        try {
            String sql = "DELETE from users where user_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, uid);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /// for showing all courses

    public ArrayList getAllCourses() throws SQLException {
        ArrayList list = new ArrayList();
        try {
            String sql = "select * from courses";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getInt(2));
            }
            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //for delete course

    public void delCourse(String cName) {
        try {
            String sql = "DELETE from courses where course_name=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cName);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/// add courses
    public void addNewCourse(String courseName, int tMarks, String time) {
        try {
            String sql = "INSERT into courses(course_name,total_marks,time) Values(?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, courseName);
            pstm.setInt(2, tMarks);
            pstm.setString(3, time);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //// add questions
    public void addQuestion(String cName, String question, String opt1, String opt2, String opt3, String opt4, String correct) throws SQLException {
        try {
            String sql = "INSERT INTO questions (course_name ,question ,opt1,opt2,opt3,opt4,correct) Values (?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cName);
            pstm.setString(2, question);
            pstm.setString(3, opt1);
            pstm.setString(4, opt2);
            pstm.setString(5, opt3);
            pstm.setString(6, opt4);
            pstm.setString(7, correct);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ////to get all questions
    public ArrayList getAllQuestion(String cName) {
        ArrayList list = new ArrayList();
        Questions question;
        try {
            String sql = "SELECT * FROM questions where course_name=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                question = new Questions(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(2));
                list.add(question);
            }
            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }
    //delete question

    public void delQuestion(int qId) {
        try {
            String sql = "DELETE from questions where question_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, qId);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///update admins profile
    public void updateAdmin(String fName, String lName, String uName, String pass, String address,
            String contact, String userType) {
        try {
            String sql = "Update users"
                    + " set firstname=? , lastname=? , username=? , password=? , address=? , contact_no=? , user_type=?  "
                    + " where userName=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, fName);
            pstm.setString(2, lName);
            pstm.setString(3, uName);
            pstm.setString(4, pass);
            pstm.setString(5, address);
            pstm.setString(6, contact);
            pstm.setString(7, "admin");

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////only course Question time
    public String getCourseTimeByName(String cName) {
        String c = null;
        try {
            PreparedStatement pstm = conn.prepareStatement("Select time from courses where course_name=?");
            pstm.setString(1, cName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                c = rs.getString(1);
            }
            pstm.close();
        } catch (Exception e) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, e);
        }

        return c;
    }
    
    
    /////Total marks according to course
    
     public int getTotalMarksByName(String cName){
     int marks=0;
     try{
         PreparedStatement pstm=conn.prepareStatement("Select total_marks from courses where course_name=?");
         pstm.setString(1,cName);
         ResultSet rs=pstm.executeQuery();
         while(rs.next()){
             marks=rs.getInt(1);
             System.out.println(rs.getInt(1));
         }
         pstm.close();
     }catch(Exception e){
          e.printStackTrace();
     }
     
     return marks;
    }
     
     /////Starting time of the exam
      public int startExam(String cName,int sId){
        int examId=0;
        try {
            String sql="INSERT into exams(course_name,date,start_time,exam_time,std_id,total_Marks) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.setString(1,cName);
            pstm.setString(2,getFormatedDate(LocalDate.now().toString()));
            pstm.setString(3,LocalTime.now().toString());
            pstm.setString(4,getCourseTimeByName(cName));
            pstm.setInt(5,sId);
            pstm.setInt(6, getTotalMarksByName(cName));
            pstm.executeUpdate();
            pstm.close();
            examId=getLastExamId();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examId;
    }
      /////Giving a format of local date
      private  String getFormatedDate(String date){
        LocalDate localDate=LocalDate.parse(date);
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
      
      /////
      
       public int getLastExamId(){
        int id=0;
         try {
            
            String sql="Select * from exams";
            PreparedStatement pstm=conn.prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            
            while(rs.next()){
               id=rs.getInt(1);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         return id;
    }

       
       /////aa= ekhnkar shomoy theke - exam start er shomoy
       /////then total exam somoy theke -aa
       public int getRemainingTime(int examId){
        int time=0;
        try {
            
            String sql="Select start_time,exam_time from exams where exam_id=?";
            PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.setInt(1, examId);
            ResultSet rs=pstm.executeQuery();
            
            while(rs.next()){
                //totalTime-(Math.abs(currentTime-examStartTime))
                //Duration.between(first,sec) returns difference between 2 dates or 2 times
               time=Integer.parseInt(rs.getString(2))-(int)Math.abs((Duration.between(LocalTime.now(),LocalTime.parse(rs.getString(1))).getSeconds()/60));
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(time);
        return time;
    }
   /////question for specific course
           public ArrayList getQuestions(String courseName,int questions){
        ArrayList list=new ArrayList();
        try {
            
            String sql="Select * from questions where course_name=? ORDER BY RAND() LIMIT ?";
            PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.setString(1,courseName);
            pstm.setInt(2,questions);
            ResultSet rs=pstm.executeQuery();
            Questions question;
            while(rs.next()){
               question = new Questions(
                       rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),
                       rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(2)
                    ); 
               list.add(question);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
           
        
    ////inserting value in answers table
    
     public void insertAnswer(int eId,int qid,String question,String ans){
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into answers(exam_id,question,answer,correct_answer,status) "
                    + "Values(?,?,?,?,?)");
            pstm.setInt(1,eId);
            pstm.setString(2, question);
            pstm.setString(3,ans);
            String correct=getCorrectAnswer(qid);
            pstm.setString(4, correct);
            pstm.setString(5,getAnswerStatus(ans,correct));
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
     
     private String getCorrectAnswer(int qid) {
        String ans="";
        
        try {
            PreparedStatement pstm=conn.prepareStatement("Select correct from questions where question_id=?");
            pstm.setInt(1,qid);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                ans=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return ans;
    }
     
     
      private String getAnswerStatus(String ans, String correct) {
        String resp="";
        if(ans.equals(correct)){
            resp="correct";
        }else{
            resp="incorrect";
        }
        return resp;
       
    }
      
        public void calculateResult(int eid,int tMarks,String endTime,int size){
        
        try {
            String sql="update exams "
                    + "set obt_marks=?, end_time=?,status=? "
                    + "where exam_id=?";
            PreparedStatement pstm=conn.prepareStatement(sql);
            int obt=getObtMarks(eid,tMarks,size);
            pstm.setInt(1,obt );
            pstm.setString(2,endTime);
            float percent=((obt*100)/tMarks);
            if(percent>=45.0){
                pstm.setString(3,"Pass");
            }else{
                pstm.setString(3,"Fail");
            }
            pstm.setInt(4, eid);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getObtMarks(int examId,int tMarks,int size) {
    int m=0;
    
        try {
            PreparedStatement pstm=conn.prepareStatement("select count(answer_id) from answers "
                    + "where exam_id=? and status='correct'");
            pstm.setInt(1, examId);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
               m= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        float rat=(float)tMarks/size;
        System.out.println(rat);
        rat=m*rat;
        System.out.println(rat);
        return m=(int) rat;
    } 
      
      
      public Exams getResultByExamId(int examId){
        Exams exam=null;
        try {
            PreparedStatement pstm=conn.prepareStatement("select * from exams where exam_id=?");
            pstm.setInt(1, examId);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                exam=new Exams(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
                ,rs.getString(6),getFormatedTime(rs.getString(7)),getFormatedTime(rs.getString(8)),rs.getString(9),rs.getString(10));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exam;
        
    }
      private String getNormalDate(String date){
        String[] d=date.split("-");
        return d[2]+"-"+d[1]+"-"+d[0];
    }
    private String getFormatedTime(String time){
        if(time!=null){
            LocalTime localTime=LocalTime.parse(time);
        return  localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }else{
            
        return  "-";
        }
    }
    ////Result from exam
      
       public ArrayList getAllResultsFromExams(int stdId){
        ArrayList list=new ArrayList();
        Exams exam=null;
        try {
            PreparedStatement pstm=conn.prepareStatement("select * from exams where std_id=? order by date desc");
            pstm.setInt(1, stdId);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                exam=new Exams(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
                ,rs.getString(6),getFormatedTime(rs.getString(7)),getFormatedTime(rs.getString(8)),rs.getString(9),rs.getString(10));
                list.add(exam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    /////answers by exam id
       
        public ArrayList getAllAnswersByExamId(int examId){
        ArrayList list=new ArrayList();
        try {
            
            String sql="Select * from answers where exam_id=?";
            PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.setInt(1,examId);
            ResultSet rs=pstm.executeQuery();
            Answers a;
            while(rs.next()){
               a = new Answers(
                       rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)
                    ); 
               list.add(a);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    void updateAdmin(String fName, String lName, String uName, String pass, String address, String contactNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
  
    
   
}
