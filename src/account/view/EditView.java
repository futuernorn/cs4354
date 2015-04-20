package account.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AccountController;
import account.model.AccountList;
import account.model.ModelEvent;
import account.view.AccountView.Handler;

public class EditView extends JFrameView {
	
	private int accountID;
	private String accountName;
	
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";
	
	private JButton deposit;
	private JButton withdraw;
	private JButton dismiss;
	
	private JTextField currentAmount;
	private JTextField adjustment;
	
	
	
	public EditView(AccountList model, AccountController controller, String currencyType) {
		super(model, controller);
		accountID = model.getCurrentAcount().getID();
		accountName = model.getCurrentAcount().getName();

		Handler handler = new Handler();

		deposit = new JButton(DEPOSIT);
		deposit.addActionListener(handler);
		
		withdraw = new JButton(WITHDRAW);
		withdraw.addActionListener(handler);
		
		dismiss = new JButton(DISMISS);
		dismiss.addActionListener(handler);
		
		currentAmount = new JTextField("100");
		currentAmount.setEditable(false);
		
		adjustment = new JTextField("0.0");
		

		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container cp = getContentPane();
		
		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new FlowLayout());		
		amountPanel.add(new JLabel("Current Amount: "));
		amountPanel.add(currentAmount);
		
		JPanel adjustmentPanel = new JPanel();
		adjustmentPanel.setLayout(new FlowLayout());		
		adjustmentPanel.add(new JLabel(String.format("Enter amount in %s: ", currencyType)));
		adjustmentPanel.add(adjustment);

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(deposit);
		controlPanel.add(withdraw);
		controlPanel.add(dismiss);
		

		cp.setLayout(new GridLayout(0, 1));
		cp.add(amountPanel);
		cp.add(adjustmentPanel);
		cp.add(controlPanel);

		setSize(600, 350);
		setTitle(String.format("%s  %06d; Operations in %s", accountName, accountID, currencyType));
		pack();
		setVisible(true);
	}

	@Override
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	// Inner classes for Event Handling
	class Handler implements ActionListener {
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == DISMISS)
				dispose();
			((AccountController) getController()).editViewOperations(e
					.getActionCommand(), accountID, Float.parseFloat(currentAmount.getText()));
		}
	}
}
