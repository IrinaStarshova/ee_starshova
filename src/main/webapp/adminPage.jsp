<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h1>Flower shop</h1>
        <h1>Admin page</h1>
        <form method=post action=loginForm>
            <input type=submit value="Log out"/>
        </form>
        <h2>Orders</h2>
			<table border="1">
				<tr align="center">
				<td>Order id</td>
				<td>Order cost</td>
				<td>Creation date</td>
				<td>Closing date</td>
				<td>Order status</td>
				</tr>
                <c:forEach items = "${orders.getOrders()}" var="iterator">
					<tr>
					<form method=post action =closeServlet>
					<td><input type="text" name="orderId" value="${iterator.id}"
                        size="3" readonly/></td>
					<td align="center">${iterator.cost}</td>
					<td align="center">${iterator.creationDate}</td>
					<td align="center">${iterator.closingDate}</td>
					<td align="center">${iterator.status}</td>
					<c:if test = "${iterator.status == 'paid'}">
					    <td align="center"><input type=submit value="Close"/></td>
                    </c:if>
					</form>
					</tr>
                    <tr><td colspan="5"><details><summary>Order details</summary>
                    <table border="1">
                        <tr align="center">
                        <td>Flower name</td>
                        <td>Quantity</td>
                        <td>Total price</td>
                        </tr>
                    <c:forEach items = "${cart.getCartByOrderId(iterator.id)}" var="iterator">
                    	<tr>
                        <td>${iterator.flowerName}</td>
                        <td>${iterator.quantity}</td>
                        <td>${iterator.totalPrice}</td>
                        </tr>
                    </c:forEach>
                    </table>
                    </details></td></tr>
				</c:forEach>
			</table>
    </body>
<html>