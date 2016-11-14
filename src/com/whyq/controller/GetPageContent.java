package com.whyq.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPageContent
 */
@WebServlet("/GetPageContent")
public class GetPageContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetPageContent() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String driverName = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://whyq-test.ctsqoeybdknm.ap-south-1.rds.amazonaws.com:3306/";
		String dbName = "botdb";
		String userId = "admin";
		String password = "password";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		
		HashMap<String, String> contentData = new HashMap<String, String>();
		//Statement statement = null;
		//ResultSet resultSet = null;
		//PrintWriter out = response.getWriter();
		try {
			connection = DriverManager.getConnection(connectionUrl + dbName, userId, password);
			String query = "select * from pageinfo";
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()){
	        	contentData.put(rs.getString("content_id"), rs.getString("field_value"));
	         }
	       System.out.println(Arrays.asList(contentData));
	       request.setAttribute("Data", contentData);
	       RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	       rd.forward(request,response);
	       	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
