<%@page import = "util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<nav>
      <div class="navbar">
        <div class="navLogo">
          <a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/navigation/technohublogo.png" width="100px;"></a>
        </div>

        <div class="navLinksContainer"> 
          <a href="${pageContext.request.contextPath}/${StringUtils.SERVLET_URL_PRODUCTLIST}" class="navLinks">Dashboard</a>
          <a href="#" class="navLinks">Products</a>
          <a href="#" class="navLinks">Contact Us</a>
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

<!-- <h1>Add Product</h1>

<div class = "form">
      <form action="/TechnoHub/AddProductServlet" method="post" enctype="multipart/form-data">
        
        <div style="display: flex;">
          <div class = "inputform">
            <input id="productName" name="productName" type="text" placeholder="Product Name" required />
          </div>
          <div class = "inputform">
            <input id="productDescription" name="productDescription" type="text" placeholder="Product Description" required />
          </div>
        </div>

        <div style = "display: flex;">
          <div class = "inputform">
            <input id="productCategory" name="productCategory" type="text" placeholder="Product Category" required  />
          </div>

        </div>

        <div style="display: flex;">
          <div class = "inputform">
            <input id="stock" name="stock" type="number" placeholder="stock" required  />
          </div>
          <div class = "inputform">
            <input id="unitPrice" name="unitPrice" type="number" placeholder="unitPrice" required  />
          </div>
        </div>

        <div style="display: flex;">
          <div class = "inputform">
           <label for="image">Product Image</label> 
           <input type="file" id="productImage" name="productImage" accept="image/png, image/jpeg">
          </div>
        </div>

        <button class="register-button" type="submit">Add</button>

      </form>
    </div> -->
    
    <div class="container">
       <h1 >Add Product</h1>
       <form  action="/TechnoHub/AddProductServlet" method="post" enctype="multipart/form-data">
           <div class="form-group">
               <label for="productName">Product Name:</label>
               <input type="text" id="productName" name="productName" required>
           </div>
           <div class="form-group">
               <label for="productDescription">Product Description:</label>
               <input type="text" id="productDescription" name="productDescription">
           </div>
           <div class="form-group">
               <label for="productCategory">Category:</label>
               <select id="productCategory" name="productCategory" required>
				   <option value="" selected disabled>-- Select --</option> 
                   <option value="Mouse">Mouse</option>
                   <option value="Keyboard">Keyboard</option>
                   <option value="HeadPhone">HeadPhone</option>
                   <option value="Microphone">Microphone</option>
               </select>
           </div>
           <div class="wrapper">
               <div class="form-group">
                   <label for="stock">Stock:</label>
                   <input type="number" id="stock" name="stock" min="0" required>
               </div>
               <div class="form-group">
                   <label for="unitPrice">Unit Price:</label>
                   <input type="number" id="unitPrice" name="unitPrice" required>
               </div>
           </div>
           <div class="form-group">
               <label for="productImage">Product Image:</label>
               <input type="file" id="productImage" name="productImage" accept="image/*" required>
           </div>
           <div class="form-btn">
	           <a href="${pageContext.request.contextPath}/${StringUtils.SERVLET_URL_PRODUCTLIST}">
	               <button class="btn" type="button">Cancel </button>
	           </a>
               <button class="btn" type="submit">Add Product </button>
           </div>

       </form>
	</div>

</body>
</html>