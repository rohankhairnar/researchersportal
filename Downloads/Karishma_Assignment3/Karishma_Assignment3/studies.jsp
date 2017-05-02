
<%--
	Document: studies.jsp
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
       // request.setAttribute("msg", "Session has ended.  Please login.");
       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
       
     }
    else if(session.getAttribute("theAdmin")==null)
    {
       // request.setAttribute("msg", "Session has ended.  Please login.");
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
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp?user=theUser" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=theUser" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >

<div class="table-responsive">
     
    <table class="table" >
        <%
               if(request.getAttribute("newStudy")!=null)
               {
                    %>
                    <tr class="table_participate">
                    <th><b> Study Name</b></th>   
                    <th><b> Requested participants</b></th> 
                    <th><b> Participations</b></th> 
                    <th><b> Status </b></th>
                    <th><b> Action</b></th> 
            </tr>
               
<!--      <%
                    List<Study> list = (ArrayList<Study>) request.getAttribute("newStudy");              
                for(Study study : list) {
            %>   
           
            
             <tr>
                 
                <td><b><% out.print(study.getStudyName());%> </b></td> 
                <td> <% out.print(study.getRequestedParticipants());%> <b></b></td>
                <td><b> <% out.print(study.getNumofParticipants());%> </b></td>
                <input type="hidden" id="code" name="studyCode" value="${study.StudyCode}">
               <td class="td_participate_odd"><input onclick="getCode(<% out.print(study.getStudyCode());%>)" class="part_button" name="action" type="submit" value ="<% if((study.getStatus().equalsIgnoreCase("start")))
               
                { 
                    out.print("stop"); 
                }
                else
                {
                    out.print("start");
                }
//%>"/>
                                           
                </td>
                
                    <td class="td_participate_odd"><input onclick="getCode(<% out.print(study.getStudyCode());%>)" class="part_button" type="submit" name="action" value="edit" /></td> 
                

             </tr>
               
              <% }
                }
                if(request.getAttribute("newStudy")== null) 
                {                
                %>      
                 <h3> no entries found </h3> 
                <% }
                %>
        -->
         <c:forEach var="study" items="${newStudy}">
        <tr>
            <%-- First study details --%>
            <td>${study.studyName}</td>
            <td>${study.requestedParticipants}</td>
            <td>${study.numofParticipants}</td>
            <c:choose>
            <c:when test="${study.status == 'start' }">
            
                <td><form action="StudyController?user=theUser&amp;action=stop&amp;studyCode=${study.studyCode}" method="post">
            <button type="Submit" class="btn btn-primary">Stop</button></form></td>
            </c:when>
            <c:otherwise>
               <td><form action="StudyController?user=theUser&amp;action=start&amp;studyCode=${study.studyCode}" method="post">
            <button type="Submit" class="btn btn-primary">Start</button></form></td>
            </c:otherwise>
            </c:choose>
            <%-- Code to display edit page --%>
            <td><form action="StudyController?user=theUser&amp;action=edit&studyCode=${study.studyCode}" method="post">
                    <button type="submit" class="btn btn-primary">Edit</button></form></td>

        </tr>
        </c:forEach>
 
    </table>
                  <input type="hidden" id="code" name="studyCode">
    
</div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>