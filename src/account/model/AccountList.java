package account.model;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList extends AbstractModel {
 private HashMap<Integer, AccountModel> accountList;
 private AccountModel currentAccount;
 
 public AccountList() {
	 accountList = new HashMap<Integer, AccountModel>();
	 currentAccount = null;
 }
 
 public void createAccount(String name, float amount, int id) {
	 accountList.put(id, new AccountModel(name, amount, id));
	 if (currentAccount == null)
		 currentAccount = accountList.get(id);
 }

public HashMap<Integer, AccountModel> getAccounts() {
	return accountList;
}

public void setCurrentAcount(AccountModel newSelection) {
	currentAccount = newSelection;
	
}

public AccountModel getCurrentAcount() {
	return currentAccount;
	
}

public void depositFunds(int accountID, double d) {
	accountList.get(accountID).depositAmount(d);
	
	ModelEvent me = new ModelEvent(this, accountID, "", accountList.get(accountID).getAmount());
	notifyChanged(me);
	
}

public void withdrawFunds(int accountID, double d) throws AccountFundsInsufficentException {
	accountList.get(accountID).withdrawAmount(d);
	ModelEvent me = new ModelEvent(this, accountID, "", accountList.get(accountID).getAmount());
	notifyChanged(me);
	

	
}
	
}
