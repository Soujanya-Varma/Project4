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
@WebServlet("/ApplyLoan")

public class ApplyLoan extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		
		int l_type=Integer.parseInt(req.getParameter("choice"));
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String pwd  = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt=con.prepareStatement("select * from loan where lid=?");
			pstmt.setInt(1, l_type);
			res=pstmt.executeQuery();
			
			if(res.next()==true) {
			
			session.setAttribute("lid", res.getInt("lid"));
			session.setAttribute("l_type",res.getString("l_type") );
			session.setAttribute("tenure", res.getInt("tenure"));
			session.setAttribute("interest", res.getInt("interest"));
			session.setAttribute("description", res.getString("description"));
			resp.sendRedirect("/BankProject/loandetails.jsp");
			
	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

}
}
