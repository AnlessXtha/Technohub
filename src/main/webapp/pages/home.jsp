<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/home.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<title>Customer Home</title>
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
          <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_HOME_LIST_PAGE}" class="navLinks">Home</a>
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
	
	 
	 <section  class="home" style = "background:url(${pageContext.request.contextPath}/resources/images/razerviper.jpg) no-repeat; background-size: cover; background-position: center;">
      <div class="content" style="max-width: 800px; padding: 30px;">

          <span style= "font-size: 32px; padding:16px 0; line-height: 1; color:#FFFFFF;">NEW R A Z E R VIPER V3</span>

          <p style="font-size: 24px; color:#FFFFFF; padding:16px 0; line-height: 1.5;">
            For Gamers. By Gamers.
          </p>

          <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_PRODUCTLISTCUSTOMER}" button class="btn-1" style=" color:#FFFFFF; text-decoration: underline;">
            Shop Now
          </a>

      </div>

    </section>
    <!--home section ends-->





    <!-- icons section starts  -->
    <section class="feat">
        <!-- delivery icon box code -->
        <div class="icons">
            <img src="${pageContext.request.contextPath}/resources/images/delivery.png" alt="Delivery Truck Image">
            <div class="info">
                <h3>No Delivery Charges</h3>
                <span>Fast and Reliable</span>
            </div>
        </div>
        <!-- payment icon box code -->
        <div class="icons">
            <img src="${pageContext.request.contextPath}/resources/images/securepayment.png" alt="Mobile Pay Image">
            <div class="info">
                <h3>Secure Payment</h3>
                <span>Protected by e-sewa / Khalti</span>
            </div>
        </div>
    
    </section>
    <!-- icons section ends -->




    <section class="products" id="products">
      <!--Latest Product Heading-->
      <h1 class="heading">Our Products</h1>
	
      <div class="box-container">
		<c:forEach var="product" items="${productsListCustomer}">
        <!--1st Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="${pageContext.request.contextPath}/resources/images/products/${product.productImageUrlFromPart}" style="width:225px; height:225px"/></a>
          <h3>${product.productName}</h3>
          <p>${product.productDescription}</p>
          <div class="price">Rs. ${product.unitPrice}</div>
          <form  method="get" action="<%=contextPath + StringUtils.SERVLET_URL_SINGLE_PRODUCT%>">
	                 <input type="hidden" name="<%=StringUtils.UPDATE_ID %>" value="${product.productID}" />
	                 <button type="submit">Learn More </button>
	              </form>
          <div class="description">
          </div>
		</div>
		
        </c:forEach>
      </div>
    </section>





    <!-- footer section starts -->
    <footer class = "footer">
      <!-- division class seperated for logo -->
      <div class="column1">
        <img src = "${pageContext.request.contextPath}/resources/images/technohublogo.png" style = "height: 50px;">
        <br><br>
        <span class="copyright"> &copy; 2023 TECHNOHUB </span>
      </div>
      <!-- newsletter code footer -->
      <div class="column2">
        <h3>NEWSLETTER</h3>
        <form class="newsletter">
          <input type = "email" placeholder="Your Email Address" required>
          <br>
          <button type = "submit">Subscribe</button>
        </form> 
      </div>
      <!-- contact us code for footer -->
      <div class="column3">
        <a href = "pages/contact.html" style="text-decoration: none; color:#D1EFEF"><h3>CONTACT US</h3></a>
        <p>cs.tech@technohub.com<br> Basundhara, Kathmandu, NP</p>
        <br>
        <div class = "social">
          <h3>FOLLOW US</h3>
          <i class ="facebook-icon"><img src="${pageContext.request.contextPath}/resources/images/facebook.png" alt="facebook" style="width: 40px;"></i></a>
          <i class ="instagram-icon"><img src="${pageContext.request.contextPath}/resources/images/instagram.png" alt="instagram" style="width: 40px;"></i></a>
          <i class ="twitter-icon"><img src="${pageContext.request.contextPath}/resources/images/twitter.png" alt="twitter" style="width: 40px;"></i></a>
        </div>
      </div>
    </footer>
	
</body>
</html>