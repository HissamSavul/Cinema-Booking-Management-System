package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RecordsController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	Customer customer;
	
	@FXML
    private TableColumn<Customer, String> fname;

    @FXML
    private Label fullName;

    @FXML
    private TableColumn<Customer, Integer> id;

    @FXML
    private TableColumn<Customer, String> lname;

    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> uname;
    
    @FXML
    private TableColumn<Account, String> fname1;

    @FXML
    private TableColumn<Account, Integer> id1;

    @FXML
    private TableColumn<Account, String> lname1;

    @FXML
    private TableView<Account> table1;

    @FXML
    private TableColumn<Account, String> uname1;

    @FXML
    private ImageView userImg;
    
    public ObservableList<Customer> addAllCustomersInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<Customer> customerList =  FXCollections.observableArrayList();
    	customerList = pf.getDB().showAllCustomers(customerList);
    	return customerList;
    }
    
    public ObservableList<Account> addAllCustAccountsInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<Account> accountList =  FXCollections.observableArrayList();
    	accountList = pf.getDB().showAllAccounts(accountList);
    	return accountList;
    }
    
    ObservableList<Customer> custList;
    ObservableList<Account> accList;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fullName.setText("admin");
		try {
			custList =  addAllCustomersInitially();
			id.setCellValueFactory(new PropertyValueFactory<>("ID"));
	    	fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
	    	lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
	    	table.setItems(custList);
	    	
	    	accList = addAllCustAccountsInitially();
			id1.setCellValueFactory(new PropertyValueFactory<>("userID"));
	    	fname1.setCellValueFactory(new PropertyValueFactory<>("username"));
	    	lname1.setCellValueFactory(new PropertyValueFactory<>("password"));
	    	table1.setItems(accList);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
	// Event Listener on Button.onAction
	public void goToLogin(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
	
	public void goToAddMovies(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("AddMovie.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Add Movies");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
	
	public void goToHome(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("ManagerInterface.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Home");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
	
	public void goToCustomers(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("CustomerRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Customers");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
	
	public void goToBookings(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BookingRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Bookings");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
	
	public void goToScreening(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Screening");
		stage.show();
		
		root.setOnMousePressed((MouseEvent Mevent) -> {
			x = Mevent.getSceneX();
			y = Mevent.getSceneY();
		});
		
		root.setOnMouseDragged((MouseEvent Mevent) ->{
			stage.setX(Mevent.getScreenX() - x);
			stage.setY(Mevent.getScreenY() - y);
		});
	}
		
	
}
