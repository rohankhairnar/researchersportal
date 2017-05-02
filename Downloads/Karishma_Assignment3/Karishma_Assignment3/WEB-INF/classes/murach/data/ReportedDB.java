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
import java.util.List;
import murach.business.Study;

/**
 *
 * @author rimpl
 */
public class ReportedDB {
    
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
    public void updateReportStatus(Study study,String status) throws ClassNotFoundException, SQLException
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
