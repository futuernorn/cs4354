package account.controller;
import account.model.AccountModel;
import account.view.AccountView;
import account.view.JFrameView;


public class AccountController extends AbstractController {
	public AccountController(){
		setModel(new AccountModel());
		setView(new AccountView((AccountModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}
	public void operation(String option){

	}
}
