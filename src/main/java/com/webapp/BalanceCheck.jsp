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
<section class="sec">
<h3>
<% 
session = request.getSession();

out.println("Balance = "+ session.getAttribute("bal"));
%>
</section>
<a href="Home.jsp">Click here to redirect</a></h3>

</body>
</html>