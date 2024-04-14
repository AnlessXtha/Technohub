<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TechnoHub</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/login.css" />
</head>
<body>
<div class = "container">

    <div class="imgbox">
      <img src="../resources/images/tempimg.jpg">
    </div>
    <div class = "logbox">
      <div class = "heading">
        <p><h1>Login</h1>
          Welcome Back!</p>
      </div>
    
    <div class = "form">
      <form action="../LoginServlet" method='post'>
        
        <div class = "inputform">
          <input id="username" name="username" type="text" placeholder="username" />
        </div>
        <div class = "inputform">
          <input id="password" name="password" type="password" placeholder="password" />
        </div>

        <p style="margin-left: auto;">Don't have an account? <a href="register.html">Register Now!</a></p>

        <button type="submit" class="login-button">Login</button>

      </form>
    </div>

  </div>
</body>
</html>