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
	
//	public void store(int value){
//		amount = value;
//		ModelEvent me = new ModelEvent(this, 1, "", amount);
//		notifyChanged(me);
//	}
//	
//	public void add(){state = "add"; total = current;}
//	
//	public void subtract(){state = "subtract"; total = current;}
	
//	public void equals(){
//		if(state == "add"){
//			total += current;
//		}
//		else {
//			total -= current;
//		}
//		current = total;
//		ModelEvent me = new ModelEvent(this, 1, "", total);
//		notifyChanged(me);
//	}
}
