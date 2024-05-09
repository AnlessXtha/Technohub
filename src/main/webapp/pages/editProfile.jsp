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

	<nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
          <a href="${pageContext.request.contextPath}/${StringUtils.SERVLET_URL_PRODUCTLIST}" class="navLinks">Home</a>
          <a href="#" class="navLinks">Products</a>
          <a href="#" class="navLinks">Contact Us</a>
        </div>


        <div class="topRight">
          <img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;"></i></a>
          <div class ="dropdown">
            <p class="d" style="font-size: 12px;">${user.username}</p></a>
            <div class="dropcontent">
              <a href="#">Profile</a>
              <a href="#">Log Out</a>
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
          <img src ="${pageContext.request.contextPath}/resources/images/users/amug.png" style="height: 100px;">
        </div>
        
        <form>
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
							id="username" name="username" value="${user.username}" required>
		        	</div>
		        </div>
	        </div>
	        <div class="button">
	        <a href="#">
				<button class="edit-btn">
					<b>Save Changes</b>
				</button>
			</a>
	        </div>
        </form>
      </div>
    
</body>
</html>