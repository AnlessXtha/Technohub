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


	<div class="top">
		<h1 class="heading">
			SHOPPING CART <a
				href="${pageContext.request.contextPath}/OrderDetailsServlet"><button
					class="history-btn" style="margin-left: 410px;">View
					History</button></a>
		</h1>
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
						<td><img
							src="${pageContext.request.contextPath}/resources/images/products/${cartProduct.productImageCartUrlFromPart}"
							alt="Product Image" class="product-image" style="width: 100px;"></td>
						<td>${cartProduct.unitPrice}</td>
						<td>${cartProduct.cartProductQuantity}</td>
						<td>${cartProduct.cartLineTotal}</td>
						<td>
							<form id="deleteForm-${cartProduct.productName}" method="post"
								action="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_MODIFYCART}">
								<input type="hidden" name="<%=StringUtils.REMOVE_ID %>"
									value="${cartProduct.productID}" />
								<button type="button"
									onclick="confirmDelete('${cartProduct.productName}')"
									class="remove-btn">Remove</button>
							</form>
						</td>
						<td>
					</tr>
				</c:forEach>


			</tbody>

		</table>
		<form id="checkoutForm"
			action="${pageContext.request.contextPath}/CheckoutServlet"
			method="post">
			
			<c:forEach var="cartProduct" items="${cartProductsList}">
				<input type="hidden" name="productID"
					value="${cartProduct.productID}" />				
			</c:forEach>

			<button type="button" onclick="checkout()" class="checkout-btn">Checkout</button>
		</form>
	</div>

</body>

<script>
	function confirmDelete(productName) {
		if (confirm("Are you sure you want to remove this product: "
				+ productName + "?")) {
			document.getElementById("deleteForm-" + productName).submit();
		}
	}

    function checkout() {
        var cartProductsList = ${cartProductsList};

        if (cartProductsList.length === 0) {
            alert("Your cart is empty. Please add products to proceed to checkout.");
        } else {
            if (confirm("Are you sure you want to checkout?")) {
                document.getElementById("checkoutForm").submit();
            }
        }
    }
</script>
</html>