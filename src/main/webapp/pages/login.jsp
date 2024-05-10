<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TechnoHub</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/login.css" />
</head>
<body>
	<div class="container">

		<div class="imgbox">
			<img
				src="${pageContext.request.contextPath}/resources/images/tempimg.jpg">
		</div>
		<div class="logbox">
			<div class="heading">
				<p>
				<h1>Login</h1>
				Welcome Back!
				</p>
			</div>

			<div class="form">
				<form action="../LoginServlet" method='post'>

					<div class="inputform">
						<input id="username" name="username" type="text"
							placeholder="username" />
					</div>
					<div class="inputform">
						<input id="password" name="password" type="password"
							placeholder="password" />
					</div>

					<p style="margin-left: auto;">
						Don't have an account? <a
							href="${pageContext.request.contextPath}${StringUtils.REGISTER_PAGE}">Register Now!</a>
					</p>

					<button type="submit" class="login-button">Login</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>