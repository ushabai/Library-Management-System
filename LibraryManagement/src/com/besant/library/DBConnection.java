package com.besant.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection(String url, String userName,String password) {
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver class loaded");
			con=DriverManager.getConnection(url,userName,password);
			//System.out.println("Connections created successfully");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
	}


}
