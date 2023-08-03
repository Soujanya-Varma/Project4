package com.digit.javaTraining;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String pwd  = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt=con.prepareStatement("select Balance from bankapp where Accno=?");
			pstmt.setInt(1, accno);
			resultSet=pstmt.executeQuery();
			if(resultSet.next()==true) {
				session.setAttribute("bal",resultSet.getInt("Balance"));
				resp.sendRedirect("/BankProject/BalanceCheck.jsp");
//				resp.sendRedirect("/BankProject/CheckBalance.jsp");
				
			}
			else {
				resp.sendRedirect("/BankProject/BalanceFail.jsp");
			}
	}
		
		catch(Exception e) {
			e.printStackTrace();
	}
}

}
