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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cust_id=Integer.parseInt(req.getParameter("cust_id"));
		int pin=Integer.parseInt(req.getParameter("pin"));
		
		
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String pwd  = "1234";
		HttpSession session=req.getSession(true);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt=con.prepareStatement("select * from bankapp where cust_id=? and pin=? ");
			
			
			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);
			
		    res=pstmt.executeQuery();
			if(res.next()==true) {
				session.setAttribute("accno", res.getInt("Accno"));
				session.setAttribute("cust_id", res.getInt("Cust_id"));
				
				
				session.setAttribute("cust_name",res.getString("Cust_name") );
				resp.sendRedirect("/BankProject/Home.jsp");
			}
			else {
				resp.sendRedirect("/BankProject/LoginFail.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
