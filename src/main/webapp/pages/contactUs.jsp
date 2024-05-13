<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechnoHub</title>
  <link rel="stylesheet" href="/TechnoHub/stylesheets/contactUs.css">
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
  </head>
  <body>

 	<%
	String userSession = (String) session.getAttribute("userName");
	int userTypeSession = (int) session.getAttribute("userType");
	String cookieUsername = null;
	String cookieSessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(StringUtils.USER))
		cookieUsername = cookie.getValue();
			if (cookie.getName().equals(StringUtils.JSESSIONID))
		cookieSessionID = cookie.getValue();
		}
	}
	%>
	
	
	<nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_HOME_LIST_PAGE}"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
	        <c:if test="<%= userTypeSession == 0 %>">
			    <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_HOME_LIST_PAGE}" class="navLinks">Home</a>
			</c:if>
			<c:if test="<%= userTypeSession == 1 %>">
			    <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_PRODUCTLIST}" class="navLinks">Dashboard</a>
			</c:if>
        
          <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_PRODUCTLISTCUSTOMER}" class="navLinks">Products</a>
          <a href="${pageContext.request.contextPath}/pages/aboutUs.jsp" class="navLinks">About Us</a>
          <a href="${pageContext.request.contextPath}/pages/contactUs.jsp" class="navLinks">Contact Us</a>
        </div>



        <div class="topRight">
        <a href="${pageContext.request.contextPath}${StringUtils. SERVLET_URL_CARTDETAILS}"><img src="${pageContext.request.contextPath}/resources/images/navigation/cart.png" alt="Cart" style="width: 30px; margin-right: 15px; padding-top: 6px;"></a>
          <img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;">
          <div class ="dropdown">
            <p class="d" style="font-size: 12px;"><%=userSession%></p>
            <div class="dropcontent">
              <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_USERPROFILE}">Profile</a>
            </div>
          </div>
			<form action= "/TechnoHub/LogoutServlet" method='post'>
				<button type="submit" class="log-button">Log out</button>
			</form>
        </div>

      </div>
    </nav>
    <body>
      <!-- division for whole form page -->
      <div class = "container">
            <form name="form" onsubmit="validateForm()">
              <div class = "inputform">
                <label for="name">First Name</label>
                <input id="name" name="fname" type="text" >
              </div>
              <div class = "inputform">
                <label for="name">Last Name</label>
                <input id="name" name="lname" type="text" >
              </div>
              <div class = "inputform">
                <label for="name">E-mail</label>
                <input id="name" name="email" type="email" >
              </div>
              <div class = "inputform">
                <label for="name">Phone No.</label>
                <input id="name" name="number" type="number" >
              </div>
              <div class = "inputform">
                <label for="name">Your Message</label>
                <input id="name" name="message" type="message" >
              </div>
              <input class="formbtn" type="submit">
            </form>
      </div>


  </body>
</html>