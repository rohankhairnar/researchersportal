<%--
	Document: reportques.jsp
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
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp?user=theAdmin" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
  -->
  <div class="table-responsive">
  <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Action</th>		
        </tr>
        
            <%-- First study details --%>
            <%--<td>I enjoy outdoor activities.</td> --%>
<!--            <%
                List<Study> list = (ArrayList<Study>) request.getAttribute("newStudy");              
                for(Study study : list) {
            %>   
           
            
             <tr>
                 
                <td><b><% out.print(study.getQuestion());%> </b></td> 
                
            <td>
            <form action="request.jsp" method="post">
            <input type="submit" class="btn btn-primary" formaction="requestc.jsp?user=theAdmin&amp;status=Removed"  value="Approve">
            <input type="submit" class="btn btn-primary" formaction="requestc.jsp?user=theAdmin&amp;status=Not%20Removed"  value="Dispprove">
            </form>
           </td>
  

        </tr>
        <% } %>
         TODO Add one more for removal and not re 
        
        <tr> 
            <%-- Second study details --%>
            <td></td>
            <td></td>

        </tr>-->
             <c:forEach var="report" items="${newStudy}">
        <tr>
            <%-- First study details --%>
            <td>${report.question}</td>
            <td>
             <form action="StudyController" method="post">
            <input type="submit" class="btn btn-primary" formaction="StudyController?user=theUser&amp;studyCode=${report.studyCode }&amp;action=approve"  value="Approve">
            <input type="submit" class="btn btn-primary" formaction="StudyController?user=theUser&amp;studyCode=${report.studyCode }&amp;action=disapprove"  value="Dispprove">
             </form>
           </td>

        </tr>
        </c:forEach>
       
        </table>
        </div>
  
  
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>