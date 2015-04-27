package account.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AccountController;
import account.model.AccountList;
import account.model.ModelEvent;

public class EditView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -268100523654777161L;
	private int accountID;
	private String accountName;

	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";

	private JButton deposit;
	private JButton withdraw;
	private JButton dismiss;

	private JFormattedTextField currentAmount;
	private JFormattedTextField adjustment;

	public enum CurrencyType {
		USD, YUAN, EUROS
	}

	private CurrencyType currencyType;

	public EditView(AccountList model, AccountController controller,
			String currencyType, int accountID, String accountName, double accountAmount) {
		super(model, controller);
		this.accountID = accountID;
		this.accountName = accountName;
		this.currencyType = CurrencyType.valueOf(currencyType);

		Handler handler = new Handler();

		deposit = new JButton(DEPOSIT);
		deposit.addActionListener(handler);

		withdraw = new JButton(WITHDRAW);
		withdraw.addActionListener(handler);

		dismiss = new JButton(DISMISS);
		dismiss.addActionListener(handler);

		NumberFormat amountFormat = DecimalFormat.getInstance();
		amountFormat.setMaximumFractionDigits(2);
		amountFormat.setMinimumFractionDigits(2);
		
		currentAmount = new JFormattedTextField(amountFormat);
		currentAmount.setEditable(false);
		currentAmount.setColumns(15);
		currentAmount.setValue(accountAmount);


		adjustment = new JFormattedTextField(amountFormat);

		// adjustment.addPropertyChangeListener("value", this);
		adjustment.setText("0.0");
		adjustment.setColumns(15);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container cp = getContentPane();

		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new FlowLayout());
		amountPanel.add(new JLabel("Current Amount: "));
		amountPanel.add(currentAmount);

		JPanel adjustmentPanel = new JPanel();
		adjustmentPanel.setLayout(new FlowLayout());
		adjustmentPanel.add(new JLabel(String.format("Enter amount in %s: ",
				currencyType)));
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
		setTitle(String.format("%s  %06d; Operations in %s", accountName,
				accountID, currencyType));
		pack();
		setVisible(true);
	}

	@Override
	public void modelChanged(ModelEvent event) {
		if (event.getID() == accountID) {

			System.out.println(event.getAmount());
			currentAmount.setValue(event.getAmount());
		}
	}

	// Inner classes for Event Handling
	class Handler implements ActionListener {
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == DISMISS)
				dispose();
			else {
				double amount = ((Number) adjustment.getValue()).doubleValue();
				adjustment.setText("0.0");
				if (amount <= 0) {
					Object[] options = new Object[]{"Dismiss"};
					JOptionPane.showOptionDialog(null,
							"Please enter an adjustment greater than zero.",
							"Invalid Adjustment Amount.",
							JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE,
							null,
							options,
							options[0]);
					
				} else {
					System.out.println("currentAmount text: "
							+ currentAmount.getText());
					System.out
							.println("currentAmount Float.parseFloat(currentAmount.getText()): "
									+ amount);
					((AccountController) getController()).editViewOperations(
							e.getActionCommand(), accountID, amount,
							currencyType);
				}
			}
		}
	}

}
