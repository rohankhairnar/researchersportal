<%--
	Document: editstudy.jsp
	Created On: April 18, 2017
	Authors: rohank

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/editstudy.js">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</script>
<%
response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
try{
    if(session.getAttribute("theUser")==null )
    {
        //request.setAttribute("msg", "Session has ended.  Please login.");
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
<script type="text/javascript" src="js/editstudy.js">
</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="UserController?action=main&user=theUser" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="StudyController?user=theUser" method="post">
    
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name" value="${study.getStudyName()}" required />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="question_text" value="${study.getQuestion()}" required/>
         </div>
            </div>
        
          <input type="hidden" name="country" id="optionsAvailable" value="${fn:length(study.getAnsList())}">
            <c:set var="totalHours" value="${0}"/>
            <c:forEach var="answer" items="${study.getAnsList()}">
            
            <input type="hidden" name="country" id="hiddenAnswers${totalHours }" value="${answer}">
            <c:set var="totalHours" value="${totalHours+1}"/>
            </c:forEach>
        
        <%-- Img tag is used to import image --%>
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">
<!--        <img src="images/placeholder.jpg" class="img-responsive" height="50" width="75" alt="Edit"/>
        <button type="button" class="btn btn-primary">Browse</button>-->
<input type="file" name="img">
         </div>
            </div>
        
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
         <div class="col-sm-4"> 
        <input type="text" class="form-control" name="participants" value="${study.getNumofParticipants()}" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select class="form-control" id="edit_study_answers">
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select> 
         </div>
            </div>
        
        
        <div id="TextBoxContainer1">
    <!--Textboxes will be added here -->
		</div>
       
       
       <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
         <div class="col-sm-4"> 
        <textarea name="desc" class="form-control" required> ${study.getDescription()}</textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Update</button>
        <input type="hidden" name="action" value="update"> 
        <input type="hidden" name="studyCode" value="${study.getStudyCode()}">

         </div>
            </div>
            <br/><br/><br/>
    </form>
</section>
        <script type="text/javascript">
$(document).ready(function() {
	 var options = document.getElementById('optionsAvailable').value;
		$("#edit_study_answers").val(options).change();
//		alert("done");

		if(options == 3){
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			
		}else if(options == 4){
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value; 
			var option4 = document.getElementById('hiddenAnswers3').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			document.getElementById('4').value = option4;
		}else{
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value; 
			var option4 = document.getElementById('hiddenAnswers3').value; 
			var option5 = document.getElementById('hiddenAnswers4').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			document.getElementById('4').value = option4;
			document.getElementById('5').value = option5;
		}
	
});

</script>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>