package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class BookingRecordsController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	
	@FXML
    private TableColumn<BookingDetails, Integer> amount;

    @FXML
    private TableColumn<BookingDetails, String> dateTime;

    @FXML
    private Label earned;

    @FXML
    private Label fullName;

    @FXML
    private TableColumn<BookingDetails, Integer> id;

    @FXML
    private TableColumn<BookingDetails, String> name;

    @FXML
    private TableColumn<BookingDetails, Integer> numTickets;

    @FXML
    private Label sold;

    @FXML
    private TableView<BookingDetails> table;

    @FXML
    private TableColumn<BookingDetails, String> title;

    @FXML
    private ImageView userImg;
    public int totalTicketssold = 0;
    public int totalEarnings = 0;
 
    
    ObservableList<BookingDetails> bookingList;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			bookingList =  addAllBookingsInitially();
			id.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
	    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	title.setCellValueFactory(new PropertyValueFactory<>("title"));
	    	numTickets.setCellValueFactory(new PropertyValueFactory<>("numTickets"));
	    	dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
	    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
	    	table.setItems(bookingList);
	    	
	    	for(int i=0 ; i<bookingList.size() ; i++) {
	    		totalTicketssold += bookingList.get(i).getNumTickets();
	    		totalEarnings += bookingList.get(i).getAmount();
	    	}
	    	sold.setText(""+totalTicketssold);
	    	earned.setText(""+totalEarnings + " Rs-/");
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public ObservableList<BookingDetails> addAllBookingsInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<BookingDetails> bookingList =  FXCollections.observableArrayList();
    	bookingList = pf.getDB().getBookingDetails(bookingList);
    	return bookingList;
    }
    
    public void goToLogin(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
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

	public void goToAddScreening(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Screenings");
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
	
	public void goToCustomerRecord(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("CustomerRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Customer Records");
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
	public void goToBooking(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BookingRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Booking Records");
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