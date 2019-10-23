<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
  <meta charset="utf-8">
  <title>Flower shop</title>
	<style type="text/css">
		.block {
		padding: 10px;
		margin: 0 auto;
        width: 1100px;
	   }
	   .block1 {
	    padding: 10px;
        background: #d2d6df;
	   }
	   .block2 {
	    padding: 10px;
		float: left;
	   }
	   .block3 {
		padding: 10px;
		float: right;
		position: relative;
	   }
	    table {
	       width: 500px;
           border-collapse: collapse;
        }
        td {
            padding: 3px;
        }
	</style>
  </head>
	<body>
		<div class="block">
	    <div class="block1">
		<h2 align="center">Flower shop</h2>
		</div>
		<div class="block2">
			<form method=post action=logoutServlet>
			<h2>Welcome, ${user.login}!</h2>
			<p>Your balance: ${user.balance} rub</p>
			<p>Your discount: ${user.discount} %</p>
			<input type=submit value="Log out"/>
			</form>

		<h2>Flowers catalog</h2>

			    <table border="1">

				<tr bgcolor="#d2d6df" align="center">
				<td>Flower name</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Add to cart</td>
				</tr>

                <c:forEach items = "${flowers}" var="flower">
					<form method=post action =cartServlet>
					<input type="hidden" name="flowerID" value="${flower.id}"/>
					<tr>
					<td>${flower.name}</td>
					<td align="center">${flower.price}</td>
					<td align="center">${flower.quantity}</td>
					<td align="center"><input type="text" pattern="[1-9]+[0-9]{0,3}"
					    maxlength="4" size="1"  placeholder="qty" name="quantity" required/>
					    <input type=submit name="addToCart" value="Add to cart"/>
					</td>
					</tr>
					</form>
			   </c:forEach>
			</table>
            <p style="color:#ff0000">${cartMessage}</p>

			<table border="1" >
			    <form method=post action =searchServlet>
            	<tr><td colspan="2" bgcolor="#d2d6df" ><p align="center">Catalog search</p></td></tr>
				<tr><td>
            	<p style="color:#ff0000">${searchMessage}</p>
				Name of flower contains:
            	<input type="search"  name="nameToSearch" pattern="[A-Za-z]{0,}"/><br><br>
            	Price from:
            	<input type="search" size = "6" name="priceFrom" pattern="[0-9]{0,}"/>
            	 to:
            	<input type="search"  size = "6" name="priceTo" pattern="[0-9]{0,}"/></td>
            	<td><p><input type=submit name="search" value="Search"/></p>
            	<input type=submit name="cancel" value="Cancel"/>
            	</td></tr>
            	</form>
			</table>
		</div>

		<div class="block3">
			<h2>Cart</h2>

			<table style="width:300px;" border="1">
				<tr bgcolor="#d2d6df" align="center">
				<td>Flower name</td>
				<td>Quantity</td>
				<td>Total price</td>
				</tr>

				<c:forEach items = "${user.carts}" var="cart">
				    <tr>
				    <td>${cart.flowerName}</td>
				    <td align="center">${cart.quantity}</td>
				    <td align="center">${cart.totalPrice}</td>
				    </tr>
                </c:forEach>
                <c:if test = "${user.cartCost!='0.00'}">
                    <tr><td style="border:1.5px solid black;" colspan="3">
                    Total cost: ${user.cartCost}</td></tr>
                </c:if>

			</table>
            <p style="color:#ff0000">${orderMessage}</p>

            <form method=post>
            <p><input type=submit <c:if test = "${user.carts.isEmpty()}"> disabled </c:if>
                name="clearCart" formaction="cartServlet" value="Clear cart"/>
            <input type=submit <c:if test = "${user.carts.isEmpty()}"> disabled </c:if>
                name="createOrder" formaction="orderServlet" value="Create order"/></p>
            </form>

			<h2>Orders</h2>
            <p style="color:#ff0000">${payMessage}</p>
			<table border="1">
				<tr bgcolor="#d2d6df" align="center">
				<td>Order id</td>
				<td>Order cost</td>
				<td>Creation date</td>
				<td>Closing date</td>
				<td>Order status</td>
				</tr>
                <c:forEach items = "${user.orders}" var="order">
                    <form method=post>
                    <input type="hidden" name="orderId" value="${order.id}"/>
					<tr>
					<td align="center">${order.id}</td>
					<td align="center">${order.cost}</td>
					<td align="center">${order.creationDate}</td>
					<td align="center">${order.closingDate}</td>
					<td align="center">${order.status.toString()}</td>
                    </tr>

                    <tr><td colspan="5"><details><summary>Order details and available actions</summary>
                    <div>
                    <div style="float: left;">
                    <br>
                    <table style="width:auto;">
                    <tr><td align="center" colspan="3">Details</td></tr>
                    <c:forEach items = "${order.carts}" var="cart">
                    	<tr>
                        <td>${cart.flowerName} - </td>
                        <td>${cart.quantity} pc. - </td>
                        <td>${cart.totalPrice}</td>
                        </tr>
                    </c:forEach>
                    </table>
                    </div>
                    <div style="float: right; position: relative;">

                        <br>
                        <table style="width:auto;">
                        <tr><td align="center">Available <br>actions</td></tr>
                        <c:if test = "${order.status == 'CREATED'}">
                        <tr><td><input type=submit name="payOrder"
							formaction="orderServlet" value="Pay"/></td></tr>
					    <tr><td><input type=submit name="deleteOrder"
					        formaction="orderServlet" value="Cancel"/></td></tr>
					    </c:if>
					    <c:if test = "${order.status == 'PAID'}">
                        <tr><td><input type=submit name="deleteOrder"
                            formaction="orderServlet"value="Cancel"/></td></tr>
                        </c:if>
                        <c:if test = "${order.status == 'CLOSED'}">
                        <tr><td align="center"> - </td></tr>
                        </c:if>
					    </table>
                    </div>
                    </div>
                    </details></td></tr>
					</form>
				</c:forEach>
			</table>
		</div>
		</div>
	</body>
<html>