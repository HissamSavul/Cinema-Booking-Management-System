package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ScreeningController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	Screening screen;
	
	@FXML
    private TextField date;

    @FXML
    private Label fullName;

    @FXML
    private TextField screenNo;

    @FXML
    private TableView<Screening> table;

    @FXML
    private TableColumn<Screening, String> tableDate;

    @FXML
    private TableColumn<Screening, Integer> tableScreen;

    @FXML
    private TableColumn<Screening, String> tableTime;

    @FXML
    private TableColumn<Screening, String> tableTitle;

    @FXML
    private TextField time;

    @FXML
    private TextField title;

    @FXML
    private ImageView userImg;
    private int screenid;

    public ObservableList<Screening> addAllScreensInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<Screening> screenList =  FXCollections.observableArrayList();
    	screenList = pf.getDB().showAllScreens(screenList);
    	return screenList;
    }
    
    ObservableList<Screening> screenList;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fullName.setText("Admin");
		try {
			screenList =  addAllScreensInitially();
			tableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
	    	tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	tableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
	    	tableScreen.setCellValueFactory(new PropertyValueFactory<>("screeningID"));
	    	table.setItems(screenList);
	    	Screening.setAllScreensFromDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void selectScreenList() {
    	Screening screen = table.getSelectionModel().getSelectedItem();
    	int count = table.getSelectionModel().getSelectedIndex();
    	
    	if((count-1)<-1)
    		return;
    	
    	screenid = screen.getScreeningID();
    	title.setText(screen.getTitle());
    	date.setText(screen.getDate());
    	time.setText(screen.getTime());
    	screenNo.setText(String.valueOf(screenid));
    }
    
	
	public void addScreening(ActionEvent event) throws SQLException, IOException {
    	boolean success = Cinema.getInstace(null,null,null,null).addScreen(title.getText(), date.getText(),time.getText(),50);
		if(success == true) {
	    	root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
    }
	
    public void updateScreening(ActionEvent event) throws IOException, SQLException {
    	boolean success = Cinema.getInstace(null,null,null,null).updateScreen(screenNo.getText(),title.getText(), date.getText(),time.getText(),50);
		if(success == true) {
	    	root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
    }
    
    public void removeScreening(ActionEvent event) throws IOException, SQLException {
    	boolean success = Cinema.getInstace(null,null,null,null).removeScreen(screenNo.getText(),title.getText(), date.getText(),time.getText(),50);
		if(success == true) {
	    	root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
    }
}
