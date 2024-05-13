<%@page import = "util.StringUtils" %>
<%@page import = "model.ProductModel" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%
// Get the session and request objects
HttpSession userSession = request.getSession ();
String productName = (String) userSession. getAttribute ("productName") ;
%> 
<%
String contextPath = request.getContextPath();
%>
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

	<% String usernameSession = (String) session.getAttribute("userName"); %>
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
          <img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;"></i></a>
          <div class ="dropdown">
            <p class="d" style="font-size: 12px;">Admin</p></a>
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
    
    <div class="container">
    <h1>Update Product</h1>
    <form action="${pageContext.request.contextPath}/ModifyServlet" method="post" enctype="multipart/form-data">
        <!-- Hidden input field for productId -->
        <input type="hidden" id="updateID" name="updateID" value="${product.productID}">
        
        <div class="form-group">
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" value="${product.productName}" required>
        </div>
        <div class="form-group">
            <label for="productDescription">Product Description:</label>
            <input type="text" id="productDescription" name="productDescription" value="${product.productDescription}">
        </div>
        <div class="form-group">
            <label for="productCategory">Category:</label>
            <select id="productCategory" name="productCategory" required>
                <!-- Options for product categories -->
                <c:if test="${product.productCategory eq 'Mouse'}">
			        <option value="Mouse" selected>Mouse</option>
			        <option value="Keyboard">Keyboard</option>
	               	<option value="HeadPhone">HeadPhone</option>
	                <option value="Microphone">Microphone</option>
			    </c:if>
			    <c:if test="${product.productCategory eq 'Keyboard'}">
			        <option value="Keyboard" selected>Keyboard</option>
			        <option value="HeadPhone">HeadPhone</option>
	                <option value="Microphone">Microphone</option>
	                <option value="Mouse">Mouse</option>
			    </c:if>
			    <c:if test="${product.productCategory eq 'HeadPhone'}">
			        <option value="HeadPhone" selected>HeadPhone</option>
			        <option value="Microphone">Microphone</option>
                    <option value="Mouse">Mouse</option>
                    <option value="Keyboard">Keyboard</option>
			    </c:if>
			    <c:if test="${product.productCategory eq 'Microphone'}">
			        <option value="Microphone" selected>Microphone</option>
			        <option value="Mouse">Mouse</option>
                   	<option value="Keyboard">Keyboard</option>
                   	<option value="HeadPhone">HeadPhone</option>
			    </c:if>
			</select>
            </select>
        </div>
        <div class="wrapper">
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock" min="0" value="${product.stock}" required>
            </div>
            <div class="form-group">
                <label for="unitPrice">Unit Price:</label>
                <input type="number" id="unitPrice" name="unitPrice" value="${product.unitPrice}" required>
            </div>
        </div>
        <div class="form-group">
        
		<input type="file" id="productImage" name="productImage" accept="image/*">

         
            <%-- <label for="productImage">Product Image:</label>
            <input type="file" id="productImage" name="productImage" accept="image/*" value="${product.productImageFromUrl}"> --%>
        
       <%--  <c:if image="${product.productImageFromUrl eq ''}">
            <label for="productImage">Product Image:</label>
            <input type="file" id="productImage" name="productImage" accept="image/*">
        </c:if> --%>
        </div>
        <div class="form-btn">
            <a href="${pageContext.request.contextPath}/${StringUtils.SERVLET_URL_PRODUCTLIST}">
                <button class="btn" type="button">Cancel</button>
            </a>
            <button class="btn" type="submit">Update Product</button>
        </div>
    </form>
</div>


</body>
</html>