<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Welcome! Please log in!</h2>
        <form method=post action =loginServlet>
            <p><input type="text" placeholder="username" name="username" required/></p>
            <p><input type="password" placeholder="password" name="password" required/></p>
            <input type=submit  value="Log in"/>
            <c:if test = "${message != '0'}">
                <p style="color:#ff0000">${message}</p>
            </c:if>
        </form>
         <form method=post action=registerForm>
            <br>Do not have an account yet?
            <input type=submit value="Create account"/>
         </form>
    </body>
<html>