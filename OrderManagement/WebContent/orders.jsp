<%@page import= "com.Order" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/orders.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Order Management </h1>
<form id="formOrder" name="formOrder" method="post" action="orders.jsp">
 Order Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Order Category: 
<input id="category" name="category" type="text" 
 class="form-control form-control-sm">
<br> Payment Method: 
<input id="paymethod" name="paymethod" type="text" 
 class="form-control form-control-sm">
<br> Order Payment: 
<input id="payment" name="payment" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidIdSave" name="hidIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divOrdersGrid">

<%
 Order orderObj = new Order(); 
 out.print(orderObj.readOrders()); 
%>
</div>

</div></div></div>

</body>
</html>