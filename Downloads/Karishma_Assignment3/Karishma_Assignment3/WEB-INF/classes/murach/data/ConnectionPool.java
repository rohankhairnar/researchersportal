/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author rimpl
 */
public class ConnectionPool {
    
    
    //getconnection
     public Connection getConnection() throws ClassNotFoundException, SQLException
     {
         Class.forName("com.mysql.jdbc.Driver");
         String dbName = "NBAD";
         String userName = "rohankhairnar1";
         String password = "applied123";
         String hostname = "murachdb1.cpk9g4yipxpc.us-west-2.rds.amazonaws.com";
         String port = "3306";
         String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
         return DriverManager.getConnection(jdbcUrl);
     }
    
}
