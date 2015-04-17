package account.view;
import account.controller.Controller;
import account.model.Model;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}
