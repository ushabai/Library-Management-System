package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.besant.library.exception.InvalidIDInputException;

public class RemoveBook {
    public static void removeBookMethod(String url, String userName, String password, Scanner sc) throws InvalidIDInputException {

    	 String selectQuery = "SELECT * FROM book WHERE bookid=?";
    	 String deleteQuery = "DELETE FROM book WHERE bookid=?";
    	 Connection con= DBConnection.getConnection(url, userName, password);
        try (PreparedStatement pr=con.prepareStatement(selectQuery)){
        	
        	 int bookid = 0;
             while (true) {
                 try {
                     System.out.print("Enter Id to delete book: ");
                     bookid = sc.nextInt();
                     if (bookid < 1) {
                         throw new InvalidIDInputException("Book ID must be greater than 0.");
                     }
                     break;
                 } catch (InputMismatchException e) {
                     System.out.println(" Invalid input! Please enter a valid integer for Book ID.");
                     sc.nextLine();
                 } catch (InvalidIDInputException e) {
                     System.out.println("Exception " + e.getMessage());
                 }
             }

             pr.setInt(1, bookid);
             ResultSet rs = pr.executeQuery();
             if (rs.next()) {
                 PreparedStatement pr1 = con.prepareStatement(deleteQuery);
                 pr1.setInt(1, bookid);
                 int result = pr1.executeUpdate();

                 if (result > 0) {
                     System.out.println(" Book deleted successfully!");
                 } else {
                     System.out.println(" Failed to delete book.");
                 }
             } else {
                 System.out.println(" Book Id not found.");
             }

         } catch (SQLException e) {
             System.out.println(" Database Error while deleting: " + e.getMessage());
         }
     }
 }

          
