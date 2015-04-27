package account.model;

public class AccountFundsInsufficentException extends Exception {

	double adjustment;
	public double getAdjustment() {
		return adjustment;
	}

	public double getAmount() {
		return amount;
	}

	double amount;
	
	public AccountFundsInsufficentException(double adjustment, double amount) {
		super();
		this.adjustment = adjustment;
		this.amount = amount;
	}
	
	
	
}
