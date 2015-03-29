package simpleLib;

import java.util.Date;

public class Journal extends LibraryDocument {
	String isbn;
	Integer copies;
	String author;


	public Journal(String title, String publisher, String date, String isbn, String copies, String author) {
		this.title = title;
		this.publisher = publisher;
		this.publishDate = new Date(date);
		this.copies = new Integer(copies);
		this.author = author;
		
		
	}
	public String toString() {
		return title + "by " + author + "(" + copies + " left)";
	}
}
