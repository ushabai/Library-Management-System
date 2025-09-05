package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchBook {
	public static void searchByName(String url, String userName, String password, Scanner sc) {
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection(url, userName, password);
            sc.nextLine(); 
            System.out.print("Enter book name (or part of it): ");
            
            String keyword = sc.nextLine();

            String query = "Select * from book where bookname like ?";
            pr = con.prepareStatement(query);
            pr.setString(1, "%" + keyword + "%");
            rs = pr.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Book Id: " + rs.getInt("bookid") +
                                   " | Book name: " + rs.getString("bookname") +
                                   " | Quantity: " + rs.getInt("quantity") +
                                   " | Price: " + rs.getInt("price") +
                                   " | Availability: " + rs.getBoolean("isavailable"));
            }
            if (!found) {
                System.out.println("No books found with keyword: " + keyword);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (pr != null) pr.close(); if (con != null) con.close(); } catch (SQLException ignored) {}
        }
	}

}
