package com.besant.library;

public class Book {
	private int bookId;
	private String bookName;
	private int quantity;
	private int price;
	private boolean isAvailable;
	
	
	public Book(int bookId, String bookName, int quantity, int price, boolean isAvailable) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.quantity = quantity;
		this.price = price;
		this.isAvailable = isAvailable;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", quantity=" + quantity + ", price=" + price
				+ ", isAvailable=" + isAvailable + "]";
	}
	
	
	

}
