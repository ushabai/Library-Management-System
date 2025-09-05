package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.besant.library.exception.InvalidIDInputException;
import com.besant.library.exception.InvalidPriceException;
import com.besant.library.exception.InvalidQunatityException;

public class CreateBook {
    public static void addBook(String url, String userName, String password, Scanner sc) {

        Connection con = null;
        PreparedStatement pr = null;

        try {
            con = DBConnection.getConnection(url, userName, password);

            
            int bookId = 0;
            while (true) {
            	try {
            		 System.out.print("Enter Book Id: ");
            		 bookId=sc.nextInt();
            		 if(bookId<1) {
            			 throw new InvalidIDInputException("Book ID must be greater than 0.");
            		 }
            		 break;
            		
            	}catch (InputMismatchException e) {
					System.out.println("Invalid input! Please enter a valid integer for Book ID.");
					sc.nextLine();
					
				}
            	catch (InvalidIDInputException e) {
					System.out.println("Error : "+e.getMessage());
				}
            	
            }
            sc.nextLine();

            

            
            System.out.print("Enter Book Name: ");
            String bookName = sc.nextLine();

            
            int quantity = 0;
            while (true) {
                
                try {
                	System.out.print("Enter Quantity: ");
                    quantity = sc.nextInt();
                    if (quantity < 1) {
                        throw new InvalidQunatityException("Quantity must be at least 1.");
                    }
                    break;
                } catch (InputMismatchException e) {
				      System.out.println("Invalid input! Please enter a valid integer for Quantity.");
				      sc.nextLine();
				}
                catch (InvalidQunatityException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            int price;
            while(true) {
            	try {
            		System.out.print("Enter Price: ");
                    price = sc.nextInt();
                    if(price<=0) {
                    	throw new InvalidPriceException("Price must be greater than 0.");
                    }
                    break;
            		
            	}catch (InputMismatchException e) {
            		System.out.println(" Invalid input! Please enter a valid number for Price.");
                    sc.nextLine();
				}
            	catch (InvalidPriceException e) {
					System.out.println("Error : "+e.getMessage());
				}
            }

            
            

            
            System.out.print("Is Available (true/false): ");
            boolean isAvailable = sc.nextBoolean();

           
            String insertQuery = "INSERT INTO book (bookid, bookname, quantity, price, isavailable) VALUES (?, ?, ?, ?, ?)";
            pr = con.prepareStatement(insertQuery);

            pr.setInt(1, bookId);
            pr.setString(2, bookName);
            pr.setInt(3, quantity);
            pr.setInt(4, price);
            pr.setBoolean(5, isAvailable);

            int result = pr.executeUpdate();
            if (result > 0) {
                System.out.println(" Book inserted successfully!");
            } else {
                System.out.println(" Failed to insert book.");
            }

        } 
        catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Error : A book with this ID already exists. Please choose another ID.");
		}catch (SQLException e) {
            System.out.println("Error while adding book: " + e.getMessage());
        } finally {
            try {
                if (pr != null) pr.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
