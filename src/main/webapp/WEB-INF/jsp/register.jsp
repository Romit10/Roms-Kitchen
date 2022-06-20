<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Register</title>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	
 	
 	<div class="container">
 	
 	<div class="page-title"><h3>Register Yourself!</h3></div>
 	<br>
 	<h4 style="color:red">${message} </h4>
 	
    <form:form method="POST" modelAttribute="user1" action="register">
 
       <table>
       		<tr>
               <td><h5>User Id *</h5></td>
               <td><form:input path="userId" /></td>
               <td><form:errors path="userId" class="error-message" /></td>
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
               <td><h5>Email *</h5></td>
               <td><form:input path="email" /></td>
               <td><form:errors path="email" class="error-message" /></td>
           </tr>
 
           <tr>
               <td><h5>phone *</h5></td>
               <td><form:input path="phone" maxlength="10"/></td>
               <td><form:errors path="phone" class="error-message" /></td>
           </tr>
 
           <tr>
               <td><h5>Address *</h5></td>
               <td><form:input path="address" /></td>
               <td><form:errors path="address" class="error-message" /></td>
           </tr>
           
           <tr>
               <td><h5>Password *</h5></td>
               <td><form:input path="password" /></td>
               <td><form:errors path="password" class="error-message" /></td>
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