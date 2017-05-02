<%--
	Document: header.jsp
	Created On: April 18, 2017
	Authors: rohank

 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
       
        <title>To-Do Planner</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        
        <!-- BootStrap -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
                    <c:if test="${param.user == null}">
                   <li> <a href="home.jsp"> To-Do Planner</a></li>
                        </c:if>
                   
                    <c:if test="${param.user == 'theUser'}">
                    <a href="home.jsp?user=theUser"> <li>Researchers Exchange Participations</li></a>
                        </c:if>
                    
                     <c:if test="${param.user == 'theAdmin'}">
                    <a href="home.jsp?user=theAdmin"> <li>Researchers Exchange Participations</li></a>
                        </c:if>
                    
                </ul>
                <ul class="right">
                    <c:if test="${param.user == null}">
                        <li><a href="about.jsp">About Us</a></li>
                        <li><a href="how.jsp">How it Works</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        </c:if>
                        <c:if test="${param.user == 'theUser'}">
                        <li><a href="UserController?action=about&user=theUser">About Us</a></li>
                        <li><a href="UserController?action=how&user=theUser">How it Works</a></li>
                        <li> <c:if test="${not empty theUser.name}">
                Hello,${theUser.name}
            </c:if>
            <c:if test="${empty theUser}">
                <a href="login.jsp">Login</a>&nbsp;
            </c:if></li>
                        <li><c:if test="${not empty theUser.name}">
                                <a href="home.jsp">Logout</a>
                            </c:if></li>
                        </c:if>
                        <c:if test="${param.user == 'theAdmin'}">
                        <li><a href="aboutl.jsp?user=theAdmin">About Us</a></li>
                        <li><a href="admin.jsp?user=theAdmin">How it Works</a></li>
                        <li>Hello, Admin</li>
                        <li><c:if test="${not empty theAdmin.name}">
                                <a href="home.jsp">Logout</a>
                            </c:if></li>
                        </c:if>
                </ul>

            </nav>



        </div>

