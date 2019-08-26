<html>
    <body>
        <form method=post action=loginForm>
            <h2>Welcome, <%out.println(request.getParameter("username"));%>!</h2>
            <input type=submit value="Log out"/>
        </form>
    </body>
 <html>