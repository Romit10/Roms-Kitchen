<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Order Confirmation</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
	<h2>List of Orders</h2>
	
	<h3><a href="orders?v=today">Click</a> to see Orders from Today</h3>
	<c:choose>
		<c:when test="${not empty orders}">
			<table>
				<thead>
					<tr>
						<td>Order Number</td>
						<td>First Name</td>
						<td>Last Name</td>
						<td>Time</td>
						<td>Price</td>
						<td>Status</td>
						<td>Confirm Order</td>
						<td>Reject Order</td>
						<td>Delivery Boy</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o"  items="${orders}">
					<tr>
						<td><a href="getOrder?o=${o.orderId}">${o.orderId}</a> </td>
						<td>${o.userId.fName}</td>
						<td>${o.userId.lName}</td>
						<td>${o.date}</td>
						<td>${o.price}</td>
						<td>${o.status}</td>
						<td>
						<a href="confirmOrder?v=${o.orderId}" style="color:green">Confirm It</a>
						</td>
						<td>
						<a href="rejectOrder?v=${o.orderId}" style="color:red">Reject It</a>
						</td>
						<td>
						<c:choose>
						<c:when test="${not empty o.DId}">
						Assigned Delivery Boy : ${o.DId.fName}  <a href="unassignD?v=${o.orderId}"> Unassign </a>
						</c:when>
						<c:when test="${empty o.DId}">
						<form method="POST" action="assignD?v=${o.orderId}">
						Enter DId : <input type="text" name="DId"/> <input type="submit" value="Submit" /> 
						</form>
						</c:when>
						</c:choose>
						</td>
					</tr>
					</c:forEach>			
				</tbody>
			
			</table>
				
			
		</c:when>
		<c:otherwise>
		
			<p>Bad Input</p>
		</c:otherwise>
		
	</c:choose>
	</div>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>
