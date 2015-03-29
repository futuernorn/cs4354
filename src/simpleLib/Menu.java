package simpleLib;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;




/**
 * @author jeffrey
 *
 */
/**
 * @author jeffrey
 *
 */
public class Menu {
	public enum MenuState {
	    MAIN_MENU, ADMIN_MENU, USER_MENU
	}

	Database data;
	MenuState currentState;
	LibraryUser currentUser;
	HashMap<Integer, String> adminMenuOptions;
	HashMap<Integer, String> mainMenuOptions;
	HashMap<Integer, String> userMenuOptions;
	public Menu(Database data) {
		this.data = data;
		currentUser = null;
		currentState = MenuState.MAIN_MENU;
		adminMenuOptions = new HashMap<Integer, String>();
		adminMenuOptions.put(new Integer(1), "Add User");
		adminMenuOptions.put(new Integer(2), "Add Document");
		adminMenuOptions.put(new Integer(3), "Display Loaned Items");
		adminMenuOptions.put(new Integer(4), "Go Back");

		mainMenuOptions = new HashMap<Integer, String>();
		mainMenuOptions.put(new Integer(1), "Login User");
		mainMenuOptions.put(new Integer(2), "Login Administrator");
		mainMenuOptions.put(new Integer(3), "Exit");
		mainMenuOptions.put(new Integer(4), "Exit & Save");

		userMenuOptions = new HashMap<Integer, String>();
		userMenuOptions.put(new Integer(1), "Search Documents");
		userMenuOptions.put(new Integer(2), "Checkout Item");
		userMenuOptions.put(new Integer(3), "Return Item");
		userMenuOptions.put(new Integer(4), "Current Loans");
		userMenuOptions.put(new Integer(5), "Exit");

		DisplayCurrentStateMenu();


	}
<<<<<<< HEAD:src/simpleLib/Menu.java

=======
	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * Shows current menu options following a completed action.
	 */
	public void DisplayCurrentStateMenu() {
		switch (currentState) {
		case MAIN_MENU:
			DisplayMenu("Main Menu", mainMenuOptions);
			break;
		case ADMIN_MENU:
			DisplayMenu("Admin Menu", adminMenuOptions);
			break;
		case USER_MENU:
			DisplayMenu("User Menu (" + currentUser + ")", userMenuOptions);
			break;
		}
	}
<<<<<<< HEAD:src/simpleLib/Menu.java


=======
	
	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * @param input
	 * @return
	 * Runs the appropriate action following a menu item being selected.
	 */
	public boolean ParseInput(Integer input) {
		switch (currentState) {
		case MAIN_MENU:
			switch (input) {
			case 1:
				LibraryUser loginUser = LoginUser();
				if (loginUser != null) {
					currentState = MenuState.USER_MENU;
					currentUser = loginUser;
				} else {
					System.out.println("Login information not accepted.");
				}
				break;
			case 2:
				LibraryUser loginAdmin = LoginUser();
				if (loginAdmin != null && loginAdmin instanceof Librarian) {
					currentState = MenuState.ADMIN_MENU;
				} else {
					System.out.println("Login information not accepted.");
				}
				break;
			case 3:
				System.out.println("Exiting application...");
				System.exit(0);
			case 4:
				return false;
			default:
				System.out.println("Unknown option entered, please try again.");
			}
			break;
		case ADMIN_MENU:
			switch (input) {
<<<<<<< HEAD:src/simpleLib/Menu.java




=======
			case 1:
				data.AddUser(CreateUser());
				break;
			case 2:
				data.AddDocument(CreateDocument());
				break;
			case 3:
				DisplayLoanedItems();
				break;
			case 4:
				currentState = MenuState.MAIN_MENU;
				break;
			default:
				System.out.println("Unknown option entered, please try again.");
					
				
					
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
			}
			break;
		case USER_MENU:
			switch (input) {
				case 1:
					DoSearch();
					break;
				case 2:
					CheckoutItem();
					break;
				case 3:
					ReturnItem();
					break;
				case 4:
					DisplayLoanedItems();
					break;
				case 5:
					currentState = MenuState.MAIN_MENU;
					break;
				default:
					System.out.println("Unknown option entered, please try again.");

			}

			break;
		}
		DisplayCurrentStateMenu();
		return true;

	}

<<<<<<< HEAD:src/simpleLib/Menu.java

=======
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * Shows the current user's loaned items, or all loaned items if the user is an administrator.
	 */
	private void DisplayLoanedItems() {
<<<<<<< HEAD:src/simpleLib/Menu.java

=======
		
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
		HashMap<Integer, LibraryDocument> results = null;
		if (currentUser instanceof Librarian)
			data.GetAllLoanedItems();
		else
			data.GetLoanedItems(currentUser);
<<<<<<< HEAD:src/simpleLib/Menu.java
		DisplayDocuments("Loaned items for " + currentUser + ":", results);
=======
		DisplayDocuments("Loaned items for " + currentUser + ":", results);	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
		System.out.println("Please press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

	/**
	 * Removes a loaned item from the database's list of loans.
	 */
	private void ReturnItem() {
		HashMap<Integer, LibraryDocument> results = data.GetLoanedItems(currentUser);
		DisplayDocuments("Loaned items for " + currentUser + ":", results);
		int itemSelection = GetSelection("Please enter selection to return: ", results.keySet());
		data.ReturnItem(currentUser, results.get(itemSelection));
	}

	/**
	 * Adds a new loaned item for the current user.
	 */
	private void CheckoutItem() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a title to checkout: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, LibraryDocument> results = data.PerformSearch(title);
		DisplayDocuments("Matching items:", results);
		int itemSelection = GetSelection("Please enter selection: ", results.keySet());
		data.CheckoutItem(currentUser, results.get(itemSelection));
	}

<<<<<<< HEAD:src/simpleLib/Menu.java

=======
	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * Search all Journals and Books for a specific title. This function is also used to provide selection options for check-outs.
	 */
	private void DoSearch() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a title to search: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, LibraryDocument> results = data.PerformSearch(title);

		DisplayDocuments("Search results for \"" + title + "\":", results);
		System.out.println("Please press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();

	}

	private void DisplayDocuments(String prompt, Map<Integer, LibraryDocument> items) {
		HashMap<Integer, String> itemStrings = new HashMap<Integer, String>();
		for(Entry<Integer, LibraryDocument> entry : items.entrySet()){
			itemStrings.put(entry.getKey(), entry.getValue().toString());
		}
		DisplayMenu(prompt, itemStrings);

	}

	public void DisplayMenu(String title, Map<Integer, String> options) {
		System.out.println("");
		System.out.println("================================");
		System.out.println(String.format("|  %-28s|", title));
		System.out.println("================================");
		System.out.println("| Options:                     |");
		for(Entry<Integer, String> entry : options.entrySet()){
			System.out.println(String.format("| %-4s %-24s|", entry.getKey()+".", entry.getValue()));

		}
		if (options.size() < 1)
			System.out.println(String.format("| %-29s|", "No options found."));
		System.out.println("================================");
	}

	private LibraryUser LoginUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String username = "";
		String password = "";
        System.out.println("Please enter your username: ");

        try {
			username = br.readLine();
	        System.out.println("Please enter your password: ");
	        password = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD:src/simpleLib/Menu.java

=======
   
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
		return data.CheckLogin(username, password);




	}

	private LibraryUser CreateUser() {
		LibraryUser newUser = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String username = "";
		String password = "";
		try {
			System.out.println("Please enter the new username: ");
			username = br.readLine();
			System.out.println("Please enter the new user's password: ");
			password = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, String> addUserOptions = new HashMap<Integer, String>();
		addUserOptions.put(new Integer(1), "Student");
		addUserOptions.put(new Integer(2), "Faculty");
		addUserOptions.put(new Integer(4), "Cancel");
		DisplayMenu("User Type?", addUserOptions);
		boolean isUserTypeSelected = false;
		while (!isUserTypeSelected) {
		    // Read line and try to call parseInt on it.
		    String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    int result;
		    try {
		    	result = Integer.parseInt(line);
		    } catch (NumberFormatException exception) {
		    	result = 0;
		    }
		    switch (result) {
		    case 1:
		    	newUser = new Student(username, password, "1");
		    	isUserTypeSelected = true;
		    	break;
		    case 2:
		    	newUser = new Librarian(username, password, "1");
		    	isUserTypeSelected = true;
		    	break;
		    case 4:
		    	return null;
		    }

		}
		return newUser;
	}
<<<<<<< HEAD:src/simpleLib/Menu.java

=======
	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * @return
	 * Used to add new Journals or Books to the Library's database.
	 */
	private LibraryDocument CreateDocument() {
		LibraryDocument newDocument = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String title = "";
		String publisher = "";
		String publishDate = "";
		try {
			System.out.println("Please enter the new document's title: ");
			title = br.readLine();
			System.out.println("Please enter the new document's publisher: ");
			publisher = br.readLine();
			System.out.println("Please enter the new document's publish date: ");
			publishDate = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, String> addDocumentOptions = new HashMap<Integer, String>();
		addDocumentOptions.put(new Integer(1), "Book");
		addDocumentOptions.put(new Integer(2), "Journal");
		addDocumentOptions.put(new Integer(4), "Cancel");
		DisplayMenu("Document Type?", addDocumentOptions);
		boolean isBookTypeSelected = false;
		while (!isBookTypeSelected) {
		    // Read line and try to call parseInt on it.
		    String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    int result;
		    try {
		    	result = Integer.parseInt(line);
		    } catch (NumberFormatException exception) {
		    	result = 0;
		    }
		    switch (result) {
		    case 1:
				String isbn = "";
				String copies = "";
				String author = "";
				try {
					System.out.println("Please enter the new book's ISBN: ");
					isbn = br.readLine();
					System.out.println("Please enter the new book's number of copies: ");
					copies = br.readLine();
					System.out.println("Please enter the new book's author: ");
					author = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newDocument = new Book(title,publisher,publishDate,isbn,copies,author);
		    	isBookTypeSelected = true;
		    	break;
		    case 2:
				String isbn1 = "";
				String copies1 = "";
				String author1 = "";
				try {
					System.out.println("Please enter the new book's ISBN: ");
					isbn1 = br.readLine();
					System.out.println("Please enter the new book's number of copies: ");
					copies1 = br.readLine();
					System.out.println("Please enter the new book's author: ");
					author1 = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newDocument = new Journal(title,publisher,publishDate,isbn1,copies1,author1);
		    	isBookTypeSelected = true;
		    	break;
		    case 4:
		    	return null;
		    }

		}
		return newDocument;
	}
<<<<<<< HEAD:src/simpleLib/Menu.java

=======
	
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
	/**
	 * @param prompt
	 * @param acceptedValues
	 * @return
	 * General purpose user input requests.
	 */
<<<<<<< HEAD:src/simpleLib/Menu.java
	private int GetSelection(String prompt, Set<Integer> acceptedValues) {
=======
	private int GetSelection(String prompt, Set<Integer> acceptedValues) { 
>>>>>>> assign02-turnin:src/simpleLib/Menu.java
		int userSelection = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(prompt);
		String input = "-1";
		while (!acceptedValues.contains(userSelection)) {
			try {
				input = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userSelection = Integer.parseInt(input);
		}
		return userSelection;
	}
}
