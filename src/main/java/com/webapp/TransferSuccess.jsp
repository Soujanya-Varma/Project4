<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="SuccessFail.css">
</head>
<body>
<div class="grp">
	<h2 align="center">
	<%
	session = request.getSession();

out.println("Amount of "+session.getAttribute("amount"));

%> Transaction Success <br>

With Transaction ID :

<%

session = request.getSession();

out.println(session.getAttribute("tid"));

%>

</h2>

<br><br>
</div>

<a href="Home.jsp">Redirect</a>

</body>
</html>