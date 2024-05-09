<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
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
    <nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
          <a href="./pages/adminDashboard.jsp" class="navLinks">Home</a>
          <a href="#" class="navLinks">Products</a>
          <a href="#" class="navLinks">Contact Us</a>
        </div>


        <div class="topRight">
          <img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;"></i></a>
          <div class ="dropdown">
            <p class="d" style="font-size: 12px;">User</p></a>
            <div class="dropcontent">
              <a href="#">Profile</a>
              <a href="#">Log Out</a>
            </div>
          </div>
<%-- <a href="#">
          <img src="${pageContext.request.contextPath}/resources/images/navigation/cart.png" alt="Cart" style="width: 30px; padding-left: 10px; padding-top: 6px;"></i>
          </a> --%>
<form action= "/TechnoHub/LogoutServlet" method='post'>
<button type="submit" class="log-button">Log out</button>
</form>
        </div>

      </div>
    </nav>

<div class="product-container">
<div class="product-image">
<img src="${pageContext.request.contextPath}/resources/images/products/${product.productImageUrlFromPart}" alt="image" />
</div>
<div class="product-content">
<h1>${product.productName}</h1>
<h4>${product.productDescription}</h4>
<h4>${product.productCategory}</h4>
<div style="display:flex; justify-content: space-between">
<h4>${product.stock}</h4>
<h4>Rs. ${product.unitPrice}</h4>
</div>
<div class="buttons"> 
        <button class="common-btn">Buy Now</button>
        <button class="common-btn">Add to Cart</button>
    </div>
</div>
</div>

<div class="review-container">
<h1>Reviews</h1>
<div class="review-content">
<p> Review 1 </p>
<br />
<p> Review 2 </p>
</div>

</div>
</body>
</html>