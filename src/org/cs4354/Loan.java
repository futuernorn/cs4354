package org.cs4354;

import java.util.Date;

public class Loan  implements java.io.Serializable {
	LibraryUser user;
	LibraryDocument document;
	Date startDate;
	
	public Loan(LibraryUser user, LibraryDocument document) {
		this.user = user;
		this.document = document;
		startDate = new Date();
	}
	
	public String toString() {
		return String.format("%s started on %s", document,startDate);
	}
}
