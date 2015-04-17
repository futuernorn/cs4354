package account.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import account.controller.AccountController;
import account.model.AccountModel;
import account.model.ModelEvent;
import account.view.JFrameView;

public class AccountView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public AccountView(AccountModel model, AccountController controller) { 
		super(model, controller); 
		   JTextField t = new JTextField(15);

		   JComboBox c = new JComboBox();

		   JButton b = new JButton("Add items");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container cp = getContentPane();
	    cp.setLayout(new FlowLayout());
	    cp.add(t);
	    cp.add(c);
	    cp.add(b);

		
		setSize(500, 500);
	pack();
	setVisible(true);
	 }
	 // Now implement the necessary event handling code 
	public void modelChanged(ModelEvent event) {


	 }
	 // Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand()); 
	    } }

		
	
}
