<%@ page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>
 
<div class="container"><div class="row"><div class="col-6">
 
	<h1>PAYMENT</h1>
	<form id="formPayment" name="formPayment">
		Payment ID: 
 		<input id="payment_id" name="payment_id" type="text" class="form-control form-control-sm" readonly>
		Customer/Card holder's Name: 
 		<input id="customer_n" name="customer_n" type="text" class="form-control form-control-sm">
 		<br> Amount: 
 		<input id="amount" name="amount" type="text" class="form-control form-control-sm">
 		<br> Email: 
 		<input id="email" name="email" type="text" class="form-control form-control-sm">
 		<br> Card Type: 
 		<input id="card_type" name="card_type" type="text" class="form-control form-control-sm">
 		<br> Card No: 
 		<input id="card_no" name="card_no" type="text" class="form-control form-control-sm">
 		<br> Expiration Month: 
 		<input id="exp_month" name="exp_month" type="text" class="form-control form-control-sm">
 		<br> Expiration Year: 
 		<input id="exp_year" name="exp_year" type="text" class="form-control form-control-sm">
 		<br> CVN : 
 		<input id="cvn" name="cvn" type="text" class="form-control form-control-sm">
 		<br>
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
	</form>
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
	 	<%
 			Payment paymentObj = new Payment(); 
 			out.print(paymentObj.readItems()); 
 		%>
	</div>
</div> </div> </div> 
</body>
</html>