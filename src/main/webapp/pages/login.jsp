<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class = "container">

    <div class = "heading">
      <h1>Login</h1>
    </div>
    
    <div class = "forum">
      <form action="../LoginServlet">
        
        <div class = "inputform">
          <input id="username" name="username" type="text" placeholder="username" />
        </div>
        <div class = "inputform">
          <input id="password" name="password" type="password" placeholder="password" />
        </div>

        <p style="margin-left: auto;">Do not have an account? <a href="register.html">Register Now!</a></p>

        <button type="submit" class="login-button">Login</button>

      </form>
    </div>

  </div>
</body>
</html>