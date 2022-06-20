<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Add Delivery Boy</title>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	
 	
 	<div class="container">
 	
 	<div class="page-title"><h3>Add Delivery Boy</h3></div>
 	<br>
 	<h4 style="color:red">${message} </h4>
 	
    <form:form method="POST" modelAttribute="delivery" action="addDelivery">
 
       <table>
       		<tr>
               <td><h5>Delivery Boy Id *</h5></td>
               <td><form:input path="DId" /></td>
               <td><form:errors path="DId" class="error-message" /></td>
           </tr>
           <tr>
               <td><h5>First Name *</h5></td>
               <td><form:input path="fName" /></td>
               <td><form:errors path="fName" class="error-message" /></td>
           </tr>
 
 			<tr>
               <td><h5>Last Name *</h5></td>
               <td><form:input path="lName" /></td>
              	<td><form:errors path="lName" class="error-message" /></td>
           </tr>
 			
           <tr>
               <td><h5>Email Id *</h5></td>
               <td><form:input path="emailId" /></td>
               <td><form:errors path="emailId" class="error-message" /></td>
           </tr>
 
           <tr>
               <td><h5>Phone *</h5></td>
               <td><form:input path="phone" maxlength="10"/></td>
               <td><form:errors path="phone" class="error-message" /></td>
           </tr>
           
           <tr>
               <td><h5>Vehicle Number *</h5></td>
               <td><form:input path="vehicleNo"/></td>
               <td><form:errors path="vehicleNo" class="error-message" /></td>
           </tr>
           
           <tr>
               <td><h5>Vehicle Name *</h5></td>
               <td><form:input path="vehicleName"/></td>
               <td><form:errors path="vehicleName" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>&nbsp;</td>
               <td><input class="btn btn-default" type="submit" value="Submit" /> 
               	<input type="reset" class="btn btn-default" value="Reset" /></td>
           </tr>
       </table> 
 	
   </form:form>
   </div>
   <jsp:include page="footer.jsp"/>
 
</body>
</html>