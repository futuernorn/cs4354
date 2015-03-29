package simpleLib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Database  implements java.io.Serializable {
	ArrayList<LibraryUser> users;
	ArrayList<Book> books;
	ArrayList<Journal> journals;
	ArrayList<Loan> loans;
	public Database() {
		users = new ArrayList<LibraryUser>();
		books = new ArrayList<Book>();
		journals = new ArrayList<Journal>();
		loans = new ArrayList<Loan>();
	}
	public void AddUser(LibraryUser newUser) {
		users.add(newUser);
		System.out.println("New user (" + newUser +") added!");
		
	}
	public void AddDocument(LibraryDocument newDocument) {
		if (newDocument instanceof Book) {
			books.add((Book) newDocument);
		} else if (newDocument instanceof Journal) {
			journals.add((Journal) newDocument);
		}
		
	}
	public Map<Integer, LibraryDocument> PerformSearch(String title) {
		title = title.toLowerCase();
		Map<Integer, LibraryDocument> searchResults = new HashMap<Integer, LibraryDocument>();
		for (Book book : books) {
			if (book.title.toLowerCase().contains(title)) {
				searchResults.put(new Integer(1), book);
			}
		}
		for (Journal journal: journals) {
			if (journal.title.contains(title)) {
				searchResults.put(new Integer(1), journal);
			}
		}
		
		return searchResults;
		
	}
	
	/**
	 * Alternative method for loading from text if save / serialized data is not available.
	 */
	public void LoadDataFromText() {
		String file ="books.txt";
	    try{
	        InputStream ips=new FileInputStream(file); 
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        BufferedReader br=new BufferedReader(ipsr);
	        String line;
	        line=br.readLine(); // ignore header
	        while ((line=br.readLine())!=null){
	        	String dataValue[] = Arrays.copyOf(line.split("\t"),6);

	        	String title = dataValue[0];
	        	String publisher = dataValue[1];
	        	String date = dataValue[2];
	        	String isbn = dataValue[3];
	        	String copies = dataValue[4];
	        	String author = dataValue[5];
	        	if (title.length() == 0)
	        		continue;
	        	books.add(new Book(title, publisher, date, isbn, copies, author));
	        }
	        br.close(); 

	    }       
	    catch (Exception e){
	        System.out.println(e.toString());

	    } 
		file ="students.txt";
	    try{
	        InputStream ips=new FileInputStream(file); 
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        BufferedReader br=new BufferedReader(ipsr);
	        String line;
	        line=br.readLine(); // ignore header
	        while ((line=br.readLine())!=null){
	        	String dataValue[] = Arrays.copyOf(line.split("\t"),4);

	        	String username = dataValue[0];
	        	String password = dataValue[1];
	        	String id = dataValue[2];
	        	String numberOfCopies = dataValue[3];
	        	if (username.length() == 0)
	        		continue;
	        	users.add(new Student(username, password, id));
	        }
	        br.close(); 

	    }       
	    catch (Exception e){
	        System.out.println(e.toString());

	    } 
	    
		file ="faculty.txt";
	    try{
	        InputStream ips=new FileInputStream(file); 
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        BufferedReader br=new BufferedReader(ipsr);
	        String line;
	        line=br.readLine(); // ignore header
	        while ((line=br.readLine())!=null){
	        	String dataValue[] = Arrays.copyOf(line.split("\t"),4);

	        	String username = dataValue[0];
	        	String password = dataValue[1];
	        	String id = dataValue[2];
	        	String numberOfCopies = dataValue[3];
	        	if (username.length() == 0)
	        		continue;
	        	users.add(new Librarian(username, password, id));
	        }
	        br.close(); 

	    }       
	    catch (Exception e){
	        System.out.println(e.toString());
	    } 
	
	}
	public void CheckoutItem(LibraryUser currentUser, LibraryDocument libraryDocument) {
		loans.add(new Loan(currentUser, libraryDocument));
		System.out.println(String.format("User %s has checked out item %s!", currentUser, libraryDocument));
		
		
	}
	public LibraryUser CheckLogin(String username, String password) {
		
		for (LibraryUser currentUser : users) {
//			System.out.println(String.format("checking %s : %s against %s : %s", currentUser.username.toLowerCase(), username.toLowerCase(), currentUser.password, password));
			if (currentUser.username.equalsIgnoreCase(username) && currentUser.password.equals(password)) {
				return currentUser;
			}
		}

		return null;
	}
	public HashMap<Integer, LibraryDocument> GetLoanedItems(LibraryUser currentUser) {
		HashMap<Integer, LibraryDocument> results = new HashMap<Integer, LibraryDocument>();
		int count = 1;
		for(Loan loan : loans) {
			if (loan.user == currentUser) {
				results.put(Integer.valueOf(count), loan.document);
				count++;
			}
		}
		return results;
	}
	public void ReturnItem(LibraryUser currentUser,
			LibraryDocument currentDocument) {
		for(Loan loan : loans) {
			if (loan.user == currentUser && loan.document == currentDocument) {
				loans.remove(loan);
				System.out.println(String.format("Loan %s removed for user %s.", loan, currentUser));
			}
		}
		
	}
	public HashMap<Integer, LibraryDocument> GetAllLoanedItems() {
		HashMap<Integer, LibraryDocument> results = new HashMap<Integer, LibraryDocument>();
		int count = 1;
		for(Loan loan : loans) {
			results.put(Integer.valueOf(count), loan.document);
			count++;	
		}
		return results;
	}
}
