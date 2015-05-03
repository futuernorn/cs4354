package account.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AccountController;
import account.controller.Controller;
import account.model.AccountList;
import account.model.Model;
import account.model.ModelEvent;
import account.view.AgentView.Handler;
import account.view.AgentView.OperationType;

public class AgentStatusView extends JFrameView  implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2089439906531629280L;

	private int accountID;
	private String accountName;

	
	public static final String STOP = "Stop";
	public static final String DISMISS = "Dismiss";

	private JButton startAgent;
	private JButton dismiss;

	private JTextField agentIDField;
	private JTextField stateField;
	private JFormattedTextField adjustment;
	private JFormattedTextField totalAmount;
	private JTextField numOperations;

	public enum State {
		RUNNING, BLOCKED, STOPPED
	}


	private OperationType operationType;
	public AgentStatusView(Model model, Controller controller,
			AgentView.OperationType operationType, UUID agentID, int accountID, double adjustmentAmt,
			double numOpertaions) {
		super(model, controller);
		this.accountID = accountID;
		this.accountName = accountName;
		this.operationType = operationType;

		Handler handler = new Handler();
		
//		“Deposit agent <agentID> for
//		account <accountID>” window or “Withdraw agent <agentID> for account
//		<accountID>”and an agent should start running and the “State” textfield should show
//		“Running”.
//		A “<Deposit/Withdraw> agent <agentID> for account <accountID>” window should
//		contain:
//		• Immutable textfields “Amount in $” and “Operations per second” with the proper info
//		• Uneditable textfield “State” (proper values: Running, Blocked, Stopped)
//		• Uneditable textfield “Amount in $ transferred” that reflects the amount transferred
//		by this agent (either deposited or withdrawn”
//		• Uneditable textfield “Operations completed” with a proper count
//		• Button “Stop agent”
//		• Button “Dismiss agent”

		startAgent = new JButton(STOP);
		startAgent.addActionListener(handler);

		dismiss = new JButton(DISMISS);
		dismiss.addActionListener(handler);

		NumberFormat amountFormat = DecimalFormat.getInstance();
		amountFormat.setMaximumFractionDigits(2);
		amountFormat.setMinimumFractionDigits(2);
		
		agentIDField = new JTextField();
		agentIDField.setText(String.valueOf(agentID));
		agentIDField.setEditable(false);

		stateField = new JTextField();
		stateField.setText("Stopped");

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

		JPanel statePanel = new JPanel();
		statePanel.setLayout(new FlowLayout());
		statePanel.add(new JLabel("State: "));
		statePanel.add(stateField);
		
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
		setTitle(String.format("%s agent %s for account %s",  String.format("%s", operationType).toLowerCase(), agentID, accountID));
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

	@Override
	public void run() {
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        System.out.println("Timer task finished at:"+new Date());
	}
    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
