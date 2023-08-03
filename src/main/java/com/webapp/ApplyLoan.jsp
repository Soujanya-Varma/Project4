<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Loan.css">
</head>
<body>

<form action="ApplyLoan">
<center>
<%

session=request.getSession();
String s1=(String)session.getAttribute("cust_name");
out.println("Dear "+s1+" , your eligible for the below loans.Please choose any option...");

%>
</center>
<center>
<a href=# >1.Home Loan</a><br>
<a href=#>2.Education Loan</a><br>
<a href=#>3.Vehicle Loan</a><br>
<a href=#>4.Gold Loan</a><br>
<a href=#>5.Personal Loan</a><br><br>

<label>Enter ur choice:</label>
<input type="text" name="choice">
<input type="submit" value="Apply">
</center>
</form>
</body>
</html>