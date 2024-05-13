<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TechnoHub</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/addProduct.css" />
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
				<img
					src="${pageContext.request.contextPath}/resources/images/navigation/user.png"
					alt="Admin" style="width: 37px;"></i></a>
				<div class="dropdown">
					<p class="d" style="font-size: 12px;">Admin</p>
					</a>
					<div class="dropcontent">
						<a href="#">Profile</a> <a href="#">Log Out</a>
					</div>
				</div>
				<form action="/TechnoHub/LogoutServlet" method='post'>
					<button type="submit" class="log-button">Log out</button>
				</form>
			</div>

		</div>
	</nav>

	<div class="container">
		<h1>Add Product</h1>
		<form action="/TechnoHub/AddProductServlet" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="productName">Product Name:</label> <input type="text"
					id="productName" name="productName" required>
			</div>
			<div class="form-group">
				<label for="productDescription">Product Description:</label> <input
					type="text" id="productDescription" name="productDescription">
			</div>
			<div class="form-group">
				<label for="productCategory">Category:</label> <select
					id="productCategory" name="productCategory" required>
					<option value="" selected disabled>-- Select --</option>
					<option value="Mouse">Mouse</option>
					<option value="Keyboard">Keyboard</option>
					<option value="HeadPhone">HeadPhone</option>
					<option value="Microphone">Microphone</option>
				</select>
			</div>
			<div class="wrapper">
				<div class="form-group">
					<label for="stock">Stock:</label> <input type="number" id="stock"
						name="stock" min="0" required>
				</div>
				<div class="form-group">
					<label for="unitPrice">Unit Price:</label> <input type="number"
						id="unitPrice" name="unitPrice" required>
				</div>
			</div>
			<div class="form-group">
				<label for="productImage">Product Image:</label> <input type="file"
					id="productImage" name="productImage" accept="image/*" required>
			</div>
			<div class="form-btn">
				<a
					href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_PRODUCTLIST}">
					<button class="btn" type="button">Cancel</button>
				</a>
				<button class="btn" type="submit">Add Product</button>
			</div>

		</form>
	</div>

</body>
</html>