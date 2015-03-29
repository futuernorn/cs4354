package simpleLib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Driver {
	private static ArrayList<Book> books;
	private static Menu menu;
	static Database data;
	public static void main(String[] args) {
		
		
		try {
			data = LoadSerialzedData();
		} catch (IOException e) {
			System.err.println("Could not find seralized data to restore from or invalid data found. Loading data from text...");
			data = new Database();
			data.LoadDataFromText();
		} 
		menu = new Menu(data);
		
		// Read input with BufferedReader.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
		    // Read line and try to call parseInt on it.
		    String line = "-1";
			try {
				line = in.readLine();
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

		    if (!menu.ParseInput(result))
		    	break;
		}
		try {
			SerializeData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	
	/**
	 * @return Data loaded from data.ser serialized file
	 * @throws IOException
	 */
	private static Database LoadSerialzedData() throws IOException {
		Database loadedData = null;
	       try
	        {
	            FileInputStream fis = new FileInputStream("data.ser");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            loadedData = (Database) ois.readObject();
	            ois.close();
	            fis.close();
	         
	          }catch(ClassNotFoundException c){
	             System.out.println("Class not found");
	             c.printStackTrace();
	          }
	          return loadedData;
	
	}
	private static void SerializeData() throws Exception {	

	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream("data.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(data);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in data.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	    
	}

}
