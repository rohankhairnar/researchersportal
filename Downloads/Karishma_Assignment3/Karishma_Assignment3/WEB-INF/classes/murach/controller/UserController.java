/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import murach.business.User;
import murach.data.UserDB;

/**
 *
 * @author Spandana
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    UserDB userDb = new UserDB();
    User user;
   
    String name, email, type, password, confirm_password, admincheck, msg;
    HashMap<String,String> passwordDB = new HashMap<String,String>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    
    response.setContentType("text/html;charset=UTF-8");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request, response);
        
        
    }




    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String url="/home.jsp";
       
       HttpSession session = request.getSession();
       String action=request.getParameter("action");
      
        Cookie c = new Cookie("myCookie",URLEncoder.encode(InetAddress.getLocalHost().getHostName()+" "+request.getLocalName()+" "+ request.getLocalPort(),"UTF-8"));
      c.setMaxAge(60*60*24*365*2);
       c.setPath("/");
       response.addCookie(c);
       try{
        if(action == null){
            url = "/home.jsp";
        }
       else if(action.equals("login")){
            
            //check the user type(incomplete)s
            String password = request.getParameter("password");
            String email = request.getParameter("email");


  int x=UserDB.validateUser(email, password);
            
               if(x==1)
               {
                   User user=UserDB.getUser(email);
                   System.out.println(user.getEmail());
                   System.out.println(user.getName());
                 
                   if(user.getType().equals("Participant"))
                   {
                    //System.out.println("CHARUUUUU"+user.getParticipants()+user.getEmail()+user.getCoins()+user.getParticipation());
                     session = request.getSession();
                    session.setAttribute("theUser", user);
                    
                    url="/main.jsp?user=theUser";
               }
                   else
                   {
                        session = request.getSession();
                    session.setAttribute("theAdmin", user);
                    url="/admin.jsp?user=theAdmin";
                   }
               }
               // if incorrect
               else
               {
                   System.out.println("password incorrect");
                   request.setAttribute("msg","LOGIN FAILD-USERNAME-PASSWORD DOES NOT EXIST");
                   
                    url="/login.jsp";
               }
        }
         
        else if(action.equals("create"))
        {
           name =  request.getParameter("name");
           email = request.getParameter("email");
          // type =  request.getParameter("type");
           password =  request.getParameter("password");
           confirm_password = request.getParameter("cpassword");
           admincheck = request.getParameter("admincheck");

   if(!UserDB.emailExists(email))
            {
            if (password.equals(confirm_password))
            {
                //creates a user bean for the user
                User newuser=  new User(name,email);
                newuser.setPassword(password);
              //  UserDB.addCredentials(email, password);
                UserDB.addUser(newuser);
                session.setAttribute("theUser", newuser);
                
                // dispatches to main.jsp
                url="/main.jsp?user=theUser";
                
            }
            // if there is an error
            else
            {
                // adds the error message to http request object
                String err="SIGNUP FAILED-PASSWORD DOEST NOT MATCH WITH CONFIRM PASSWORD";
                request.setAttribute("msg", err);
                // dispatches to signup.jsp
                url="/signup.jsp";
                
            }
            }
            else
            {
                String err="SIGNUP FAILED-EMAIL ALREADY EXISTS";
                request.setAttribute("msg", err);
                // dispatches to signup.jsp
                url="/signup.jsp";
            }
            
        }
        
        else if(action.equals("how")){
            User session_exists = (User)session.getAttribute("theUser");
            if(session_exists == null){
                url = "/how.jsp";
            }
            else{
                url = "/main.jsp";
            }
        }
        
        else if(action.equals("about")){
            User session_exists = (User) session.getAttribute("theUser");
            
            if(session_exists == null){
                url="/about.jsp";
            }
            else{
                url = "/aboutl.jsp?user=theUser";
            }
        }
        
        else if(action.equals("home")){
            User session_exists = (User) session.getAttribute("theUser");
            
            if(session_exists == null){
                url="/home.jsp";
            }
            else{
                url = "/main.jsp";
            }
        }
        
        else if(action.equals("main")){
            User session_exists = (User) session.getAttribute("theUser");
            
            if(session_exists == null){
                url="/login.jsp";
            }
            else{
                url = "/main.jsp";
            }
        }
        
        else if(action.equals("logout")){
            User session_exists = (User) session.getAttribute("theUser");
            User admin = (User)session.getAttribute("theAdmin");
            if((session_exists != null) | (admin != null)){
                session.invalidate();
                url = "/home.jsp";
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
       }catch(Exception e){
           System.out.println(e);
       }
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


