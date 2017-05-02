<%--
	Document: login.jsp
	Created On: April 18, 2017
	Authors: rohank

 --%>
<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
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
<%-- Section to input login details --%>
<br/>
<h5 id="msg"> ${msg}</h5>
    <%-- Code to create login form--%>
    <form class="form-horizontal" action="UserController?action=login" method="post">
    
        <input type="hidden" name="action" value="login">
        	<div class="form-group">
       		 	<label class="col-sm-4 control-label" >Email Address *</label>
        	<div class="col-sm-4">
                    <input type="email"  class="form-control" id="email" name="email" required/>
        	</div>
       		</div>
       	<div class="form-group">
        	<label class="col-sm-4 control-label" >Password *</label>
        <div class="col-sm-4">
            <input class="form-control" type="password" id="password" name="password" required/>
        </div>
        </div>
         <div class="form-group">
    	<div class="col-sm-offset-4 col-sm-10">
        <input type="submit" value="Log in" class="btn btn-info" >
        <!--<input type="submit" formaction="UserController?action=login" class="btn btn-primary" value="Admin">-->
		</div>
		</div>
    </form>
    <div class="row col-md-4 col-md-offset-4">
    <%-- Code to go to Sign up for a new account  --%>
    <a href="signup.jsp" id="sign_up_link">Sign up for a new account</a>
    </div>
<br/>
<br/>
<br/>

    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>