<%--
	Document: participate.jsp
	Created On: April 18, 2017
	Authors: rohank

 --%>
<%@page import="java.util.List"%>
<%@page import="murach.business.Study"%>
<%@page import="java.util.ArrayList"%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%
response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
try{
    if(session.getAttribute("theUser")==null )
    {
     //   request.setAttribute("msg", "Session has ended.  Please login.");
       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
       
     }
    else if(session.getAttribute("theAdmin")==null)
    {
      //  request.setAttribute("msg", "Session has ended.  Please login.");
       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
    }
    else
    {
        System.out.println("user logged in is::"+session.getAttribute("theUser").toString());
    }
}
    catch(NullPointerException e)
            {
            
            
            }
    %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>  
          <li>Coins(${theUser.numCoins}) </li>
       <li>Participants (0) </li>
        <li>Participation(${theUser.numParticipation}) </li>
      <li><br></li>
       <li><a href="UserController?action=main&user=theUser">Home</a></li>
        <li><a href="StudyController?action=participate&user=theUser">Participate</a></li>
        <li><a href="StudyController?action=studies&user=theUser">My Studies</a></li>
        <li><a href="recommend.jsp?user=theUser">Recommend</a></li>
        <li><a href="contact.jsp?user=theUser">Contact</a></li>
    </ul>

</nav>
<%-- Section to display studies and participate in that study--%>
<div>
   
     <h3 class="text-left">
<!--         <span class="label label-default " >Studies</span>-->
  <span ><a class="label label-default" href="StudyController?action=studies&user=theUser">Studies</a></span>
     <span ><a class="label label-default" href="StudyController?action=reporthistory&?user=theUser">Report History</a></span></h3>
     </div>
     
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
    
     <div class="table-responsive">
           <%
        if (request.getAttribute("openStudyList") != null) {
    %>

    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
            
        </tr>
        
<!--        <%
            List<Study> list = (ArrayList<Study>) request.getAttribute("openStudyList");

            for (Study study : list) {
        %>   
        <tr>
           <input type="hidden"  name="studycode" value="<% out.print(study.getStudyCode());%>"/>
            <td class="td_participate_odd"><b><% out.print(study.getStudyName());%></b></td> 
                <td class="td_participate_odd" > <img src="images/small_tree.jpg" alt="tree" height="42" width="92" /><b></b></td>
                <td class="td_participate_odd"><b><% out.print(study.getQuestion());%></b></td>
                <td class="td_participate_odd"><input class="part_button" type="submit" name="action" value="Participate" /></td>
                <td class="td_participate_odd"><input class="part_button" type="submit" name="action" value="Report" /></td>
              
                   
                    <input type="hidden"  name="ReporterEmail" value="${theUser.getEmail()}"/>    
                
        </tr>
          <% }
        } else {
        %>      
        <h3> no entries found </h3>
        <% }%>      
        <tr>
            <td></td>
            <td></td>
            <td></td> 
            <td></td>
            <td></td> 
        </tr>-->
        <c:forEach var="study" items="${openStudyList}">
        <tr>
            <%-- First study details --%>
            <td>${study.studyName }</td>
            <td><img src="images/small_tree.jpg" alt="Tree"></td>
            <td>${study.question }</td>
            <td><form action="StudyController?user=theUser&amp;action=participate&amp;studycode=${study.studyCode}" method="post"><input type="submit" class="participate_button"
                                                                                value="participate" /></form></td>
            <td><form action="StudyController?user=theUser&amp;action=report&amp;studycode=${study.studyCode}" method="post"><input type="submit" class="participate_button"
                                                                                value="report" /></form></td>

        </tr>
        </c:forEach>
        
    </table>
    </div>
   

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>