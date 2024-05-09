<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/products.css" />
<title>Products</title>
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

	<nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
          <a href="${pageContext.request.contextPath}/pages/home.jsp" class="navLinks">Home</a>
          <a href="./pages/products.jsp" class="navLinks">Products</a>
          <a href="#" class="navLinks">Contact Us</a>
        </div>


        <div class="topRight">
        	<div class ="dropdown" style="display:flex; align-items: center">
	          	<img src="${pageContext.request.contextPath}/resources/images/navigation/user.png" alt="Admin" style="width: 37px;"></i></a>
	          
	            <p class="d" style="font-size: 12px;"><%=userSession%></p></a>
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
	
    <section class="products" id="products">
      <!--Latest Product Heading-->
      <h1 class="heading">Recently Added</h1>

      <div class="box-container">

        <!--1st Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg" ></a>
          <h3>Boxwood</h3>
          <p>Boxwood prefer shady areas, out of the hot afternoon sun.</p>
          <div class="price">$27.99 <span style="text-decoration: line-through;"> $34.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> BUXUS </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--2nd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Azalea</h3>
          <p>Azaleas prefer outdoor conditions in areas with semi-shade.</p>
          <div class="price">$42.99 <span style="text-decoration: line-through;"> $49.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> FICUS RETUSA OR GINSENG </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--3rd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>

        <!--4th Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>
    </section>


    <section class="products" id="products">
      <!--Latest Product Heading-->
      <h1 class="heading">Popular Items</h1>

      <div class="box-container">

        <!--1st Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg" ></a>
          <h3>Boxwood</h3>
          <p>Boxwood prefer shady areas, out of the hot afternoon sun.</p>
          <div class="price">$27.99 <span style="text-decoration: line-through;"> $34.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> BUXUS </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--2nd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Azalea</h3>
          <p>Azaleas prefer outdoor conditions in areas with semi-shade.</p>
          <div class="price">$42.99 <span style="text-decoration: line-through;"> $49.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> FICUS RETUSA OR GINSENG </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--3rd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>

        <!--4th Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>
    </section>




    <section class="products" id="products">
      <!--Latest Product Heading-->
      <h1 class="heading">For You</h1>

      <div class="box-container">

        <!--1st Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg" ></a>
          <h3>Boxwood</h3>
          <p>Boxwood prefer shady areas, out of the hot afternoon sun.</p>
          <div class="price">$27.99 <span style="text-decoration: line-through;"> $34.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> BUXUS </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--2nd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Azalea</h3>
          <p>Azaleas prefer outdoor conditions in areas with semi-shade.</p>
          <div class="price">$42.99 <span style="text-decoration: line-through;"> $49.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> FICUS RETUSA OR GINSENG </span>
            <span> Broadleaf </span>
          </div>
        </div>

        <!--3rd Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>

        <!--4th Product-->
        <div class="col-3">
          <a href="pages/learnmore.html"><img src="images/Cherry01-Sakura-Japan.jpg"></a>
          <h3>Cherry</h3>
          <p>Cherry prefer the cool temperatures of winter and the warmth of spring.</p>
          <div class="price">$55.99 <span style="text-decoration: line-through;"> $69.99</span></div>
          <a href="pages/learnmore.html"><button class="btn">Learn More</button></a>
          <div class="description">
            <span> PRUNUS, SAKURA</span>
            <span> Deciduous </span>
          </div>
        </div>
    </section>



    <!-- footer section starts -->
    <!-- <footer class = "footer">
      division class seperated for logo
      <div class="column1">
        <img src = "../images/technohublogo.png" style = "height: 50px;">
        <br><br>
        <span class="copyright"> &copy; 2023 TECHNOHUB </span>
      </div>
      newsletter code footer
      <div class="column2">
        <h3>NEWSLETTER</h3>
        <form class="newsletter">
          <input type = "email" placeholder="Your Email Address" required>
          <br>
          <button type = "submit">Subscribe</button>
        </form> 
      </div>
      contact us code for footer
      <div class="column3">
        <a href = "../pages/contact.html" style="text-decoration: none; color:#D1EFEF"><h3>CONTACT US</h3></a>
        <p>cs.tech@technohub.com<br> Basundhara, Kathmandu, NP</p>
        <br>
        <div class = "social">
          <h3>FOLLOW US</h3>
          <i class ="facebook-icon"><img src="../images/facebook.png" alt="facebook" style="width: 40px;"></i></a>
          <i class ="instagram-icon"><img src="../images/instagram.png" alt="instagram" style="width: 40px;"></i></a>
          <i class ="twitter-icon"><img src="../images/twitter.png" alt="twitter" style="width: 40px;"></i></a>
        </div>
      </div>
    </footer>
 -->



  </body>
</html>