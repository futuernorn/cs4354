package account.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import account.controller.AccountController;

public class Driver {

	public static void main(String [] args) {
		String accountDataFilename = args[0];
		new AccountController(accountDataFilename); 
        
	}

}
