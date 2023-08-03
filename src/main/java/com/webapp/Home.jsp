<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Home.css">

</head>
<body>
<form>
<h1 class="grp">Welcome to the Bank</h1>
<div class="container">


<%
session=request.getSession();
String s1=(String)session.getAttribute("cust_name");
out.println(s1+" welcome to your account.Please select an operation to perform.");
%>



</div>
<br>
<br>
<a href="CheckBalance">1.Check Balance</a><br>
<a href="ChangePin.html">2.Change Pin</a><br>
<a href="ApplyLoan.jsp">3.Apply Loan</a><br>
<a href="Transfer.html">4.Transfer Amount</a><br>
<a href="ViewTransaction.jsp">5.Transactions</a><br>

<a href="Logout">5.Logout</a>

</form>
</body>
</html>