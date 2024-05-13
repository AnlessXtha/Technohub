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
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/adminDashboard.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

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
          <img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;">
          <div class ="dropdown">
            <p class="d" style="font-size: 12px;">Admin</p>
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
 
	<%-- <div class="welcome-container">
		<h1>Hello <%=cookieUsername %>. Welcome to Admin Dashboard page!</h1>
		<h3>Cookie session Id is <%=cookieSessionID %></h3>
		<p>Session username: <%=userSession %></p>
		<a href="${pageContext.request.contextPath}/index.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
		
	</div> --%>
	

	
	<div class="container">
      <h1 style="padding-bottom: 20px;">ADMIN DASHBOARD</h1>
	    <div class = "label">
	      <h2>Products</h2>
	      <a href="./pages/addProduct.jsp">
	      	<button class="add-btn"><b>Add Product</b></button>
	      </a>	
	    </div>
	    
      <table class="product-table">
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Description</th>
          <th>Category</th>
          <th>Stock</th>
          <th>Price</th>
          <th>Image</th>
          <th>Actions</th>
        </tr>
        
        <c:forEach var="product" items="${productsList}"> 
	        <tr>
	          <td>${product.productID}</td>
	          <td>${product.productName}</td>
	          <td>${product.productDescription}</td>
	          <td>${product.productCategory}</td>
	          <td>${product.stock}</td>
	          <td>Rs. ${product.unitPrice}</td>
	          <td>
	          	<img src="${pageContext.request.contextPath}/resources/images/products/${product.productImageUrlFromPart}" style="width:225px; height:225px"/>
	          </td>
	          <td>
		          <form id="updateForm-${product.productName}" method="get" action="<%=contextPath + StringUtils.SERVLET_URL_UPDATE%>">
	                 <input type="hidden" name="<%=StringUtils.UPDATE_ID %>" value="${product.productID}" />
	                 <button type="submit">Update</button>
	              </form>
					<form id="deleteForm-${product.productName}" method="post" 
	                    action="${pageContext.request.contextPath}/ModifyServlet">
	                    <input type="hidden" name="<%=StringUtils.DELETE_ID %>" value="${product.productName}" />
	                    <button type="button"
	                        onclick="confirmDelete('${product.productName}')">Delete</button>
	                </form>
				</td>
	        </tr>
        </c:forEach>
	        
      </table>
      
      <h2 style="padding-top:50px">Orders</h2>
      
      <table class="order-table">
        <tr>
          <th>ID</th>
          <th>Customer</th>
          <th>Address</th>
          <th>Product</th>
          <th>Quantity</th>
          <th>Grand Total</th>
        </tr>
        <tr>
          <td>1</td>
          <td>Ram Mandal</td>
          <td>Baneshwor</td>
          <td>Airpods<br>Monitor</td>
          <td>2<br>3</td>
          <td>$1998</td>
        </tr>
        <tr>
          <td>1</td>
          <td>binu</td>
          <td>Nardevi</td>
          <td>Monitor</td>
          <td>1</td>
          <td>$999</td>
        </tr>
        <tr>
          <td>1</td>
          <td>Hari</td>
          <td>Maitighar</td>
          <td>Keyboard</td>
          <td>1</td>
          <td>$999</td>
        </tr>
        <tr>
          <td>1</td>
          <td>Paremsh</td>
          <td>Jhamsikhel</td>
          <td>Mouse</td>
          <td>1</td>
          <td>$999</td>
        </tr>
      </table>
    </div>
	
</body>

<script>
    function confirmDelete(productName) {
        if (confirm("Are you sure you want to delete this product: " + productName
                + "?")) {
            document.getElementById("deleteForm-" + productName).submit();
        }
    }
</script>

</html>