
package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import murach.business.Answer;
import murach.business.Study;


public class AnswerDB {

    List<Answer> ansList = new ArrayList<Answer>();   
    private ArrayList<Answer> listAns;
      
     public List<Answer> getAnswer() {            
		return ansList;
	}
    
     public void setAnswer(ArrayList<Answer> listAns) { 
        this.listAns = listAns;        
	}

     public void setAnswerObj(String email,int choice) {	 
        Answer ans = new Answer(email,choice);
        ansList.add(ans);       
	}
     
     public void addAnswer(Answer ans) throws ClassNotFoundException, SQLException{  
         
         
         //DateUtil dateutil=new DateUtil();
        int choice = ans.getChoice();
        String email = ans.getEmail();
        String code = ans.getStudyCode();
        String questionCode="";
        
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet resultSet = null;
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        
        
        String query1 = "Select QuestionID from Question where StudyID = '" + code + "'";
        String query = "insert into Answer"
				+ "(UserName, Choice, StudyID ,DateSubmitted,QuestionID) VALUES"
				+ "(?,?,?,?,?)";
        
        try {
            ps1 = connection.prepareStatement(query1);
            resultSet = ps1.executeQuery();
            if(resultSet.next()) {
            questionCode = resultSet.getString("QuestionID");
            }
            System.out.println("I am inside try block in put");
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, choice);
            ps.setString(3, code);
            
            
           //  ps.setDate(4, new java.sql.Date(dateutil.getDate().getTime()));
            
            java.util.Date date = new Date();
            ps.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
            ps.setString(5,questionCode);
            
           // ps.setTimestamp(4, new java.sql.Date(new java.util.Date()));
            //ps.setDate(4, dateCreated);
          
            ps.executeUpdate();
            
            System.out.println("Insertion done");
                
        } catch (SQLException e) {
            System.out.println(e);
         
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
          
     }
     
     
     public List<Answer> getAnswersFor(String e_mail) throws ClassNotFoundException, SQLException    //working with db      
    {                    
        System.out.println("inside get all studies using status function");        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Answer> newStudies = new ArrayList<Answer>();         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        String query = "select * from Answer where UserName = '" + e_mail + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();           
            while(resultSet.next()) {
                    Answer ans = new Answer();
                    System.out.println("I am inside foooorrr");
                    String email =  resultSet.getString("UserName");
                    int choice =  resultSet.getInt("Choice");
                   String code =  resultSet.getString("StudyID");
                   // Date dateSubmitted =  resultSet.getDate("DateSubmitted");
                    ans.setEmail(email);
                    ans.setChoice(choice);
                    ans.setStudyCode(code);
                    //ans.setdateSubmitted(dateSubmitted);
                    newStudies.add(ans);
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
         
     public List<Answer> getAnswerByCode(String SCode) throws ClassNotFoundException, SQLException    //working with db      
    {                                            
        System.out.println("inside get all studies using status function");        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Answer> newStudies = new ArrayList<Answer>();         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();  
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        String query = "select * from Answer where StudyID = '" + SCode + "'";
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();           
            while(resultSet.next()) {
                    Answer ans = new Answer();
                    System.out.println("I am inside foooorrr");
                    String email =  resultSet.getString("Email");
                    int choice =  resultSet.getInt("Choice");
                    String code =  resultSet.getString("StudyID");
                   // Date dateSubmitted =  resultSet.getDate("DateSubmitted");
                    ans.setEmail(email);
                    ans.setChoice(choice);
                    ans.setStudyCode(code);
                    //ans.setdateSubmitted(dateSubmitted);
                    newStudies.add(ans);
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
     
     
     
     
     public static boolean answerExists(String email,String code) throws ClassNotFoundException, SQLException {
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
        Connection connection=pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT UserName FROM Answer "
                + "WHERE UserName = ? AND StudyID = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
             ps.setString(2, code);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
    }
     
     
     
}
