package com.tim.servlets.preparedstatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	PreparedStatement stmt;
		public void init() throws ServletException
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","kmit1234");
				stmt=con.prepareStatement("update product set price =? where id =?");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		int id=Integer.parseInt(request.getParameter("id"));
    		int price = Integer.parseInt(request.getParameter("price"));
    		
    		try {
				stmt.setInt(1, price);
				stmt.setInt(2, id);
				int result = stmt.executeUpdate();
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("<b>"+result+"products updated</b>");
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	public void destroy()
    	{
    		try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}

}
