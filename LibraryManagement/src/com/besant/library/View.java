package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View {
    public static void viewAll(String url, String userName, String password) {
        String query = "SELECT * FROM book";

        
        try (Connection con = DBConnection.getConnection(url, userName, password);
             PreparedStatement pr = con.prepareStatement(query);
             ResultSet rs = pr.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("bookid") +
                        " | Name: " + rs.getString("bookname") +
                        " | Qty: " + rs.getInt("quantity") +
                        " | Price: " + rs.getInt("price") +
                        " | Available: " + rs.getBoolean("isavailable"));
            }

            if (!found) {
                System.out.println("No books available.");
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching books: " + e.getMessage());
        }
    }
}
