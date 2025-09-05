Library Management System

A Java Console-based Library Management System built using JDBC and MySQL.
This project helps to manage books in a library with operations such as adding, updating, deleting, searching, borrowing, and returning books. It demonstrates CRUD operations, database connectivity, exception handling, and collections in Java.




Project Overview

The project provides a menu-driven console interface for users (librarians/admins) to manage the library database.
It connects with MySQL via JDBC and supports persistent storage of book records.
The system handles invalid inputs using custom exceptions (e.g., Invalid ID, Invalid Quantity, Invalid Price) and ensures smooth user experience.



Features
 - Add Books – Insert new book records into the library.
 - Delete Books – Remove existing books using ID.
 - Update Books – Modify book details (name, price, quantity, availability).
 - View All Books – Display complete book list.
 - View Book by ID – Fetch book details using book ID.
 - Search by Name – Search books using partial/full name.
 - Borrow Books – Borrow if available, reduce quantity, auto-update availability.
 - Return Books – Return borrowed books and auto-update availability.
 - Sort Books – Sort by name, price, or quantity.



Technologies Used
-   Java (JDK 17+) – Core language.
- JDBC – Database connectivity.
- MySQL – Database for storing book records.
- Scanner (Java Utility) – User input.
- Custom Exceptions – For validation and error handling.



Advantages
- Easy and organized management of library books.
- Reduces manual record-keeping.
- Strong input validation with meaningful error messages.
- Demonstrates Java OOPs, JDBC, SQL, and Exception Handling.
- Extendable into Spring Boot REST API or GUI app.
