<%-- 
    Document: reporth.jsp
    Created On: April 18, 2017
    Authors: rohank
--%>
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
      //  request.setAttribute("msg", "Session has ended.  Please login.");
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
<%-- Code to go back to Main page  --%>
<br>
<a href="main.jsp?user=theUser" id="back_to_page">&laquo;Back to the Main Page</a>
<br>
 <div class="table-responsive">
<table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Report Date</th>
            <th>Report Question</th>		
            <th>Report Status</th>
            
        </tr>
        
         <c:forEach var="report" items="${report}">
        <tr>
            <%-- First study details --%>
            <td>${report.reportDate }</td>
            <td>${report.question }</td>
            <td>${report.reportStatus }</td>
           
        </tr>
        </c:forEach>

<!--        <tr>
            <%-- First study details --%>
            <td>01/15/2016</td>
            <td>How much do you...</td>
            <td>Approved</td>
            

        </tr>
        <tr> 
            <%-- Second study details --%>
            <td>01/18/2016</td>
            <td>What do you do w...</td>
            <td>Pending</td> 
            

        </tr>-->
        
        
    </table>
    </div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>