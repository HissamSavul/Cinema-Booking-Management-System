package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;

public class Controller {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField Username;
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private PasswordField Password;
	@FXML
	private Button SignIn;
	@FXML
	private Hyperlink Forgot;
	@FXML
	private RadioButton CustBtn;
	@FXML
	private ToggleGroup role;
	@FXML
	private RadioButton ManBtn;
	@FXML
	private Button SignUpLink;
	@FXML
	private Button SignInLink;
	@FXML
	private Button Register;
	
	
	public void goToLogin(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToSignUp(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void signIn()
	{
		System.out.println(Username.getText());
		System.out.println(Password.getText());
		
		if(CustBtn.isSelected())
		{
			System.out.println("Customer");
		}
		else if(ManBtn.isSelected())
		{
			System.out.println("Manager");
		}
	}
	
	public void signUp()
	{
		System.out.println(Username.getText());
		System.out.println(Password.getText());
		System.out.println(firstName.getText());
		System.out.println(secondName.getText());
	}

}
