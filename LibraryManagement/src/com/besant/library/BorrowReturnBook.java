package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BorrowReturnBook {
	public static void borrowBook(String url, String userName, String password, Scanner sc) {
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;

        try {
            System.out.print("Enter Book ID to borrow: ");
            int bookId = sc.nextInt(); 

            con = DBConnection.getConnection(url, userName, password);

            
            String checkQuery = "SELECT quantity, isAvailable FROM book WHERE bookid = ?";
            pr = con.prepareStatement(checkQuery);
            pr.setInt(1, bookId);
            rs = pr.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                boolean isAvailable = rs.getBoolean("isAvailable");

                if (!isAvailable || quantity <= 0) {
                    System.out.println(" Book is not available for borrowing.");
                } else {
                   
                    String updateQuery = "UPDATE book SET quantity = quantity - 1 WHERE bookid = ?";
                    try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();
                    }

                    
                    String checkQty = "SELECT quantity FROM book WHERE bookid = ?";
                    try (PreparedStatement checkStmt = con.prepareStatement(checkQty)) {
                        checkStmt.setInt(1, bookId);
                        ResultSet newRs = checkStmt.executeQuery();
                        if (newRs.next() && newRs.getInt("quantity") == 0) {
                            String makeUnavailable = "UPDATE book SET isavailable = false WHERE bookid = ?";
                            try (PreparedStatement makeStmt = con.prepareStatement(makeUnavailable)) {
                                makeStmt.setInt(1, bookId);
                                makeStmt.executeUpdate();
                            }
                        }
                    }

                    System.out.println(" Book borrowed successfully!");
                }
            } else {
                System.out.println(" Book ID not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter a valid number for Book ID.");
            sc.nextLine(); 
        } catch (SQLException e) {
            System.out.println("Error while borrowing book: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (pr != null) pr.close(); if (con != null) con.close(); } catch (SQLException ignored) {}
        }
    }

    // Return a book
    public static void returnBook(String url, String userName, String password, Scanner sc) {
        Connection con = null;
        PreparedStatement pr = null;

        try {
            System.out.print("Enter Book ID to return: ");
            int bookId = sc.nextInt(); 

            con = DBConnection.getConnection(url, userName, password);

            
            String updateQuery = "UPDATE book SET quantity = quantity + 1 WHERE bookid = ?";
            pr = con.prepareStatement(updateQuery);
            pr.setInt(1, bookId);
            int rows = pr.executeUpdate();

            if (rows > 0) {
                
                String makeAvailable = "UPDATE book SET isavailable = true WHERE bookid = ?";
                try (PreparedStatement makeStmt = con.prepareStatement(makeAvailable)) {
                    makeStmt.setInt(1, bookId);
                    makeStmt.executeUpdate();
                }
                System.out.println(" Book returned successfully!");
            } else {
                System.out.println(" Book ID not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter a valid number for Book ID.");
            sc.nextLine(); 
        } catch (SQLException e) {
            System.out.println("Error while returning book: " + e.getMessage());
        } finally {
            try { if (pr != null) pr.close(); if (con != null) con.close(); } catch (SQLException ignored) {}
        }
    }

}
