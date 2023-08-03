package com.digit.javaTraining;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String pwd  = "1234";
		
		
		
		
		int cust_id=Integer.parseInt(req.getParameter("cust_id"));
		String bank_name=req.getParameter("bank_name");
		String ifsc_code=req.getParameter("ifsc_code");
		
		int pin=Integer.parseInt(req.getParameter("pin"));
		int amount=Integer.parseInt(req.getParameter("amount"));
		
		int sender_accno=Integer.parseInt(req.getParameter("sender_accno"));
		String receiver_ifsc=req.getParameter("receiver_ifsc");
		int receiver_accno=Integer.parseInt(req.getParameter("receiver_accno"));
		
		HttpSession session=req.getSession();
		session.setAttribute("error", "Transaction Failed");
		session.setAttribute("amount",amount);
		int accno=(int)session.getAttribute("accno");
		
		
		Random r = new Random();
	    String tran_id = (100000 + r.nextInt(900000))+"";
	    session.setAttribute("tid", tran_id);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			pstmt=con.prepareStatement("select * from bankapp where Cust_id=? and IFSC_code=? and Accno=? and pin=?");
			pstmt.setInt(1,cust_id);
			pstmt.setString(2,ifsc_code);
			pstmt.setInt(3,accno);
			pstmt.setInt(4,pin);
			res1=pstmt.executeQuery();
			if(res1.next()==true) {
				pstmt=con.prepareStatement("select * from bankapp where IFSC_code=? and Accno=?");
				pstmt.setString(1,ifsc_code );
				pstmt.setInt(2, accno);
				res2=pstmt.executeQuery();
				
				if(res2.next()==true) {
					pstmt=con.prepareStatement("select Balance from bankapp where  Accno=?");
					pstmt.setInt(1, accno);
					res3=pstmt.executeQuery();
					res3.next();
					int bal=res3.getInt(1);
					
					if(bal>amount) {
						pstmt=con.prepareStatement("update bankapp set Balance=Balance-? where Accno=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, accno);
						int x1=pstmt.executeUpdate();
						if(x1>0) {
							pstmt=con.prepareStatement("update bankapp set Balance=Balance+? where Accno=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, accno);
							int x2=pstmt.executeUpdate();
							if(x2>0) {
								pstmt=con.prepareStatement("insert into transferstatus values(?,?,?,?,?,?,?,?)");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2,bank_name );
								pstmt.setString(3,ifsc_code );
								pstmt.setInt(4, sender_accno);
								pstmt.setString(5,receiver_ifsc );
								pstmt.setInt(6, receiver_accno);
								pstmt.setInt(7, amount);
								pstmt.setString(8, tran_id);
								int x3=pstmt.executeUpdate();
								if(x3>0) {
									resp.sendRedirect("/BankProject/TransferSuccess.jsp");
								}
								
							
							else {
								resp.sendRedirect("/BankProject/TransferFail.jsp");
							}
						}
						else {
							resp.sendRedirect("/BankProject/TransferFail.jsp");
						}
						
					}
					else {
						resp.sendRedirect("/BankProject/TransferFail.jsp");
					}
					

				}
				else {
					resp.sendRedirect("/BankProject/TransferFail.jsp");
				}
				
			}
			}
			else {
				resp.sendRedirect("/BankProject/TransferFail.jsp");
			}
			
			
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

}
}
