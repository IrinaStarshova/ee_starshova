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
	    width: 520px;
		padding: 10px;
		float: right;
		position: relative;
	   }
	    table {
	       width: 450px;
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

                <c:forEach items = "${flowers}" var="iterator">
					<form method=post action =cartServlet>
					<input type="hidden" name="flowerID" value="${iterator.id}"/>
					<input type="hidden" name="availableQuantity"
					 value="${iterator.quantity-iterator.quantityInCart}"/>
					<tr>
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
            	<td><p><input type=submit value="Search"/></p>
            	</form>
            	<form method=post action ="searchServlet">
            	    <input type="hidden" name="cancel" value="cancel"/>
            	<input type=submit value="Cancel"/>
            	</td></tr>
            	</form>
			</table>
		</div>

		<div class="block3">
			<h2>Cart</h2>
			<form method=post action =orderServlet>
			<table border="1">
				<tr bgcolor="#d2d6df" align="center">
				<td>Flower name</td>
				<td>Quantity</td>
				<td>Total price</td>
				</tr>

				<c:forEach items = "${cart}" var="iterator">
				    <tr>
				    <td>${iterator.flowerName}</td>
				    <td align="center">${iterator.quantity}</td>
				    <td align="center">${iterator.totalPrice}</td>
				    </tr>
                </c:forEach>
                <c:if test = "${user.cartCost!='0.00'}">
                    <tr><td style="border:1.5px solid black;" colspan="3">
                    Total cost: ${user.cartCost}</td></tr>
                </c:if>

			</table>
            <p style="color:#ff0000">${orderMessage}</p>
			<p><input type=submit value="Create order"/></p>
			</form>

			<h2>Orders</h2>
            <p style="color:#ff0000">${payMessage}</p>
			<table style="width:auto" border="1">
				<tr bgcolor="#d2d6df" align="center">
				<td style="width:80px;">Order id</td>
				<td style="width:80px;">Order cost</td>
				<td style="width:86px;">Creation date</td>
				<td style="width:86px;">Closing date</td>
				<td style="width:86px;">Order status</td>
				</tr>
                <c:forEach items = "${orders}" var="iterator">
                    <form method=post action =payServlet>
                    <input type="hidden" name="orderId" value="${iterator.id}"/>
					<tr>
					<td align="center">${iterator.id}</td>
					<td align="center">${iterator.cost}</td>
					<td align="center">${iterator.creationDate}</td>
					<td align="center">${iterator.closingDate}</td>
					<td align="center">${iterator.status.toString()}</td>
					<c:if test = "${iterator.status == 'CREATED'}">
					    <td style="width:30px;"><input type=submit value="Pay"/></td>
                    </c:if>
                    </tr>
					</form>
                    <tr><td colspan="5"><details><summary>Order details</summary>
                    <table style="width:auto;">
                    <c:forEach items = "${iterator.carts}" var="iterator">
                    	<tr>
                        <td>${iterator.flowerName} - </td>
                        <td>${iterator.quantity} pc. - </td>
                        <td>${iterator.totalPrice}</td>
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