<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Order Placed</title>
</head>
<body>

	<jsp:include page="header.jsp"/>
	<div align="center">
		<h4>Thank you for your Order , ${user.getfName()}</h4>
			
		<h3>Your order id is : ${order.getOrderId()}</h3>
		<h3>Your order status is : ${order.getStatus()}</h3>
		<h3>Your Delivery Boy is : Unassigned</h3>
	
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>