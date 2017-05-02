<%--
	Document: main.jsp
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
<%-- Code to display items in List --%>
<nav id="menu">
<c:if test="${param.user == 'theUser' }">
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
</c:if>
<c:if test="${param.user == 'theAdmin' }">
		<li><a href="admin.jsp">Home</a></li>
        <li><a href="StudyController?action=studies">Reported Questions</a></li>
</c:if>
</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
    <h3>How it Works</h3>
    <p>This site was built to help researchers conduct their user studies</p>
    <p>1 participation = 1 coin</p>
    <p><b>To participate,</b> go to "Participate" section and choose a study to complete</p>
    <p><b>To get participants,</b> submit your study here to start getting Participations. Inorder to do so, you must have enough coins in Your account</p>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>