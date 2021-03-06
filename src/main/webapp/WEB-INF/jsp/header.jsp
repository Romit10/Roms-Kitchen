<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
	
    <%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/carouFredSel.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/easing.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script> --%>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/button.css"/>">
 --%></head>
<body>

<header>
		<div class="wrapper">
			<div class="logo">
				<a href="index"><img src="<c:url value="/resources/img/logo.png"/>" width="165" height="90" alt="Taj Restaurant" title=""/></a>
			</div>

			<nav>
				<ul>
					<!--<li><a href="" class="active">Home</a></li>-->
					<!-- <li><a href="">Our Story</a></li> -->
					<!-- <li><a href="index">Menu</a></li> -->
					<!-- <li><a href="">Reservations</a></li>
					<li><a href="">News</a></li>
					<li><a href="">Reviews</a></li> -->
					<c:choose>
					  <c:when test="${empty user}">
					   	<li><a href="register">Register</a></li>
					   	<li><a href="login">Login</a></li>
					  </c:when>
					  <c:when test="${not empty user}">
					    <li>Welcome .. ${user.getfName()}</li>
					    <li><a href="logout">Logout</a></li>
					   	<c:if test="${user.getfName()=='admin'}">
					   	<li><a href="addProduct">Add Product</a></li>
					   	<li><a href="addDelivery">Add Delivery Boy</a></li>
					   	<li><a href="allOrders">All Orders</a></li>
					   	</c:if>
					   	<c:if test="${user.getfName()!='admin'}">
					   	<li><a href="orderDetail">My Orders</a></li>
					   	</c:if>
					  </c:when>
					  </c:choose>
					<li><a href="index" >Home</a></li> 
					<li><a href="shoppingCart">My Cart</a></li>
				</ul>
			</nav>
		</div>
	</header><!--  end header  -->



<!-- <a href="" >Home</a> <a href="shoppingCart">My Cart</a> -->


</body>
</html>