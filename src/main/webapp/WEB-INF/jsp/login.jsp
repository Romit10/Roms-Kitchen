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
 	
 	<div class="page-title"><h3>Login!</h3></div>
 	<br>
 	<h4 style="color:red">${message} </h4>
 	
    <form:form method="POST" modelAttribute="login" action="login">
 
       <table>
       		<tr>
               <td><h5>User Id *</h5></td>
               <td><form:input path="userId" /></td>
               <td><form:errors path="userId" class="error-message" /></td>
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
