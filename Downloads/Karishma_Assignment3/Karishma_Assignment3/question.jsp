<%--
	Document: question.jsp
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
        //request.setAttribute("msg", "Session has ended.  Please login.");
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
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/small_tree.jpg" class="img-responsive" height="250" width="250" alt="Tree"/>

<%--Code to rating the Question --%>
    <p>${study.getQuestion()}</p>

<!--        <form action="StudyController?user=theUser&studycode=${study.studyCode}" method="post">
        <div class="radio">
            <input type="radio" name="number" value="1" required>${answerlist[0]}
            </div>
<div class="radio">
            <input type="radio" name="number" value="2">${answerlist[1]}
            </div>
            <div class="radio">
            <input type="radio" name="number" value="3">${answerlist[2]}
            </div>  
    -->
    
     <form action="StudyController?user=theUser&action=answer&amp;studyCode=${study.studyCode}" method="post">
        <c:forEach var="answer" items="${study.ansList}">
            <c:if test="${not empty answer}">
            <div class="radio">
            <input type="radio" name="number" value="${answer}" required> ${answer}
            </div>
            </c:if>
            </c:forEach>
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
       <span>
            <input type="hidden" name="studycode" value="${study.studyCode}"> 
            <input type="hidden" name="action" value="answer"> 

            <!--<input type="submit" value="Submit" name="submit" />-->
            <input type="submit" value="Submit" name="submit" />

        </span>

         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>