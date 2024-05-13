<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TechnoHub</title>
<link rel="stylesheet" type="text/css"
href="/TechnoHub/stylesheets/aboutUs.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap"
      rel="stylesheet">
  
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pacifico&effect=neon|outline|emboss|shadow-multiple">
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
href="/TechnoHub/stylesheets/navigation.css" /> 
    
  </head>
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
      <section class="aboutus">
        <div class="main">
          <div class="AboutUsText">
            <h2>Our Story</h2>
            <p align="justify">We're not just your average team. We're a family united by an unwavering commitment to each other and our collective vision. We set out with a single goal: "To stand out as the absolute best". With this mantra driving us forward, every member of our team embodies a relentless pursuit of excellence in everything we do."</p>
          </div>
        </div>
      </section>

      <section class="aboutus2">
        <div class="main">   
          <h2 class="font-effect-emboss">Meet The Team</h2>
        </div>
        </section>
      <hr>
      <section class ="indi1">
      <a href="https://www.linkedin.com/in/shaswat-maharjan-530892256/"><img src="${pageContext.request.contextPath}/resources/aboutimage/shas.jpg" style="border-radius:50%; margin-left:125px; "></a>
      <a href="https://www.linkedin.com/in/aadesh-shrestha-a72697249/"><img src="${pageContext.request.contextPath}/resources/aboutimage/aadu.png" style="border-radius:50%; margin-left: 150px;"></a>
      <a href="https://www.linkedin.com/in/subodh-lamichhane-166b79266/"><img src="${pageContext.request.contextPath}/resources/aboutimage/subodh.png" style="border-radius:50%; margin-left: 190px;"></a>
      <a href="https://www.linkedin.com/in/kabin-dongol-maharjan-0bb3a4290/"><img src="${pageContext.request.contextPath}/resources/aboutimage/kanib.png" style="border-radius:50%; margin-left: 160px;"></a>
      <a href="https://www.linkedin.com/in/amogh-man-bajracharya-4b97822a0"><img src="${pageContext.request.contextPath}/resources/aboutimage/amug.png" style="border-radius:50%; margin-left: 170px;"></a>
        
        <div class="main">    
            <h1 class="font-effect-emboss">Shaswat Nibha Maharjan</h1>
            <h1 class="font-effect-emboss">Aadesh Shrestha</h1>
            <h1 class="font-effect-emboss">Subodh Lamichhane</h1>
            <h1 class="font-effect-emboss">Kabin Dongol Maharjan</h1>
            <h1 class="font-effect-emboss">Amogh Man Bajracharya</h1>  
       </div>
  
          
    </section>
  

    </body>
</html>