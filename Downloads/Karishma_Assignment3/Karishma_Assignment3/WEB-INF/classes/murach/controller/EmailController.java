/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Email.*;

public class EmailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
        PrintWriter out = response.getWriter();
       
         String url="/confirmr.jsp?user=theUser";
        
       String action=request.getParameter("action");
       String name=request.getParameter("name");
       String friendemail=request.getParameter("friendemail");
       String message=request.getParameter("message");
       String email=request.getParameter("email");
         
         System.out.println("action is"+action);
         
      
         
         System.out.println(email);
         
         if(action==null)
       {
           System.out.println("its null action");
           url="/confirmr.jsp?user=theUser";
          
       }
      else if(action.equals("recommendemail"))
       {
           
           System.out.println("its email recommendateion");
           
           
           
           try
           {
         MainClass e= new MainClass();
         e.sendEmailRecommend(name,friendemail,message);
           
           System.out.println("After Email sent");
           out.println("email gone");
           
           url="/confirmr.jsp?user=theUser";
          
           }
           
            catch(Exception e)
         {
             System.out.println("Exception is "+e);
         }
         
                    
   
           
       }
      else if(action.equals("contactemail"))
         {
             out.println("I am in contact");
         System.out.println("its email contact");
         
         try{
        
         MainClass e= new MainClass();
        e.sendEmailContact(name, email, message);
         
           System.out.println("After Email contact sent");
           out.println("emailcontact gone");
           
          url="/confirmc.jsp?user=theUser"; 
           
           
                              
         }
         catch(Exception e)
         {
             System.out.println("Exception is "+e);
         }
         
                    
                 
          }
                 
       
      getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     //   processRequest(request, response);
  
      
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
