<%-- 
    Document: requestc.jsp
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
<a href="admin.jsp?user=Hello,Admin" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Message Sent   --%>
<br/>
<br/>
<br/>
    <h3 class="text-center">Question ${param.status} ..</h3>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
