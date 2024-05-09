<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/cart.css" />
</head>
<body>
<%
	String userSession = (String) session.getAttribute("userName");
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
	
<%-- 	<div class="heading">
		<a
			href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_USERPROFILE}"><button>View
				Profile</button></a>
	</div>

	<div class="welcome-container">
		<h1>
			Hello
			<%=cookieUsername%>
		</h1>
		<h3>
			Cookie session Id is
			<%=cookieSessionID%></h3>
		<p>
			Session username:
			<%=userSession%></p>
		<a href="${pageContext.request.contextPath}/index.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>

	</div> --%>
	
	
	<div class="top">
      <h1 class="heading">SHOPPING CART <a href="${pageContext.request.contextPath}/OrderDetailsServlet"><button class="history-btn" style="margin-left: 410px;">View History</button></a></h1>
    </div>
    <hr>
      <div class="container">
        <table>
          <thead>
            <tr>
              <th>Product</th>
              <th>Image</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
          
          <c:forEach var="cartProduct" items="${cartProductsList}">
	        <tr>
	          <td>${cartProduct.productName}</td>
	          <td><img src="${pageContext.request.contextPath}/resources/images/products/${cartProduct.productImageCartUrlFromPart}" alt="Product Image" class="product-image" style="width: 100px;"></td>
	          <td>${cartProduct.unitPrice}</td>
	          <td>${cartProduct.cartProductQuantity}</td>
	          <td>${cartProduct.cartLineTotal}</td>
	          <td> 
	          	<form id="deleteForm-${cartProduct.productName}" method="post" 
	                    action="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_MODIFYCART}">
                   <input type="hidden" name="<%=StringUtils.REMOVE_ID %>" value="${cartProduct.productID}" />
                   <button type="button"
                       onclick="confirmDelete('${cartProduct.productName}')" class="remove-btn">Remove</button>
	                </form>
	          </td>
	          <td>
	         </tr>
        </c:forEach>

            
          </tbody>

        </table>
        <button class="checkout-btn">Checkout</button>
      </div>
      
</body>

<script>
    function confirmDelete(productName) {
        if (confirm("Are you sure you want to remove this product: " + productName
                + "?")) {
            document.getElementById("deleteForm-" + productName).submit();
        }
    }
</script>
</html>