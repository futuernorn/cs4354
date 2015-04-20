package account.model;

import java.util.ArrayList;

public class AccountList extends AbstractModel {
 private ArrayList<AccountModel> accountList;
 private AccountModel currentAccount;
 
 public AccountList() {
	 accountList = new ArrayList<AccountModel>();
	 currentAccount = null;
 }
 
 public void createAccount(String name, float amount, int id) {
	 accountList.add(new AccountModel(name, amount, id));
	 if (currentAccount == null)
		 currentAccount = accountList.get(0);
 }

public ArrayList<AccountModel> getAccounts() {
	return accountList;
}

public void setCurrentAcount(AccountModel newSelection) {
	currentAccount = newSelection;
	
}

public AccountModel getCurrentAcount() {
	return currentAccount;
	
}
	
}
