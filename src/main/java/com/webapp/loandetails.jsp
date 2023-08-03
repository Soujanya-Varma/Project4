<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="SuccessFail.css">
</head>
<body>
<div class=grp>
<%

session=request.getSession();
int s1=(int)session.getAttribute("lid");
String s2=(String)session.getAttribute("l_type");
int s3=(int)session.getAttribute("tenure");

String s5=(String)session.getAttribute("description");

out.println("\n LoanID: "+s1);
out.println("\nLoanType: "+s2);
out.println("\nLoanTenure: "+s3);
out.println("\nLoanInterest: "+session.getAttribute("interest"));
out.println("\nLoanDescription: " +s5);

%>
<br>
	<br>
	<a href="Home.jsp">Click to Redirect</a>
	</div>

</body>
</html>