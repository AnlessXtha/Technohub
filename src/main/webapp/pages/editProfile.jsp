<%@page import="util.StringUtils"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/userProfile.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/editProfile.css" />
</head>
<body>
	<% String userSession = (String) session.getAttribute("userName"); %>
	<% int userTypeSession = (int) session.getAttribute("userType"); %>
	
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
	<div class="label">
		<h1>EDIT PROFILE</h1>
		
	</div>
	<div class="container">

        <div class="profileimg">
          <img src ="${pageContext.request.contextPath}/resources/images/user.png" style="height: 100px;">
        </div>
        
        <form action="${pageContext.request.contextPath}/ModifyUserProfileServlet" method="post">
        	<input type="hidden" id="updateID" name="updateID" value="${user.username}">
        	
        	<div class='form-total'>
		        <div class='form-column'>
		        	<div class='form-row'>
		        		<label for="firstName">First Name:</label>
		        		<input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
		        	</div>
		        	<div class='form-row'>
		        		<label for="lastName">Phone Number:</label> 
		        		<input type="tel" id="contactNumber" name="contactNumber" value="${user.contactNumber}" required>
		        	</div>
		        	<div class='form-row'>
		        		<label for="email">Email:</label> <input type="email" id="email"
							name="email" value="${user.email}"  required>
		        	</div>
		        </div>
		        
		        <div class='form-column'>
		        	<div class='form-row'>
		        		<label for="lastName">Last Name:</label> 
		        		<input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
		        	</div>
		        	<div class='form-row'>
		        		<label for="email">Address:</label> <input type="text" id="address"
							name="address" value="${user.address}" required>
		        	</div>
		        	<div class='form-row'>
		        		<label for="username">Username:</label> <input type="text"
							id="username" name="username" value="${user.username}" readonly required>
		        	</div>
		        </div>
	        </div>
	        <div class="button">	        
				<button class="edit-btn" type="submit">
					<b>Save Changes</b>
				</button>
	        </div>
        </form>
      </div>
    
</body>
</html>