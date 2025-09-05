package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortBooks {
	 public static void sortBooks(String url, String userName, String password, Scanner sc) {
	        Connection con = null;
	        PreparedStatement pr = null;
	        ResultSet rs = null;

	        try {
	            con = DBConnection.getConnection(url, userName, password);

	            String query = "select * from book";
	            pr = con.prepareStatement(query);
	            rs = pr.executeQuery();

	            List<Book> books = new ArrayList<>();
	            while (rs.next()) {
	                Book b = new Book(
	                        rs.getInt("bookid"),
	                        rs.getString("bookname"),
	                        rs.getInt("quantity"),
	                        rs.getInt("price"),
	                        rs.getBoolean("isavailable")
	                );
	                books.add(b);
	            }

	            if (books.isEmpty()) {
	                System.out.println("No books found in library.");
	                return;
	            }

	            System.out.println("Sort by: 1-Name | 2-Price | 3-Quantity");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    books.sort(Comparator.comparing(Book::getBookName));
	                    break;
	                case 2:
	                    books.sort(Comparator.comparingDouble(Book::getPrice));
	                    break;
	                case 3:
	                    books.sort(Comparator.comparingInt(Book::getQuantity));
	                    break;
	                default:
	                    System.out.println("Invalid choice! Showing unsorted list.");
	            }

	            System.out.println("\n Sorted Books:");
	            for (Book b : books) {
	                System.out.println(b);
	            }

	        } catch (SQLException e) {
	            System.out.println("Error while sorting: " + e.getMessage());
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pr != null) pr.close();
	                if (con != null) con.close();
	            } catch (SQLException ignored) {}
	        }
	    }
	


}
