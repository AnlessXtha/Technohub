<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mouse</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/singleProductPage.css" />
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
			    <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_ADMIN_DASHBOARD}" class="navLinks">Dashboard</a>
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

	<div class="product-container">
		<div class="product-image">
			<img
				src="${pageContext.request.contextPath}/resources/images/products/${product.productImageUrlFromPart}"
				alt="image" />
		</div>
		<div class="product-content">
			<h1>${product.productName}</h1>
			<h4>Description: ${product.productDescription}</h4>
			<h4>Category: ${product.productCategory}</h4>
			<div style="display: flex; justify-content: space-between">
				<h4>Stock: ${product.stock}</h4>
				<h4>Price: Rs. ${product.unitPrice}</h4>
			</div>
			<div class="buttons">
				<form action="${pageContext.request.contextPath}/AddToCartServlet" method="post">
				<input type="hidden" name="productID" value="${product.productID}" />
				<input class="buy-amount" type= "number" id="cartProductQuantity" name="cartProductQuantity" min="1" max="${product.stock}" value="1" />
				<button class="common-btn">Add to Cart</button>
				</form>
			</div>
		</div>
	</div>

	<div class="review-container">
		<h1>Reviews</h1>
		<div class="review-content">
			<h2>No Review Available.</h2>
		</div>

	</div>
</body>
</html>