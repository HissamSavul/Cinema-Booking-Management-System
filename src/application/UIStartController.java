package application;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;

public class UIStartController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	public  Booking booking;
	public  Movie movie;
	public  Screening screening;
	public  Account account;

	//FXML
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
	
	public Image image;
	public String path;
	
     public void importImage() {
	    	FileChooser open = new FileChooser();
	    	open.setTitle("Open Image File");
	    	open.getExtensionFilters().add(new ExtensionFilter("Image File","*png","*jpg"));
	    	File file = open.showOpenDialog(stage);
	    	if(file != null) {
	    		image = new Image(file.toURI().toString(),124,146,false,true);
	    		path = file.getAbsolutePath();
	    	}
	    }
     	
	public void goToLogin(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToSignUp(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToPage(String FXMLpage, Button btn) throws IOException{
		btn.getScene().getWindow().hide();
		root = FXMLLoader.load(getClass().getResource(FXMLpage));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void signIn(ActionEvent event) throws SQLException, IOException{
		Cinema cinema = Cinema.getInstace(booking, movie, screening, account);
		String interfaceis = cinema.Signin(ManBtn,CustBtn,Username.getText(), Password.getText(),SignIn);
		if(!interfaceis.equals("")) {
			goToPage(interfaceis,SignIn);
		}
	}

	public void signUp(ActionEvent event) throws SQLException, IOException{
		boolean success =Cinema.getInstace(null,null,null,null).Signup(event,Username.getText(),Password.getText(),firstName.getText(),secondName.getText(),path);
		if(success == true) {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
}