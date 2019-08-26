<html>
    <body>
        <h2>Welcome! Please log in!</h2>
        <form method=post action =loginServlet>
            <p><input type="text" placeholder="username" name="username" required/></p>
            <p><input type="password" placeholder="password" name="password" required/></p>
            <input type=submit  value="Log in"/>
            <% Object message = request.getAttribute("message");
                if(message!=null) {
                out.println("<p style=\"color:#ff0000\">" + message + "</p>");
            } %>
        </form>
         <form method=post action=registerForm>
            <br>Do not have an account yet?
            <input type=submit value="Create account"/>
         </form>
    </body>
<html>