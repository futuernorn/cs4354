package account.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import account.model.AccountList;
import account.model.AccountModel;
import account.view.AccountView;
import account.view.EditView;
import account.view.JFrameView;

public class AccountController extends AbstractController {

	private String fileName;
	private Date lastSaved;

	public AccountController(String fileName) {
		lastSaved = new Date();
		setModel(new AccountList());
		this.fileName = fileName;
		loadData();

		setView(new AccountView((AccountList) getModel(), this));
		((JFrameView) getView()).setVisible(true);

	}

	public void loadData() {

		System.out.println("Attempting to open filename: " + fileName);
		InputStream ips;
		try {
			ips = new FileInputStream(fileName);

			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;

			line = br.readLine(); // ignore headers

			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String dataValue[] = Arrays.copyOf(line.split("\\|"), 3);
				String name = dataValue[0].trim();
				String id = dataValue[1].trim();
				String amount = dataValue[2].trim().substring(1);
				((AccountList) getModel())
						.createAccount(name, Float.parseFloat(amount),
								Integer.parseInt(id));

			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveData() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName + "-test", "UTF-8");

			writer.println(String.format("|%-17s|%-8s|%-10s", "name", "id",
					"amount"));
			writer.println("--------------------------------------");
			Iterator<AccountModel> iterator = ((AccountList) getModel()).getAccounts().iterator();
			while (iterator.hasNext()) {
				AccountModel account = iterator.next();
				writer.println(String.format("|%-17s| %06d |$%-10.2f", account.getName(), account.getID(), account.getAmount()));
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void accountViewOperations(String option) {
		if (option == AccountView.SAVE) {
			saveData();
		} else if (option == AccountView.EXIT) {
			if (((AccountView) getView()).getLastUpdate().after(lastSaved)  ) {
				System.out.println("saving unsaved changes.");
				saveData();
			}
			System.exit(0);
		} else if (option == AccountView.EDIT_IN_USD) {
			new EditView((AccountList) getModel(), this, "USD");
		} else if (option == AccountView.EDIT_IN_EUROS) {
			new EditView((AccountList) getModel(), this, "EUROS");
		} else if (option == AccountView.EDIT_IN_YUAN) {
			new EditView((AccountList) getModel(), this, "YUAN");
		} 

	}
	
	public void editViewOperations(String option, int accountID, float amount) {
		
	}
	
	public void updateAccountSelection(AccountModel newSelection) {
		((AccountList) getModel()).setCurrentAcount(newSelection);
	}
	

}
