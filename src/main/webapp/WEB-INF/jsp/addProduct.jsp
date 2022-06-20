<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Add Product</title>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	
 	
 	<div class="container">
 	
 	<div class="page-title"><h3>Add Product</h3></div>
 	<br>
 	<h4 style="color:red">${message} </h4>
 	
    <form:form method="POST" modelAttribute="product" action="addProduct">
 
       <table>
       		<tr>
               <td><h5>Product Id *</h5></td>
               <td><form:input path="productCode" /></td>
               <td><form:errors path="productCode" class="error-message" /></td>
           </tr>
           <tr>
               <td><h5>Product Name *</h5></td>
               <td><form:input path="productName" /></td>
               <td><form:errors path="productName" class="error-message" /></td>
           </tr>
 
 			<tr>
               <td><h5>Price *</h5></td>
               <td><form:input path="price" /></td>
              	<td><form:errors path="price" class="error-message" /></td>
           </tr>
 			
           <tr>
               <td><h5>Description *</h5></td>
               <td><form:input path="description" /></td>
               <td><form:errors path="description" class="error-message" /></td>
           </tr>
 
           <tr>
               <td><h5>Category Id *</h5></td>
               <td><form:input path="categoryId" maxlength="10"/></td>
               <td><form:errors path="categoryId" class="error-message" /></td>
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