<%--
	Document: aboutl.jsp
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

<c:if test="${param.user == 'theUser'}">
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
    </c:if>


 <c:if test="${param.user == 'theAdmin'}">
 <nav id="menu">
 <ul>
	    <li><a href="admin.jsp">Home</a></li>
        <li><a href="reportques.jsp?user=theAdmin">Reported Questions</a></li>
  </ul>
 </nav>
</c:if>

<%-- Section tag is used to write description  --%>
<section class="main">
    <h3>About us</h3>
    <p>Researchers Exchange Participations is a platform for researchers 
        to exchange participations</p>
    <p>The aim of this platform is to encourage researchers participate in each others
        user studies. Moreover, recruiting serious participants has been always a burden on
        a researcher's shoulder, thus, this platform gives researchers the opportunity
        to do that effectively and in a suitable time.</p>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>