package account.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AccountController;
import account.model.AccountList;
import account.model.AccountModel;
import account.model.ModelEvent;
import account.view.JFrameView;

public class AccountView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";

	public static final String EDIT_IN_USD = "Edit in USD";
	public static final String EDIT_IN_YUAN = "Edit in Yuan";
	public static final String EDIT_IN_EUROS = "Edit in Euros";
	
	public static final String CREATE_DEPOSIT_AGENT = "Create deposit agent";
	public static final String CREATE_WIDTHDRAW_AGENT = "Create withdraw agent";
	

	JComboBox accountList;
	
	private JButton editUSD;
	private JButton editEuros;
	private JButton editYuan;
	private JButton openDepositAgent;
	private JButton openWithdrawAgent;
	private JButton save;
	private JButton exit;
	
	private Date lastUpdate;

	public AccountView(AccountList model, AccountController controller) {
		super(model, controller);
		
		lastUpdate = new Date();
		lastUpdate = new Date(lastUpdate.getTime() - 1000);
		accountList = new JComboBox(model.getAccounts().values().toArray());
		
		accountList.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println(accountList.getSelectedItem());
		    	((AccountController) getController()).updateAccountSelection((AccountModel) accountList.getSelectedItem());
		    }
		});
		
		Handler handler = new Handler();

		editUSD = new JButton(EDIT_IN_USD);
		editUSD.addActionListener(handler);
		
		editEuros = new JButton(EDIT_IN_YUAN);
		editEuros.addActionListener(handler);
		
		editYuan = new JButton(EDIT_IN_EUROS);
		editYuan.addActionListener(handler);

		openDepositAgent = new JButton(CREATE_DEPOSIT_AGENT);
		openDepositAgent.addActionListener(handler);
		
		openWithdrawAgent = new JButton(CREATE_WIDTHDRAW_AGENT);
		openWithdrawAgent.addActionListener(handler);
		
		
		save = new JButton(SAVE);
		save.addActionListener(handler);
		
		exit = new JButton(EXIT);
		exit.addActionListener(handler);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container cp = getContentPane();
		// GridLayout frameGridLayout = new GridLayout(0,1);
		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new FlowLayout());
		comboPanel.add(accountList);

		JPanel editPanel = new JPanel();
		editPanel.setLayout(new FlowLayout());
		editPanel.add(editUSD);
		editPanel.add(editEuros);
		editPanel.add(editYuan);

		JPanel agentPanel = new JPanel();
		agentPanel.setLayout(new FlowLayout());
		agentPanel.add(openDepositAgent);
		agentPanel.add(openWithdrawAgent);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(save);
		controlPanel.add(exit);

		cp.setLayout(new GridLayout(0, 1));
		cp.add(comboPanel);
		cp.add(editPanel);
		cp.add(agentPanel);
		cp.add(controlPanel);

		setSize(500, 500);
		setTitle("Account Manager MVC");
		pack();
	}

	// Now implement the necessary event handling code
	public void modelChanged(ModelEvent event) {

	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	// Inner classes for Event Handling
	class Handler implements ActionListener {
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController) getController()).accountViewOperations(e
					.getActionCommand());
		}
	}
	
	class AlertDialog extends JDialog implements ActionListener {

		  JButton button;

		  public AlertDialog(String message) {
		   JLabel alertMessage = new JLabel(message);
			  button = new JButton("Close");
		     button.addActionListener(this);
		     add(alertMessage);
		     add(button);
		     pack();
		     setVisible(true);
		  }

		  public void actionPerformed(ActionEvent e) {
		      dispose();
		  }
	}

	public void showWithdrawError(String message) {
		Object[] options = new Object[]{"Dismiss"};
		JOptionPane.showOptionDialog(this,
				message,
				"Withdraw Error",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[0]);
		
	}

}
