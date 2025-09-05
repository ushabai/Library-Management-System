package com.besant.library;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.besant.library.exception.InvalidIDInputException;

public class Application {
	public static void main(String[] args) throws InvalidIDInputException {
		String url = "jdbc:mysql://localhost:3306/librarymanagement";
		String userName = "root";
		String password = "root";

		Scanner sc = new Scanner(System.in);

	
		while (true) {
			try {
			System.out.println("*****Welcome to Library*****");
			System.out.println("Type 1 for add books");
			System.out.println("Type 2 for remove books");
			System.out.println("Type 3 for update books");
			System.out.println("Type 4 for view all books");
			System.out.println("Type 5 to view the book by id");
			System.out.println("Type 6 to Search Book by Name");
            System.out.println("Type 7 to Borrow a Book");
            System.out.println("Type 8 to Return a Book");
            System.out.println("Type 9 to Sort Books");
			System.out.println("Type 0 to exit");

			System.out.println("Enter your choice:");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Add books");
				CreateBook.addBook(url, userName, password,sc);
				break;
			case 2:
				System.out.println("Delete books");
				RemoveBook.removeBookMethod(url, userName, password,sc);
				break;
			case 3:
				System.out.println("Update books");
				UpdateBook.updateBookMethod(url, userName, password,sc);
				break;
			case 4:// System.out.println("view all books");
				View.viewAll(url, userName, password);
				break;
			case 5:
				System.out.println("view book by id");

				System.out.println("Enter book id");
				int id = sc.nextInt();
				ViewId.viewIDMethod(url, userName, password, id);

				break;
			case 6:
				System.out.println("Search Book by Name");
			    SearchBook.searchByName(url, userName, password, sc);
			    break;
			case 7:
                System.out.println("Borrow a Book");
                BorrowReturnBook.borrowBook(url, userName, password, sc);
                break;
            case 8:
                System.out.println("Return a Book");
                BorrowReturnBook.returnBook(url, userName, password, sc);
                break;
            case 9:
                System.out.println("Sort Books");
                SortBooks.sortBooks(url, userName, password, sc);
                break;
			case 0:
				System.out.println("Exiting");
				sc.close();
				return;
			default:
				System.out.println("Invalid choice. Please enter between 0–9.");

			}
			}catch (InputMismatchException e) {
				System.out.println(" Invalid input! Please enter a valid number.");
				sc.nextLine();
			}
			catch (Exception e) {
				System.out.println("Unexpected error : "+e.getMessage());
			}
		}
	}

}
