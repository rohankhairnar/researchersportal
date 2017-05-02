/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import murach.business.Answer;
import murach.business.Study;
import murach.business.User;
import murach.data.AnswerDB;
import murach.data.ReportedDB;
import murach.data.StudyDB;
import murach.data.UserDB;

/**
 *
 * @author rimpl
 */
@WebServlet(name = "StudyController", urlPatterns = {"/StudyController"})
public class StudyController extends HttpServlet {
    
     StudyDB obj = new StudyDB();
     UserDB userObj = new UserDB();
     AnswerDB ansdb = new AnswerDB();
     ReportedDB reportObj=new ReportedDB();
       int count=0;
       ArrayList<String> answerlist;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudyController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudyController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
        }

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
         String url ="/";
        response.setContentType("text/html");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();   
        HttpSession session = request.getSession();
       
               
        System.out.print(action);
       try{
       if(action == null)
        {
            User UserObj = (User)session.getAttribute("theUser");
             if(UserObj != null)
            {
                out.print("object is not  null");
                url = "/main.jsp?user=theUser";
                out.print("url is" + url);
            }
            else
            {
                out.print("object is null");
                url = "/home.jsp";
            }
     
        }
       else if (action.equalsIgnoreCase("Participate"))
        {
    
            
             System.out.print("Action is" + action);
            User UserObj_participate = (User)session.getAttribute("theUser");
            if(UserObj_participate != null)
            {
                String code = request.getParameter("studycode");
                System.out.println("Code is" + code);
                if (code == null)
                {
                 List<Study> allStudies=  obj.getStudies("start");                
                System.out.println("size is "+allStudies.size());
                request.setAttribute("openStudyList", allStudies);
                 url = "/participate.jsp?user=theUser";
                }
                else
                {
                 
                 Study study=obj.getStudy(code);
                 request.setAttribute("study", study);
                 ArrayList<String> answerlist=study.getAnsList();
                 System.out.println(answerlist.get(0));
                 request.setAttribute("answerlist", answerlist);
                  out.print("inside participate"+study.getQuestion());
                 url = "/question.jsp?user=theUser";
                }
            }
            else
            {
                url = ("/login.jsp");
            }
        }
        
         else if (action.equalsIgnoreCase("edit"))
         {
  
              out.println("Action is" + action);
           User UserObj_edit = (User)session.getAttribute("theUser");
           String email = UserObj_edit.getEmail();
             if(UserObj_edit != null)
             {                
                 String code = request.getParameter("studyCode");
                 out.println("In edit Code is" + code + "email is" + email);   
                 
                 Study s = obj.getStudy(code, email);
                 ArrayList answers=s.getAnsList();
                 System.out.println("Image: "+s.getGetImageURL());
                 System.out.println("data in new object" + s.getStudyName() + s.getEmail() + s.getStudyCode());
                 System.out.println("Answers are "+answers.get(0)+", "+answers.get(1)+", "+answers.get(2));
                 request.setAttribute("study", s);
                 
                 url = "/editstudy.jsp?user=theUser"; 
                
             }
             else
             {
                 url = ("/login.jsp");
             }   
             }
        else if (action.equalsIgnoreCase("update"))
        {
             System.out.print("Action is" + action);             
             String code = request.getParameter("studyCode");
             System.out.println("Code is" + code);
             User UserObj_update = (User)session.getAttribute("theUser");
             String email = UserObj_update.getEmail();
             System.out.println(email);
             if(UserObj_update!= null)
             {                                 
                 String name = request.getParameter("study_name");
                 String question = request.getParameter("question_text");
                 int requestedParticipants = Integer.parseInt(request.getParameter("participants"));
                 String description = request.getParameter("desc");
                 
                 Study study = obj.getStudy(code);
                 System.out.println("/////Before update///" +study.getStudyName() + study.getQuestion() + study.getNumofParticipants() + study.getDescription()+ study.getStudyCode());
                 study.setStudyName(name);
                 study.setQuestion(question);
                 study.setRequestedParticipants(requestedParticipants);
                 study.setDescription(description);
                 String[] answerValues=request.getParameterValues("DynamicTextBox");
                 System.out.println("Answers are "+answerValues[1]);
                  ArrayList<String> answers=new ArrayList<>();
             answers.add(answerValues[0]);
             answers.add(answerValues[1]);
             answers.add(answerValues[2]);
             
             if(answerValues.length==4)
             {
                  answers.add(answerValues[3]);
             }
              if(answerValues.length==5)
             {
                  answers.add(answerValues[3]);
                  answers.add(answerValues[4]);
             }
                 
             
            // answers.add(answerValues[3]);
            // answers.add(answerValues[4]);
             study.setAnsList(answers);
                 obj.updateRecord(code,study);
                 System.out.println("/////After update///" +study.getStudyName() + study.getQuestion() + study.getNumofParticipants() + study.getDescription()+ study.getStudyCode());
                 List<Study> newStudies = obj.getStudiesFor(email);
                 request.setAttribute("newStudy", newStudies);
                 url = ("/studies.jsp?user=theUser");                 
             }
             else{
                 url = ("/login.jsp");
             }        
        }
       
//         else if (action.equalsIgnoreCase("studies"))
//        {  
//            System.out.println("inside GET in studies");
//           List<Study> allStudies1=  obj.getStudies();
//           System.out.println("inside studies in my studies"+allStudies1.size());
//           request.setAttribute("newStudy", allStudies1);
//             url = ("/studies.jsp?user=theUser"); 
//        }
//        
       
        
        else if (action.equalsIgnoreCase("studies"))
        {  
//            if(session.getAttribute("theUser")== null && session.getAttribute("theAdmin") == null){
//                url="/login.jsp";
//            }
//            else if(session.getAttribute("theUser") != null){
//                System.out.println("inside GET in studies");
//                List<Study> allStudies1=  obj.getStudies();
//                System.out.println("inside studies in my studies"+allStudies1.size());
//                request.setAttribute("newStudy", allStudies1);
//                url = ("/studies.jsp?user=theUser"); 
//            }
             User UserObj = (User)session.getAttribute("theUser");
             if(UserObj != null)
             {
             String email=UserObj.getEmail();
             
            out.print("inside GET in studies"+email);
           List<Study> allStudies1=  obj.getStudiesFor(email);
           System.out.println("inside studies in my studies"+allStudies1.size());
           if(allStudies1.size() > 0)
           {
            request.setAttribute("newStudy", allStudies1);
           }
           else
           {
               request.setAttribute("newStudy", null);
           }
             url = ("/studies.jsp?user=theUser"); 
               }
             else
             {
              url = ("/login.jsp");
             }
        }
        else if (action.equalsIgnoreCase("adminstudies")){
            if(session.getAttribute("theAdmin") != null){
                List<Study> adminStudies=  reportObj.getQuestions("UNDER REVIEW");
                System.out.println("inside studies in my admin studies"+adminStudies.size());
               
                
                request.setAttribute("newStudy", adminStudies);
                url = ("/reportques.jsp?user=theAdmin");
            }
        }
        
        else if(action.equalsIgnoreCase("report"))
        {
             User UserObj = (User)session.getAttribute("theUser");
             
             if(UserObj != null)
            {
                
                System.out.println("Study Code"+request.getParameter("studycode"));
                System.out.println("Reporter Email"+request.getParameter("ReporterEmail"));
                
                if(request.getParameter("studycode")!=null)
                {
                        url="/confirmrep.jsp";
                        String studyCode = request.getParameter("studycode");
                        System.out.println("inside reported : Studycode is : "+ studyCode);
                        Study study = new Study();
                        study.setStudyCode(studyCode);
                        reportObj.addReported(study);
                }
            }
             
        }
       else if (action.equalsIgnoreCase("add"))
        {  
            Study study = new Study();
              System.out.print("Action is" + action); 
             User UserObj_add = (User)session.getAttribute("theUser");
             
             study.setUsername(UserObj_add.getName());
             String username = UserObj_add.getName();
             System.out.println(username);
             String email = UserObj_add.getEmail();
             study.setEmail(email);
              System.out.println(email);
             if(UserObj_add != null)
             { 
                 // count=count+1;
                 System.out.print("object not null");
                 String name = request.getParameter("study_name");
                 String question = request.getParameter("question_text");
                 int numOfParticipants = Integer.parseInt(request.getParameter("participant_text"));
                 String description = request.getParameter("desc");
                 String imgURL = request.getParameter("img");
                 System.out.println("URL is "+imgURL);
                 String[] answerValues=request.getParameterValues("DynamicTextBox");
                 System.out.println("Answers are "+answerValues[1]);
              //String code = request.getParameter("studyCode");
                 String status = request.getParameter("status");
                 String noofanswers = request.getParameter("answers");
             
             Integer numanswers = Integer.parseInt(noofanswers);
              System.out.println(name+"    "+question+"    "+noofanswers +"   "+ numanswers);
             answerlist = new ArrayList<String>();
             for(int i=0; i< numanswers; i++)
             {
                 
                 answerlist.add(request.getParameter("answers"));
                  System.out.println("List"+answerlist.get(i));
                 
             }
             study.setStudyName(name);
             study.setDescription(description);
             study.setgetImageURL(imgURL);
             study.setRequestedParticipants(numOfParticipants);
             study.setStatus(status);
             study.setQuestion(question);
             ArrayList<String> answers=new ArrayList<>();
             answers.add(answerValues[0]);
             answers.add(answerValues[1]);
             answers.add(answerValues[2]);
             
             if(answerValues.length==4)
             {
                  answers.add(answerValues[3]);
             }
              if(answerValues.length==5)
             {
                  answers.add(answerValues[3]);
                  answers.add(answerValues[4]);
             }
                 
             
            // answers.add(answerValues[3]);
            // answers.add(answerValues[4]);
             study.setAnsList(answers);
             System.out.println(name + question + numOfParticipants + description);                
             //study = new Study(name,code,email,question,numOfParticipants,answerlist,description,status);
             // UserObj_add.setNumParticipation(count);
             
             System.out.print("After creating object");
             //  System.out.print(study.getStudyName() + study.getStudyCode()+ study.getQuestion() + study.getRequestedParticipants() + study.getDescription() +study.getStatus());                  
             obj.addStudy(study);
                 
                 study.setEmail(email);
                 List<Study> newStudies = obj.getStudiesFor(email);  
                  UserDB.updateUserStudies(UserObj_add);
                 System.out.println("hi");
                // newStudies.add(study);
                 System.out.println(study.getStatus());
                 System.out.println("Hi");
                 request.setAttribute("newStudy", newStudies);    
                 System.out.println(newStudies);
                 url = "/studies.jsp?user=theUser";
             }
             else
             { 
                 System.out.println("object is null");
                 url = ("/login.jsp");
             }         
        }
       else if (action.equalsIgnoreCase("start"))
        {   

              out.print("Action is" + action);
             
             User UserObj_stop = (User)session.getAttribute("theUser");
             String email = UserObj_stop.getEmail();
             if(UserObj_stop!= null)
             {   
                 String code = request.getParameter("studyCode");
                 System.out.println("Code in stop is " + code);  
                 Study s = obj.getStudy(code);           
                 if(s != null)               
                 {
                 obj.updateStatus(s, "start");
                 List<Study> newStudies = obj.getStudiesFor(email);
                 request.setAttribute("newStudy", newStudies);
                 url = "/studies.jsp?user=theUser"; 
                 }
             }            
             
        }
        else if (action.equalsIgnoreCase("stop"))
        {   

            out.print("Action is" + action);
             
             User UserObj_stop = (User)session.getAttribute("theUser");
             String email = UserObj_stop.getEmail();
             if(UserObj_stop!= null)
             {   
                 String code = request.getParameter("studyCode");
                 System.out.println("Code in stop is " + code);  
                 Study s = obj.getStudy(code);           
                 if(s != null)               
                 {
                 obj.updateStatus(s, "stop");
                 List<Study> newStudies = obj.getStudiesFor(email);
                 request.setAttribute("newStudy", newStudies);
                 url = "/studies.jsp?user=theUser"; 
                 }
             }            
         
             
        }
         else if (action.equalsIgnoreCase("approve"))
        {   out.print("Action is" + action);
             
             User UserObj_stop = (User)session.getAttribute("theAdmin");
             String email = UserObj_stop.getEmail();
             if(UserObj_stop!= null)
             {   
                 String code = request.getParameter("studyCode");
                 out.print("Code in approve is " + code);  
                 Study s = obj.getStudy(code);
                 reportObj.updateReportStatus(s,"APPROVED");
                  List<Study> adminStudies=  reportObj.getQuestions("UNDER REVIEW");
                System.out.println("inside studies in my admin studies"+adminStudies.size());
               
                
                request.setAttribute("newStudy", adminStudies);
              

                 url = ("/reportques.jsp?user=theAdmin");               
             }
             else
             {
                 url = ("/login.jsp");
             }
             
             }  
         
         else if(action.equalsIgnoreCase("reporthistory"))
         {
              out.print("Action is" + action);
             
             User UserObj_stop = (User)session.getAttribute("theUser");
             String email = UserObj_stop.getEmail();
             if(UserObj_stop!= null)
             {
                 List<Study> reportHistory = reportObj.getReportHistory(email);
                 
                 // System.out.println("first: "+reportHistory.get(1).getQuestion());
                   //System.out.println("first: "+reportHistory.get(2).getQuestion());
                 request.setAttribute("report", reportHistory);
                 url="/reporth.jsp?user=theUser";
             }
             else
             {
                 url="login.jsp";
             }
         }
        else if (action.equalsIgnoreCase("disapprove"))
        {   out.print("Action is" + action);
             
             User UserObj_stop = (User)session.getAttribute("theAdmin");
             String email = UserObj_stop.getEmail();
             if(UserObj_stop!= null)
             {   
                 String code = request.getParameter("studyCode");
                 out.print("Code in approve is " + code);  
                 Study s = obj.getStudy(code);
                 reportObj.updateReportStatus(s,"DISAPPROVED");
                  List<Study> adminStudies=  reportObj.getQuestions("UNDER REVIEW");
                System.out.println("inside studies in my admin studies"+adminStudies.size());
               
                
                request.setAttribute("newStudy", adminStudies);
              

                 url = ("/reportques.jsp?user=theAdmin");               
             }
             else
             {
                 url = ("/login.jsp");
             }
             
             
             }  
        
        if (action.equalsIgnoreCase("answer"))
        {   
           int coins;
           int participation; 
           int numOfParticipants;
           Answer ans = new Answer() ;
           String code = request.getParameter("studycode");
           int choice = Integer.parseInt(request.getParameter("number"));
            
           System.out.println("Action is " + action);
           User UserObj_participate = (User)session.getAttribute("theUser");
           String email=UserObj_participate.getEmail();
            if(UserObj_participate != null)
            {
                
               
                System.out.println("Code is " + code);
                if (code != null )
                {                   
                        ans.setChoice(choice);
                        ans.setEmail(email);
                        ans.setStudyCode(code);
                        
                        if(!AnswerDB.answerExists(email, code))
                            
                        {
                            System.out.println("ANSWER DOES NOT EXISTS");
                        ansdb.addAnswer(ans);
                        //Insert code for updating coin and participation in DB
                        coins=UserObj_participate.getNumCoins();
                        participation = UserObj_participate.getNumParticipation();
                        participation++;
                        coins++;                     
                       // UserObj_participate.setCoins(coins);
                      //  UserDB.updateParticipations(UserObj_participate);
                      //  UserDB.updateCoins(UserObj_participate);  
                        email=UserObj_participate.getEmail();
                        Study study = obj.getStudy(code);
                        numOfParticipants = study.getNumofParticipants();
                        numOfParticipants++;
                        study.setNumofParticipants(numOfParticipants);
                        UserObj_participate.setNumCoins(coins);
                        UserObj_participate.setNumParticipation(participation);  
                        obj.updateActParticipants(code, participation);
                        
                        
                        
                        UserDB.updateParticipations(UserObj_participate);
                        UserDB.updateCoins(UserObj_participate); 
                        
                        List<Study> allStudies=  obj.getStudies("start");                
                        System.out.println("size is "+allStudies.size());
                        request.setAttribute("openStudyList", allStudies);
                        url = "/participate.jsp?user=theUser";
                        
//                        List list = obj.getOpenStudies("start");
//                        request.setAttribute("allStudies", list);
//                        url= ("/participate.jsp?user=theUser");    
                }
                        else
                        {
                            
                            System.out.println("ANSWER EXISTS");
                            System.out.println("You have already given answ");
                            request.setAttribute("duplicateanswer", "duplicateanswer");
                            List<Study> allStudies=  obj.getStudies("start");                
                            System.out.println("size is "+allStudies.size());
                            request.setAttribute("openStudyList", allStudies);
                            url = "/participate.jsp?user=theUser";
                        }
                }  
            
               
                
                    }
            else
            {
                url = ("/login.jsp");
            }          
            
        }
        if (action.equalsIgnoreCase("sendemail"))
        {
           
        }
        
       
         getServletContext().getRequestDispatcher(url).forward(request, response);
       
       }catch(Exception e){
           System.out.println(e.toString());
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
