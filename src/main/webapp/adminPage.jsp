<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Flower shop</title>
	<style type="text/css">
		.block {
		margin: 0 auto;
        width: 800px;
	   }
	   .block1 {
		padding: 10px;
        background: #d2d6df;
	   }
	    table {
           border-collapse: collapse;
        }
        td {
            width: 120px;
            padding: 3px;
        }
	</style>
  </head>
    <body>
    <div class="block">
        <div class="block1">
        <h2 align="center">Flower shop. Admin page</h2>
        </div>
        <form method=post action=loginForm>
            <p align="right"><input type=submit value="Log out"/></p>
        </form>
        <div align="center">
        <h2>Orders</h2>
			<table border="1">
				<tr bgcolor="#d2d6df" align="center">
				<td>Order id</td>
				<td>Order cost</td>
				<td>Creation date</td>
				<td>Closing date</td>
				<td>Order status</td>
				</tr>
                <c:forEach items = "${orders}" var="iterator">
					<form method=post action =closeServlet>
					<input type="hidden" name="orderId" value="${iterator.id}"/>
					<tr>
					<td align="center">${iterator.id}</td>
					<td align="center">${iterator.cost}</td>
					<td align="center">${iterator.creationDate}</td>
					<td align="center">${iterator.closingDate}</td>
					<td align="center">${iterator.status}</td>
					<c:if test = "${iterator.status == 'paid'}">
					    <td style="width:30px;" align="center"><input type=submit value="Close"/></td>
                    </c:if>
					</form>
					</tr>
                    <tr><td colspan="5"><details><summary>Order details</summary>
                    <table>
                    <c:forEach items = "${iterator.carts}" var="iterator">
                    	<tr>
                        <td style="width:auto;">${iterator.flowerName} - </td>
                        <td style="width:auto;">${iterator.quantity} pc. - </td>
                        <td style="width:auto;">${iterator.totalPrice}</td>
                        </tr>
                    </c:forEach>
                    </table>
                    </details></td></tr>
				</c:forEach>
			</table>
		</div>
	</div>
    </body>
<html>