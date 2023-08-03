<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
<%

    Connection con;

    ResultSet resultSet;

    PreparedStatement pstmt;

    int accno=(int)session.getAttribute("accno");

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/project";

        String user = "root";

        String pwd = "1234";

        con = DriverManager.getConnection(url, user, pwd);

        String sql = "select * from transferstatus where sender_accno=? or receiver_accno=?";

        pstmt=con.prepareStatement(sql);

        pstmt.setInt(1, accno);

        pstmt.setInt(2, accno);

        resultSet=pstmt.executeQuery();

    %>

    <table border="1">

        <tr>

            <th>Transfer ID</th>

            <th>Customer ID</th>

            <th>Bank Name</th>

            <th>IFSC</th>

            <th>Sender Account Number</th>

            <th>Receiver IFSC</th>

            <th>Receiver Account Number</th>

            <th>Amount</th>

        </tr>

        <%

        while (resultSet.next()) {

        %>

        <tr>

            <td><%=resultSet.getInt(1)%></td>

            <td><%=resultSet.getString(2)%></td>

            <td><%=resultSet.getString(3)%></td>

            <td><%=resultSet.getInt(4)%></td>

            <td><%=resultSet.getString(5)%></td>

            <td><%=resultSet.getInt(6)%></td>

            <td><%=resultSet.getInt(7)%></td>

            <td><%=resultSet.getInt(8)%></td>

        </tr>

        <%

        }

        } catch (Exception e) {

        out.println(e);

        }  

        %>

    </table>

    <br>

    <a href="Home.jsp"> Go to Home Page</a>

</body>
</html>