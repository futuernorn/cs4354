package account.model;

public class AccountModel  {
	private float amount;
	private String name;
	private int id;
	
	public AccountModel(String name, float amount, int id) {
		this.name = name;
		this.amount = amount;
		this.id = id;
	}

	public String toString() {
		return name + " (" + id + ")";
	}
//	public void clear(){amount = 0; store(0);}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public float getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	public void depositAmount(double d) {
		this.amount += d;

		
	}

	public void withdrawAmount(double d) throws AccountFundsInsufficentException {
		if (d > amount)
			throw new AccountFundsInsufficentException(d, amount);
		this.amount -= d;
		
	}

	
}
