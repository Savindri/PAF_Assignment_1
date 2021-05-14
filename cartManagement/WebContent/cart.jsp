<%@page import= "com.cart" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/cart.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">

<h1>Cart Management </h1>
<form id="formCart" name="formCart" method="post" action="cart.jsp">
 Product Id: 
<input id="productId" name="productId" type="text" 
 class="form-control form-control-sm">
<br> User Id: 
<input id="userId" name="userId" type="text" 
 class="form-control form-control-sm">
<br> Unit Price: 
<input id="unitPrice" name="unitPrice" type="text" 
 class="form-control form-control-sm">
<br> Quantity: 
<input id="quantity" name="quantity" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="Save" name="Save" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divCartGrid">

<%
cart cartObj = new cart(); 
 out.print(cartObj.readCart()); 
%>
</div>
</body>
</html>