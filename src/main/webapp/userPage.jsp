<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<body>
		<h1>Flower shop</h1>
		<div>
			<form method=post action=logoutServlet>
			<h2>Welcome, ${user.login}!</h2>
			<p>Your balance: ${user.balance} rub</p>
			<p>Your discount: ${user.discount} %</p>
			<input type=submit value="Log out"/>
			</form>
		</div>

		<div>
			<h2>Flowers catalog</h2>
			<table border="1">
				<tr align="center">
				<td style="display: none">Id</td>
				<td>Flower name</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Add to cart</td>
				</tr>

                <c:forEach items = "${flowers.getFlowers()}" var="iterator">
					<form method=post action =cartServlet>
					<tr>
					<td style="display: none">
						<input type="text" name="flowerID"
						value="${iterator.id}" readonly/>
					</td>
					<td>${iterator.name}</td>
					<td align="center">${iterator.price}</td>
					<td align="center">${iterator.quantity-iterator.quantityInCart}</td>
					<td align="center"><input type="text" pattern="[1-9]+[0-9]{0,3}"
					    maxlength="4" size="1"  placeholder="qty" name="quantity" required/>
					    <input type=submit value="Add to cart"/>
					</td>
					</tr>
					</form>
			   </c:forEach>
			</table>
            <p style="color:#ff0000">${cartMessage}</p>
		</div>

		<div>
			<h2>Cart</h2>
			<form method=post action =orderServlet>
			<table border="1">
				<tr align="center">
				<td>Flower name</td>
				<td>Quantity</td>
				<td>Total price</td>
				</tr>

				<c:forEach items = "${cart.getCart(user.login)}" var="iterator">
				    <tr>
				    <td>${iterator.flowerName}</td>
				    <td>${iterator.quantity}</td>
				    <td>${iterator.totalPrice}</td>
				    </tr>
                </c:forEach>
                <tr><td colspan="3">Total cost: ${cartCost}</td></tr>

			</table>
            <p style="color:#ff0000">${orderMessage}</p>
			<p><input type=submit value="Create order"/></p>
			</form>
		</div>

		<div>
			<h2>Orders</h2>
            <p style="color:#ff0000">${payMessage}</p>
			<table border="1">
				<tr align="center">
				<td>Order id</td>
				<td>Order cost</td>
				<td>Creation date</td>
				<td>Closing date</td>
				<td>Order status</td>
				</tr>
                <c:forEach items = "${orders.getOrders(user.login)}" var="iterator">
                    <form method=post action =payServlet>
					<tr>
					<td><input type="text" name="orderId" value="${iterator.id}"
					    size="3" readonly/></td>
					<td align="center">${iterator.cost}</td>
					<td align="center">${iterator.creationDate}</td>
					<td align="center">${iterator.closingDate}</td>
					<td align="center">${iterator.status}</td>
					<c:if test = "${iterator.status == 'created'}">
					    <td align="center"><input type=submit value="Pay"/></td>
                    </c:if>
					</tr>
					</form>
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

		</div>
	</body>
<html>