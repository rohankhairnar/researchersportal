/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.http.HttpServlet;

import murach.business.Study;
import murach.business.User;


/**
 *
 * @author rimpl
 */
public class UserDB extends HttpServlet{
    
    
    
    HashMap<String, User> map = new HashMap<String, User>(); 
           
    public static void addUser(User user) throws ClassNotFoundException, SQLException
            
    {
       try {
            
          //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (Username,Name, Password,Type) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2,user.getName());
            ps.setString(3, user.getPassword());
             ps.setString(4, "Participant");
           
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return ;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
     
    }
       finally
       {
           
       }
    }
     
  
    public static User getUser(String email) throws SQLException, ClassNotFoundException {
        
    
  //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("Name"));// note
                user.setEmail(rs.getString("Username"));
                user.setType(rs.getString("Type"));
              
               user.setNumParticipation(rs.getInt("Participation"));
               user.setNumCoins(rs.getInt("Coins"));
                
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
        
        
        
    }
    
    public  HashMap<String, User> getUsers() {
  
         return null;
     
     }
     public static int validateUser(String Email, String Password) throws SQLException, ClassNotFoundException
     {
         
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
         ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Password FROM User "
                + "WHERE Username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Email);
            rs = ps.executeQuery();
            while(rs.next())
            {
               if(rs.getString(1).equals(Password))
               {
                   return 1;
               }
            }
            return 0;
            
        } catch (SQLException e) {
            System.out.println("Exception is"+e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
         
         
         
         
         
         
        
         
         }
     public static boolean emailExists(String email) throws ClassNotFoundException, SQLException {
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Username FROM User "
                + "WHERE Username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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
      public static void updateUserStudies(User user) throws ClassNotFoundException, SQLException
     {
         
         StudyDB study1=new StudyDB();
         //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
        
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "Studies = ? "
                 +"WHERE Username= ?";
               
        try {
          
            ps = connection.prepareStatement(query);
           
           ps.setInt(1, study1.getStudiesFor(user.getEmail()).size());
           // ps.setInt(1, user.getParticipants());
             ps.setString(2, user.getEmail());
          
       
           
          

           ps.executeUpdate();
        } catch (SQLException e) {
            
            System.out.println(e);
            return ;
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
         }
    
     public static void updateParticipations(User user) throws ClassNotFoundException, SQLException
     {
          //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
         ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
         
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "Participation = ? "
                 +"WHERE Username= ?";
               
        try {
          
            ps = connection.prepareStatement(query);
           
            ps.setInt(1, user.getNumParticipation());
            ps.setString(2, user.getEmail());
       
           
          

           ps.executeUpdate();
        } catch (SQLException e) {
            
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
         
         
         
         }
     public static void updateCoins(User user) throws ClassNotFoundException, SQLException
     {
          //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
         ConnectionPool pool = new ConnectionPool();
      Connection connection=pool.getConnection();
         
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "Coins = ? "
                +"WHERE Username= ?";
               
        try {
          
            ps = connection.prepareStatement(query);
           
            ps.setInt(1, user.getNumCoins());
       
             ps.setString(2, user.getEmail());
          

           ps.executeUpdate();
        } catch (SQLException e) {
            
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
            connection.close();
        }
         
         
         
         }
   
    
    
}