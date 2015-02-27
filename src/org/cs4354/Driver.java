package org.cs4354;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
	private static ArrayList<Book> books;
	
	public static void main(String[] args) {

	}

	private static void LoadData() {
		String file ="books.txt";
	    //reading   
	    try{
	        InputStream ips=new FileInputStream(file); 
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        BufferedReader br=new BufferedReader(ipsr);
	        String line;
	        while ((line=br.readLine())!=null){
	        	String dataValue[] = Arrays.copyOf(line.split("\t"),6);
	        	String title = dataValue[0];
	        	String publisher = dataValue[1];
	        	String date = dataValue[2];
	        	String isbn = dataValue[3];
	        	String copies = dataValue[4];
	        	String author = dataValue[5];
	        	books.add(new Book(title, publisher, date, isbn, copies, author));
	        }
	        br.close(); 

	    }       
	    catch (Exception e){
	        System.out.println(e.toString());
	    } 	
	}

}
