package org.cs4354;

import java.util.Date;

public abstract class LibraryDocument {
	String publisher;
	String title;
	Date publishDate;
	
	public LibraryDocument(String title, String publisher, String date) {
		this.title = title;
		this.publisher = publisher;
		this.publishDate = new Date(date);
		
	}
}
