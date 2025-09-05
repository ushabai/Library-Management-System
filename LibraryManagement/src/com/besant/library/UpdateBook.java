package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.besant.library.exception.InvalidIDInputException;
import com.besant.library.exception.InvalidPriceException;
import com.besant.library.exception.InvalidQunatityException;

public class UpdateBook {
    public static void updateBookMethod(String url, String userName, String password,Scanner sc) throws InvalidIDInputException {
       
        Connection con = null;
        PreparedStatement pr = null;

        try {
        	con=DBConnection.getConnection(url, userName, password);
        	int bookid=0;
        	while(true) {
        		try {
        			    System.out.print("Enter Book Id to update: ");
        	            bookid = sc.nextInt();
        	            if(bookid<1) {
        	            	throw new InvalidIDInputException("Book ID must be greater than 0.");
        	            }
        	            break;
        			
        		}
        		catch (InputMismatchException e) {
					System.out.println("Invalid input! Please enter a valid integer for Book ID.");
					sc.nextLine();
				}
        		catch (InvalidIDInputException e) {
					System.out.println("Invalid Id : "+e.getMessage());
				}
        	}
          

            if (ViewId.viewIDMethod(url, userName, password, bookid)) {
                System.out.println("1 - Update name");
                System.out.println("2 - Update quantity");
                System.out.println("3 - Update price");
                System.out.println("4 - Update availability");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                	choice=sc.nextInt();
                	
                }
                catch (InputMismatchException e) {
					System.out.println("Invalid input! Please enter a number (1–4).");
					sc.nextLine();
					return;
				}
               
                String updateQuery = "";
                int result;

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter new name: ");
                        String name = sc.nextLine();
                        updateQuery = "UPDATE book SET bookname=? WHERE bookid=?";
                        pr = con.prepareStatement(updateQuery);
                        pr.setString(1, name);
                        pr.setInt(2, bookid);
                       
                        break;

                    case 2:
                    	int quantity=0;
                    	while(true) {
                    		try {
                    			System.out.print("Enter new quantity: ");
                                quantity = sc.nextInt();
                                if(quantity < 1) {
                                	throw new InvalidQunatityException("Quantity must be at least 1.");
                                }
                                break;
                    			
                    		}catch (InputMismatchException e) {
								System.out.println(" Invalid input! Please enter a valid integer for Quantity.");
								sc.nextLine();
							}
                    		catch (InvalidQunatityException e) {
								System.out.println("Invalid quantity : "+e.getMessage());
							}
                    	}
                        
                        updateQuery = "UPDATE book SET quantity=? WHERE bookid=?";
                        pr = con.prepareStatement(updateQuery);
                        pr.setInt(1, quantity);
                        pr.setInt(2, bookid);
                        
                        break;

                    case 3:
                    	int price;
                    	while(true) {
                    		try {
                    			 System.out.print("Enter new price: ");
                                 price = sc.nextInt();
                                 if(price<=0) {
                                	 throw new InvalidPriceException("Price must be greater than 0.");
                                 }
                                 break;
                    		}
                    		catch (InputMismatchException e) {
								System.out.println("Invalid input! Please enter a valid number for Price.");
								sc.nextLine();
							}
                    		catch (InvalidPriceException e) {
								System.out.println(" Invalid price : "+e.getMessage());
							}
                    		
                    	}
                       
                        updateQuery = "UPDATE book SET price=? WHERE bookid=?";
                        pr = con.prepareStatement(updateQuery);
                        pr.setInt(1, price);
                        pr.setInt(2, bookid);
                        
                        break;

                    case 4:
                    	boolean isAvailable;
                    	try {
                    		System.out.print("Enter new availability (true/false): ");
                    		isAvailable = sc.nextBoolean();
                    		
                    	}
                        catch (InputMismatchException e) {
							System.out.println(" Invalid input! Please enter true or false.");
							  sc.nextLine();
	                          return;
						}
                        updateQuery = "UPDATE book SET isAvailable=? WHERE bookid=?";
                        pr = con.prepareStatement(updateQuery);
                        pr.setBoolean(1, isAvailable);
                        pr.setInt(2, bookid);
                       
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                result=pr.executeUpdate();
                if(result>0) 
                	System.out.println("Update successful ");
                
                else 
                	 System.out.println("Update failed.");
                	
                }
                else {
                System.out.println("Book ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating book:"+e.getMessage());
        }  finally {
        	try {
        		if(pr!=null)pr.close();
        		if(con!=null)con.close();
        	}
        	catch (SQLException e) {
				e.printStackTrace();
			}
        }
        }
    }

