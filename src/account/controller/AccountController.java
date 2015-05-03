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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import account.model.AccountFundsInsufficentException;
import account.model.AccountList;
import account.model.AccountModel;
import account.view.AccountView;
import account.view.AgentView;
import account.view.EditView;
import account.view.JFrameView;

public class AccountController extends AbstractController {

	private String fileName;
	private Date lastSaved;
	
	public final static double USD_TO_EUROS = 0.92;
	public final static double USD_TO_YUAN = 6.23;
	
	private Set<UUID> agentList;

	public AccountController(String fileName) {
		lastSaved = new Date();
		setModel(new AccountList());
		this.fileName = fileName;
		loadData();

		setView(new AccountView((AccountList) getModel(), this));
		((JFrameView) getView()).setVisible(true);
		
		agentList = new HashSet<UUID>();

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
			writer = new PrintWriter(fileName, "UTF-8");

			writer.println(String.format("|%-17s|%-8s|%-10s", "name", "id",
					"amount"));
			writer.println("--------------------------------------");
			Iterator<AccountModel> iterator = ((AccountList) getModel()).getAccounts().values().iterator();
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
		AccountList accountList = (AccountList) getModel();
		if (option == AccountView.SAVE) {
			saveData();
		} else if (option == AccountView.EXIT) {
			if (((AccountView) getView()).getLastUpdate().after(lastSaved)  ) {
				System.out.println("saving unsaved changes.");
				saveData();
			}
			System.exit(0);
		} else if (option == AccountView.EDIT_IN_USD) {
			AccountModel currentAccount = accountList.getCurrentAcount();
			new EditView(accountList, this, "USD", currentAccount.getID(), currentAccount.getName(), currentAccount.getAmount());
		} else if (option == AccountView.EDIT_IN_EUROS) {
			
			AccountModel currentAccount = accountList.getCurrentAcount();
			new EditView(accountList, this, "EUROS", currentAccount.getID(), currentAccount.getName(), currentAccount.getAmount());
		} else if (option == AccountView.EDIT_IN_YUAN) {
			
			AccountModel currentAccount = accountList.getCurrentAcount();
			new EditView(accountList, this, "YUAN", currentAccount.getID(), currentAccount.getName(), currentAccount.getAmount());
		} else if (option == AccountView.CREATE_DEPOSIT_AGENT) {
			AccountModel currentAccount = accountList.getCurrentAcount();
			UUID newID = UUID.randomUUID();
			agentList.add(newID);
			new AgentView(accountList, this, "DEPOSIT", newID, currentAccount.getID(), currentAccount.getName(), currentAccount.getAmount());
		} else if (option == AccountView.CREATE_WIDTHDRAW_AGENT) {
			AccountModel currentAccount = accountList.getCurrentAcount();
			UUID newID = UUID.randomUUID();
			agentList.add(newID);
			new AgentView(accountList, this, "WITHDRAW", newID, currentAccount.getID(), currentAccount.getName(), currentAccount.getAmount());
		}

	}
	
	public void editViewOperations(String option, int accountID, double d, EditView.CurrencyType currencyType) {
		switch (currencyType) {
			case YUAN:
				d *= USD_TO_YUAN;
				break;
			case EUROS:
				d *= USD_TO_EUROS;
				break;
		}
		System.out.println("editViewOperations: " + d);
		if (option == EditView.DEPOSIT) {
			((AccountList) getModel()).depositFunds(accountID, d);
		} else if (option == EditView.WITHDRAW) {
			try {
				((AccountList) getModel()).withdrawFunds(accountID, d);
			} catch (AccountFundsInsufficentException e) {
				((AccountView) getView()).showWithdrawError(String.format("Insufficient funds: amount to withdraw is %0.2f greater than available funds: %0.2f", e.getAdjustment(), e.getAmount()));
			}
		}
	}
	
	public void updateAccountSelection(AccountModel newSelection) {
		((AccountList) getModel()).setCurrentAcount(newSelection);
	}
	

}
