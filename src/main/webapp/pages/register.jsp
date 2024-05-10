<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TechnoHub</title>
<link rel="stylesheet" type="text/css"
	href="/TechnoHub/stylesheets/register.css" />
</head>
<body>
	<div class="container">

		<div class="imgbox">
			<img src="${pageContext.request.contextPath}/resources/images/tempimg.jpg">
		</div>

		<div class="regbox">
			
				<%-- Display error message if it exists --%>
				<%
				String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
				if (errorMessage != null && !errorMessage.isEmpty()) {
				%>
				<p class="error-message" style="color:red;">
					<%=errorMessage%>
				</p>
				<%
				}
				%>
			<div class="heading">
				<h1>Register</h1>
				<p>Please complete to create your account.</p>
			</div>


			<div class="form">
			<form action="/TechnoHub/RegisterServlet" method="post">

					<div style="display: flex;">
						<div class="inputform">
							<input id="firstName" name="firstName" type="text"
								placeholder="First Name" required />
						</div>
						<div class="inputform">
							<input id="lastName" name="lastName" type="text"
								placeholder="Last Name" required />
						</div>
					</div>

					<div style="display: flex;">
						<div class="inputform">
							<input id="name" name="email" type="email" placeholder="E-mail"
								required />
						</div>
						<div class="inputform">
							<input id="username" name="username" type="text"
								placeholder="Username" required />
						</div>
					</div>

					<div style="display: flex;">
						<div class="inputform">
							<input id="contactNumber" name="contactNumber" type="tel"
								placeholder="Phone Number" required />
						</div>
						<div class="inputform">
							<input id="address" name="address" type="text"
								placeholder="Address" required />
						</div>
					</div>

					<div style="display: flex;">
						<div class="inputform">
							<input id="password" name="password" type="password"
								placeholder="Password" required />
						</div>
						<div class="inputform">
							<input id="retypePassword" name="retypePassword" type="password"
								placeholder="Retype Password" required />
						</div>
					</div>
			</div>

			<p>
				Already have an account? <a href="${pageContext.request.contextPath}${StringUtils.LOGIN_PAGE}">Login</a>
			</p>

			<button class="register-button" type="submit">Register</button>

			</form>
		</div>

	</div>
</body>
</html>