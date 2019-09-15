<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Flower shop</title>
	<style type="text/css">
		.block {
		margin: 0 auto;
		padding: 10px;
        background: #d2d6df;
        width: 350px;
        align: center;
	   }
	</style>
  </head>
    <body>
    <div class="block" align="center">
        <h2>Welcome! <br>Please create new account!</h2>

        <form method=post action =registerServlet>
            <p><input type="text" placeholder="username" name="username" required/></p>
            <p><input type="password" placeholder="password" name="password" required/></p>
            <p><input type="text" placeholder="first name" name="firstName" required/></p>
            <p><input type="text" placeholder="patronymic" name="patronymic" required/></p>
            <p><input type="text" placeholder="last name" name="lastName" required/></p>
            <p><input type="text" placeholder="address" name="address" required/></p>
            <p><input type="text" placeholder="phone number" name="phoneNumber" required/></p>
            <input type=submit  value="Create account"/>
            <c:if test = "${message != '0'}">
                <p style="color:#ff0000">${message}</p>
            </c:if>
        </form>
        <form method=post action=loginForm>
            <br>Already have an account?
            <input type=submit value="Log in"/>
        </form>
    </div>
    </body>
<html>