package account.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private long amount;
	public ModelEvent(Object obj, int id, String message, long amount){
		super(obj, id, message);
		this.amount = amount;
	}
	public long getAmount(){return amount;}
}
