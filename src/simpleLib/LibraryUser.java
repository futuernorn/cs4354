package simpleLib;

import java.util.ArrayList;

public class LibraryUser  implements java.io.Serializable {
	ArrayList<LibraryDocument> checkedOutItems;
	public LibraryUser(String username, String password) {
		checkedOutItems = new ArrayList<LibraryDocument>();
		this.username = username;
		this.password = password;
	}

	String username;
	public String password;

	public String toString() {
		return username;
	}
	

}
