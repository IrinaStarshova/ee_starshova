<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
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
        <h2>Welcome! Please log in!</h2>
        <form method=post action =loginServlet>
            <p><input type="text" placeholder="username" name="username" required/></p>
            <p><input type="password" placeholder="password" name="password" required/></p>
            <input type=submit  value="Log in"/>
            <p style="color:#ff0000">${message}</p>
        </form>
         <form method=post action=registerForm>
            <br>Do not have an account yet?
            <input type=submit value="Create account"/>
         </form>

    </div>
    </body>
<html>