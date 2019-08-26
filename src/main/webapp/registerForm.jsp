<html>
    <body>
        <h2>Welcome! Please create new account!</h2>

        <form method=post action =registerServlet>
            <p><input type="text" placeholder="username" name="username" required/></p>
            <p><input type="password" placeholder="password" name="password" required/></p>
            <input type=submit  value="Create account"/>
            <%  Object message = request.getAttribute("message");
                if(message!=null) {
                out.println("<p style=\"color:#ff0000\">" + message + "</p>");
            }%>
        </form>
        <form method=post action=loginForm>
            <br>Already have an account?
            <input type=submit value="Log in"/>
        </form>
    </body>
<html>