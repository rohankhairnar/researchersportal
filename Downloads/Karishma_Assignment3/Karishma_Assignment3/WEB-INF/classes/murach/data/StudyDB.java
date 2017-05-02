/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import murach.business.Study;

/**
 *
 * @author rimpl
 */
public class StudyDB {
    
    int count=1;
    static private int codegenerate=1003;
    
    public HashMap<String, Study> studies = new HashMap<String, Study>(); 
 
    public Study getStudy(String studyCode) throws ClassNotFoundException, SQLException                  
    {      

PreparedStatement ps = null;
        ResultSet resultSet = null;
        Study study = new Study();
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();      
        String query = "select * from Study join Question on Study.StudyID=Question.StudyID where Study.StudyID = '" + studyCode + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
  
                    System.out.println("I am inside foooorrr");
                    String sName =  resultSet.getString("StudyName");
                    String sCode =  resultSet.getString("StudyID");
                    String qustion =  resultSet.getString("Question");
                    String description =  resultSet.getString("Description");
                    String email =  resultSet.getString("Username");
                   Date dateCreated =  resultSet.getDate("DateCreated");
                  
                    String imageURL =  resultSet.getString("ImageURL");
                    int reqParticipants =  resultSet.getInt("ReqParticipants");
                    int actParticipants =  resultSet.getInt("ActParticipants");
                    String sStatus =  resultSet.getString("SStatus");
                    String option1=resultSet.getString("Option1");
                    String option2=resultSet.getString("Option2");
                    String option3=resultSet.getString("Option3");
                    String option4=resultSet.getString("Option4");
                    String option5=resultSet.getString("Option5");
                    
                    ArrayList<String> answerValues=new ArrayList<>();
                    answerValues.add(option1);
                    answerValues.add(option2);
                    answerValues.add(option3);
                    answerValues.add(option4);
                    answerValues.add(option5);
                    System.out.println("Study details is " + sName);
                    study.setStudyName(sName);
                    study.setStudyCode(sCode);
                    study.setDescription(description);
                    study.setEmail(email);
                    study.setQuestion(qustion);
                    
                    study.setGetImageURL(imageURL);
                    study.setRequestedParticipants(reqParticipants);
                    study.setNumofParticipants(actParticipants);
                    study.setStatus(sStatus);
                    study.setAnsList(answerValues);
                   
                    
                  
                 }
                  return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
    
    }
    
 public List<Study> getStudies(String status) throws ClassNotFoundException, SQLException
 {
     
        System.out.println("inside get all studies function"); 
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Study> newStudies = new ArrayList<Study>();         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        String query = "select * from Study join Question on Study.StudyID=Question.StudyID where SStatus = '" + status + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()) {
                    Study study = new Study();  
                    System.out.println("I am inside foooorrr");
                    String sName =  resultSet.getString("StudyName");
                    String sCode =  resultSet.getString("StudyID");
                    String description =  resultSet.getString("Description");
                    String email =  resultSet.getString("Username");
                  String question =  resultSet.getString("Question");
                   Date dateCreated =  resultSet.getDate("DateCreated");
                  
                    String imageURL =  resultSet.getString("ImageURL");
                    int reqParticipants =  resultSet.getInt("ReqParticipants");
                    int actParticipants =  resultSet.getInt("ActParticipants");
                    String sStatus =  resultSet.getString("SStatus");
                    System.out.println("Study details is " + sCode);
                    study.setStudyName(sName);
                    study.setStudyCode(sCode);
                    study.setDescription(description);
                    study.setEmail(email);
                    study.setQuestion(question);
                    study.setGetImageURL(imageURL);
                    study.setRequestedParticipants(reqParticipants);
                    study.setNumofParticipants(actParticipants);
                    study.setStatus(sStatus);                         
                    newStudies.add(study);
                    System.out.println(newStudies);
                 }
            
                  return newStudies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }    
   
 }  

  public List<Study> getStudiesFor(String username) throws ClassNotFoundException, SQLException                
    {            
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Study> newStudies = new ArrayList<Study>();
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        String query = "select * from Study where Username = '" + username + "'";
        
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                    Study study = new Study();
                    System.out.println("I am inside foooorrr");
                    String sName =  resultSet.getString("StudyName");
                    System.out.println(sName);
                    String sCode =  resultSet.getString("StudyID");
                    String description =  resultSet.getString("Description");
                    String email =  resultSet.getString("Username");
                   Date dateCreated =  resultSet.getDate("DateCreated");
                   // String question =  resultSet.getString("Question");
                    String imageURL =  resultSet.getString("ImageURL");
                    int reqParticipants =  resultSet.getInt("ReqParticipants");
                    int actParticipants =  resultSet.getInt("ActParticipants");
                    String sStatus =  resultSet.getString("SStatus");
                    System.out.println("Study details is " + sName);
                    study.setStudyName(sName);
                    study.setStudyCode(sCode);
                    study.setDescription(description);
                    study.setgetImageURL(imageURL);
                    study.setRequestedParticipants(reqParticipants);
                    study.setNumofParticipants(actParticipants);
                    study.setStatus(sStatus);      
                  
                    newStudies.add(study);
                 
            }
                  return newStudies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }

    }
    
  public void addStudy(Study study) throws ClassNotFoundException, SQLException
  {         //working
     System.out.println("I am inside put");
     String code1,code2;
     String option1="";
     String option2=""; 
     String option3="";
     String option4="";
     String option5="";
     code1= codeGenerator();
     study.setStudyCode(code1);
     code2=codeGenerator();
     String answerType=study.getAnswerType();
   
     String studyName=study.getStudyName();
     String desc=study.getDescription();
     String email=study.getEmail();
     String question=study.getQuestion();
    // String username = study.getUsername();
     Date dateCreated=study.getDateCreated();
     
     String imageURL=study.getgetImageURL();
     int reqParticipants =study.getRequestedParticipants();
     int actParticipants=study.getNumofParticipants();
     String sStatus=study.getStatus();
     ArrayList<String> answers=study.getAnsList();
     option1=answers.get(0);
     option2=answers.get(1);
     option3=answers.get(2);
     
     if(answers.size()==4)
     {
         option4=answers.get(3);
     }
     if(answers.size()==5)
     {
         option4=answers.get(3);
         option5=answers.get(4);
     }
    // String option4=answers.get(3);
    // String option5=answers.get(4);
      //ConnectionPool pool = ConnectionPool.getInstance();
      //Connection connection = pool.getConnection();
      ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
      PreparedStatement ps1 = null;
      PreparedStatement ps2 = null;
      ResultSet resultSet = null;
      
      String query1 = "insert into Study"
				+ "(StudyID, StudyName, Description, Username,DateCreated,ImageURL,ReqParticipants,ActParticipants,SStatus) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
      
      String query2 = "insert into Question"
				+ "(QuestionID, StudyID, Question, AnswerType,Option1,Option2,Option3,Option4,Option5) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
 
      try {
            System.out.println("I am inside try block in put");
            ps1 = connection.prepareStatement(query1);
            ps1.setString(1,code1);
            ps1.setString(2, studyName);
            ps1.setString(3, desc);
            ps1.setString(4, email);
            java.util.Date date = new Date();
            ps1.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
            //ps.setDate(5, dateCreated);
            ps1.setString(6, imageURL);
           
            ps1.setInt(7,reqParticipants );
            ps1.setInt(8, actParticipants);
            ps1.setString(9, sStatus);
            ps1.executeUpdate();
            
            ps2 = connection.prepareStatement(query2);
            ps2.setString(1,code2);
            ps2.setString(2, code1);
            ps2.setString(3, question);
            ps2.setString(4, answerType);
            ps2.setString(5, option1);
            ps2.setString(6, option2);
            ps2.setString(7, option3);
            ps2.setString(8, option4);
            ps2.setString(9, option5);
              
             ps2.executeUpdate();
          
            System.out.println("Insertion done");
                
        } catch (SQLException e) {
            System.out.println(e);
             
    }
  }
  
  public void addReported(Study study) throws ClassNotFoundException, SQLException
  {
      
      //ConnectionPool pool = ConnectionPool.getInstance();
      //Connection connection = pool.getConnection();
      ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
      PreparedStatement ps = null;
      PreparedStatement ps1 = null;
      ResultSet resultSet = null;  
      
      String query1 = "insert into Reported"
				+ "(QuestionID,StudyID,Date,NumParticipants,Status) VALUES"
				+ "(?,?,?,?,?)";
      
      int actParticipants=study.getNumofParticipants();
      
      String questionCode="";
      String studyCode = study.getStudyCode();
      System.out.println("stud code : " + studyCode);
      String query = "select QuestionID from Question where StudyID = '" + studyCode +"'";
      try
      {
      ps1 = connection.prepareStatement(query);
      resultSet = ps1.executeQuery();
      if(resultSet.next()) {
          questionCode = resultSet.getString("QuestionID");
      }
            System.out.println("I am inside try block in put of reported");
            ps = connection.prepareStatement(query1);
            ps.setString(1,questionCode);
            ps.setString(2, studyCode);
            java.util.Date date = new Date();
            ps.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
            ps.setInt(4, actParticipants);
            ps.setString(5, "UNDER REVIEW");
            ps.executeUpdate();
            System.out.println("Insertion done");
                
        } catch (SQLException e) {
            System.out.println(e);
        }
      
      
  }
    
 
    public String codeGenerator()
    {
        StringBuilder sb=new StringBuilder();
        String code = "";
        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt(100) + 1;
        sb.append(num);
        sb.append(count);
        code=sb.toString();
        
        count++;
        return code;
    }
    public Study getStudy(String studyCode,String email) throws ClassNotFoundException, SQLException
    {
        
       
   PreparedStatement ps = null;
        ResultSet resultSet = null;
        Study study = new Study();
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
       
        String query = "select * from Study join Question on Study.StudyID=Question.StudyID where Study.StudyID = '" + studyCode + "' and Username = '" + email + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
  
                   System.out.println("I am inside foooorrr");
                    String sName =  resultSet.getString("StudyName");
                    String sCode =  resultSet.getString("StudyID");
                    String qustion =  resultSet.getString("Question");
                    String description =  resultSet.getString("Description");
                    String emailID =  resultSet.getString("Username");
                   Date dateCreated =  resultSet.getDate("DateCreated");
                  //  String question =  resultSet.getString("Question");
                    String imageURL =  resultSet.getString("ImageURL");
                    int reqParticipants =  resultSet.getInt("ReqParticipants");
                    int actParticipants =  resultSet.getInt("ActParticipants");
                    String sStatus =  resultSet.getString("SStatus");
                    String option1=resultSet.getString("Option1");
                    String option2=resultSet.getString("Option2");
                    String option3=resultSet.getString("Option3");
                    String option4=resultSet.getString("Option4");
                    String option5=resultSet.getString("Option5");
                    ArrayList<String> answerValues=new ArrayList<>();
                    answerValues.add(option1);
                    answerValues.add(option2);
                    answerValues.add(option3);
                    answerValues.add(option4);
                    answerValues.add(option5);
                    System.out.println("Study details is " + sName);
                    study.setStudyName(sName);
                    study.setStudyCode(sCode);
                    study.setDescription(description);
                    study.setEmail(emailID);
                    study.setQuestion(qustion);
                    
                    study.setGetImageURL(imageURL);
                  
                    study.setNumofParticipants(reqParticipants);
                    study.setStatus(sStatus);
                    study.setAnsList(answerValues);
                    
                  
                 }
                  return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
    
    }
   
     public void updateStatus(Study s,String status) throws SQLException, ClassNotFoundException
    {
        String code = s.getStudyCode();
        System.out.println("code in update func is" + code);
          PreparedStatement ps = null;
        ResultSet resultSet = null;
        Study study = new Study();
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
       ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        String query = "update Study SET SStatus = ? "
				                  + " WHERE StudyID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, code);
            ps.executeUpdate();
            System.out.println("status updated");
           
                
        } catch (SQLException e) {
            System.out.println(e);
        
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
    
        
    }
     
     public void updateRecord(String code,Study study) throws SQLException, ClassNotFoundException
    {
        
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet resultSet = null;
        String option1="";
     String option2=""; 
     String option3="";
     String option4="";
     String option5="";
      ArrayList<String> answers=study.getAnsList();
      option1=answers.get(0);
     option2=answers.get(1);
     option3=answers.get(2);
     
     if(answers.size()==4)
     {
         option4=answers.get(3);
     }
     if(answers.size()==5)
     {
         option4=answers.get(3);
         option5=answers.get(4);
     }
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
       
       String query1 = "update Study SET StudyName = ?, ReqParticipants=? ,  Description=?"
				                  + " WHERE StudyID = ?";
       
       String query2 = "update Question SET Question = ?, Option1=? , Option2=?, Option3=? , Option4=? , Option5=?"
				                  + " WHERE StudyID = ?";
        try {
            System.out.println("I am inside try block in put");
            ps1 = connection.prepareStatement(query1);
            ps1.setString(1, study.getStudyName());
            System.out.println("Study Name: "+study.getStudyName());
           // ps.setString(2, study.getQuestion());
            ps1.setInt(2, study.getRequestedParticipants());
            ps1.setString(3, study.getDescription());
            ps1.setString(4, study.getStudyCode());
            ps1.executeUpdate();
            
            ps2 = connection.prepareStatement(query2);
            ps2.setString(1, study.getQuestion());
             ps2.setString(2, option1);
            ps2.setString(3, option2);
            ps2.setString(4, option3);
            ps2.setString(5, option4);
            ps2.setString(6, option5);
            ps2.setString(7, study.getStudyCode()); 
             ps2.executeUpdate();
          
            
            System.out.println("Insertion done");
                
        } catch (SQLException e) {
            System.out.println(e);
         
        } finally {
            DBUtil.closePreparedStatement(ps1);
            DBUtil.closePreparedStatement(ps2);
            //pool.freeConnection(connection);
            connection.close();
        }
        
        
        
    }
     
     
    public List<Study> getQuestions(String status) throws SQLException, ClassNotFoundException                
    {             
         System.out.println("inside get all studies using status function"); 
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        List<Study> ReviewQuestions = new ArrayList<Study>();         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
       
        String query = "select * from Reported join Question on Reported.QuestionID=Question.QuestionID where Status = '" + status + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()) {
                    Study study = new Study();  
                    System.out.println("I am inside foooorrr");
                   
                    String sCode =  resultSet.getString("StudyID");
                   
                   
                   // Date dateCreated =  resultSet.getDate("DateCreated");
                    String question =  resultSet.getString("Question");
                   
           
                    study.setStudyCode(sCode);
                  
                  
                     study.setQuestion(question);
                                           
                    ReviewQuestions.add(study);
                 }
            
                  return ReviewQuestions;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
     
      
    }
    
     public void updateActParticipants(String code,int numofParticipants) throws SQLException, ClassNotFoundException
    {
         //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        
        
        PreparedStatement ps = null;

        String query = "UPDATE Study SET "
                + "ActParticipants = ? "
                 +"WHERE StudyID= ?";
               
        try {
          
            ps = connection.prepareStatement(query);
           
            ps.setInt(1, numofParticipants);
            ps.setString(2, code);
       
           
          

           ps.executeUpdate();
        } catch (SQLException e) {
            
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
         
        
        
        
        
    }
    public void updateReportStatus(Study study,String status) throws SQLException, ClassNotFoundException
    {
        
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
       
        String questionCode ="";
        ResultSet resultSet = null;
       
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
       
       String query = "update Reported SET Status = ?"
				                  + " WHERE StudyID = ? AND QuestionID = ?";
       String query1 = "Select QuestionID from Question where StudyID = '" + study.getStudyCode()+"'";
       
        try {
            ps1 = connection.prepareStatement(query1);
            resultSet = ps1.executeQuery();
            if(resultSet.next()) {
            questionCode = resultSet.getString("QuestionID");
      }
            System.out.println("I am inside try block in put");
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
           
           // ps.setString(2, study.getQuestion());
            ps.setString(2, study.getStudyCode());
            ps.setString(3, questionCode);
           
            ps.executeUpdate();
            
            System.out.println("Updation done");
                
        } catch (SQLException e) {
            System.out.println(e);
         
        } finally {
            DBUtil.closePreparedStatement(ps);
            
            //pool.freeConnection(connection);
            connection.close();
        }
        
        
        
    }
     
    public List<Study> getReportHistory(String email) throws SQLException, ClassNotFoundException                  //not used
    {      
//        System.out.print("studyCode in getStudy func" + studyCode);
//        Study study;
//        
//        Iterator iterator = studies.keySet().iterator();
//            int i=0;
//            String code;
//           
//           while (iterator.hasNext()) 
//           {
//                System.out.println("inside iterator"+i); 
//                String key = iterator.next().toString();
//                study = studies.get(key);
//                code = study.getStudyCode();
//                if(code.equalsIgnoreCase(studyCode))
//                {
//                    
//                    
//                    return study;
//                }
//                i++;          
//            }
//        
//        return null;
PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        List<Study> newStudies = new ArrayList<Study>();         
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
       
        String query = "select * from Question Q join Reported R on Q.QuestionID=R.QuestionID join Study S on S.StudyID=Q.StudyID join User U on U.Username=S.Username where U.Username = '" + email + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            int count=0;
           
            while(resultSet.next()) {
                Study study = new Study();
                    System.out.println("I am inside foooorrr report");
                   
                    Date reportDate =  resultSet.getDate("Date");
                    String question =  resultSet.getString("Question");
                   
                    String reportStatus =  resultSet.getString("R.Status");
                    
                    study.setReportDate(reportDate);
                    study.setReportStatus(reportStatus);
                    study.setQuestion(question);
                   
                  newStudies.add(study);
                    System.out.println(newStudies);  
                    
                  count++;
                 }
            System.out.println("count is: "+count);
                  return newStudies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
    
    }
    
    
}

