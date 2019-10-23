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
	<script src="http://code.jquery.com/jquery-2.2.4.js"
                    type="text/javascript"></script>
  </head>
    <body>
    <div class="block" align="center">
        <h2>Welcome! <br>Please create new account!</h2>

        <form method=post action =registerServlet>

            <p><input id="username" type="text" placeholder="username"
                name="username" autocomplete="off" required/></p>
            <div id ="verificationMessage" style="color:#ff0000"></div>
            <p><input id ="password" type="password" placeholder="password" name="password" required/></p>
            <p><input type="text" placeholder="first name" name="firstName"/></p>
            <p><input type="text" placeholder="patronymic" name="patronymic"/></p>
            <p><input type="text" placeholder="last name" name="lastName"/></p>
            <p><input type="text" placeholder="address" name="address"/></p>
            <p><input type="text" placeholder="phone number" name="phoneNumber"/></p>
            <p id ="registrationMessage" style="color:#ff0000">${registrationMessage}</p>
            <input id="create" type=submit  value="Create account" />
        </form>
        <form method=post action=loginForm>
            <br>Already have an account?
            <input type=submit value="Log in"/>
        </form>
        </div>
        <script>
            $("#username").on('keyup',function() {
                $("#verificationMessage").text( "" );
                $("#registrationMessage").text( "" );
                $("#create").attr("disabled", false);
                if(this.value) {
                    $.ajax({
                        url: "http://localhost:8080/flowershop/rest/verifyLogin/" + this.value,
                        success: function(data) {
                            if(data == "true"){
                                $("#create").attr("disabled", true);
                                $("#verificationMessage").text("Inputted login already exists!");
                            }
                        },
                        error: function(data){
                            $("#verificationMessage").text("Unknown error!");
                        }
                    });
                }
            });
        </script>
    </body>
<html>