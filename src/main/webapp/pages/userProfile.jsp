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
</head>
<body>

	<nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
          <a href="${pageContext.request.contextPath}/${StringUtils.SERVLET_URL_PRODUCTLIST}" class="navLinks">Home</a>
          <a href="./pages/products.jsp" class="navLinks">Products</a>
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
		<h1>USER PROFILE</h1>
		<a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_EDITPROFILE}">
			<button class="edit-btn">
				<b>Edit Profile</b>
			</button>
		</a>
	</div>
	<div class="container">

        <div class="profileimg">
          <img src ="${pageContext.request.contextPath}/resources/images/user.png" style="height: 100px;">
        </div>
        
        <div class="info" style="justify-content: space-evenly ; display: flex;">
          <p>First Name: ${user.firstName}
          </p>
          <p>Last Name: ${user.lastName}</p>
        </div>

        <div class="info" style="justify-content: space-evenly ; display: flex;">
          <p>Phone Number: ${user.contactNumber}
          </p>
          <p>Address: ${user.address}</p>
        </div>

        <div class="info" style="justify-content: space-evenly ; display: flex;">
          <p>E-mail: ${user.email}
          </p>
          <p>Username: ${user.username} </p>
        </div>
      </div>
    
</body>
</html>