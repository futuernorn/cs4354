package account.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AccountController;
import account.model.AccountList;
import account.model.ModelEvent;
import account.view.EditView.CurrencyType;
import account.view.EditView.Handler;

public class AgentView extends JFrameView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6692634844411797019L;

	private int accountID;
	private String accountName;

	
	public static final String START = "Start";
	public static final String DISMISS = "Dismiss";

	private JButton startAgent;
	private JButton dismiss;

	private UUID agentID;
	private JTextField agentIDField;
	private JFormattedTextField adjustment;
	private JFormattedTextField numOperations;

	public enum OperationType {
		DEPOSIT, WITHDRAW
	}


	private OperationType operationType;
	public AgentView(AccountList model, AccountController controller,
			String operationType, UUID agentID, int accountID, String accountName,
			double accountAmount) {
		super(model, controller);
		this.accountID = accountID;
		this.accountName = accountName;
		this.operationType = OperationType.valueOf(operationType);
		this.agentID = agentID;
		Handler handler = new Handler();

		startAgent = new JButton(START);
		startAgent.addActionListener(handler);

		dismiss = new JButton(DISMISS);
		dismiss.addActionListener(handler);

		NumberFormat amountFormat = DecimalFormat.getInstance();
		amountFormat.setMaximumFractionDigits(2);
		amountFormat.setMinimumFractionDigits(2);
		
		agentIDField = new JTextField();
		agentIDField.setText(String.valueOf(agentID));
		agentIDField.setEditable(false);


		adjustment = new JFormattedTextField(amountFormat);

		// adjustment.addPropertyChangeListener("value", this);
		adjustment.setText("0.0");
		adjustment.setColumns(15);
		
		numOperations = new JFormattedTextField(amountFormat);
		numOperations.setText("0.0");
		numOperations.setColumns(15);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container cp = getContentPane();

		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new FlowLayout());
		amountPanel.add(new JLabel("Agent ID: "));
		amountPanel.add(agentIDField);

		JPanel adjustmentPanel = new JPanel();
		adjustmentPanel.setLayout(new FlowLayout());
		adjustmentPanel.add(new JLabel(String.format("Enter amount in %s: ",
				"USD")));
		adjustmentPanel.add(adjustment);
		
		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(new FlowLayout());
		operationsPanel.add(new JLabel("Operations per second"));
		operationsPanel.add(numOperations);


		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(startAgent);
		controlPanel.add(dismiss);

		cp.setLayout(new GridLayout(0, 1));
		cp.add(amountPanel);
		cp.add(adjustmentPanel);
		cp.add(operationsPanel);
		cp.add(controlPanel);

		setSize(600, 350);
		setTitle(String.format("%s  %06d; Operations in %s", accountName,
				accountID, "USD"));
		pack();
		setVisible(true);
	}


	
	@Override
	public void modelChanged(ModelEvent event) {
		if (event.getID() == accountID) {

			System.out.println(event.getAmount());
//			currentAmount.setValue(event.getAmount());
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
				double operations = ((Number) numOperations.getValue()).doubleValue();
				numOperations.setText("0.0");
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
//					AgentStatusView view = new AgentStatusView()
				} else {
					AgentStatusView view = new AgentStatusView(getModel(), getController(),
							 "Deposit", agentID, accountID, amount, operations);
//					System.out.println("currentAmount text: "
//							+ currentAmount.getText());
					System.out
							.println("currentAmount Float.parseFloat(currentAmount.getText()): "
									+ amount);
//					((AccountController) getController()).editViewOperations(
//							e.getActionCommand(), accountID, amount,
//							currencyType);
				}
			}
		}
	}

}
