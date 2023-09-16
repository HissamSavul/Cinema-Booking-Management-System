package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookMovieController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	Screening screen;
	Booking booking;
	
	@FXML
    private TextField Cost;

	@FXML
    private TextField date;

	@FXML
    private Pane pane1;
	
    @FXML
    private Label fullName;

    @FXML
    private TextField screenNo;

    @FXML
    private Spinner<Integer> spin;

    @FXML
    private TableView<Screening> table;

    @FXML
    private TableColumn<Screening, String> tableDate;

    @FXML
    private TableColumn<Screening, String> tableTime;

    @FXML
    private TableColumn<Screening, String> tableTitle;
    
    @FXML
    private TableColumn<Screening, String> tableScreen;

    @FXML
    private TextField time;

    @FXML
    private TextField title;
    
    @FXML
    private ImageView movieImg;
    
    @FXML
    private TextField seatsCounter;
    
    @FXML
    private ImageView userImg;
    private int screenid;
    public Image image;
    public String path;

    ObservableList<Screening> screenList;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50);
    	valueFactory.setValue(0);
    	spin.setValueFactory(valueFactory);    	
    	
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
		SingletonCookie sc = SingletonCookie.getInstace("", -1, "");
		fullName.setText(sc.getUserName());	
		InputStream stream = null;
		try {
			stream = new FileInputStream(sc.getImagePath());
		    Image image = new Image(stream);
		    userImg.setImage(image);
		    stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void importImage() throws SQLException {
    	FileChooser open = new FileChooser();
    	open.setTitle("Open Image File");
    	open.getExtensionFilters().add(new ExtensionFilter("Image File","*png","*jpg"));
    	Stage stage = (Stage)pane1.getScene().getWindow();
    	File file = open.showOpenDialog(stage);
    	if(file != null) {
    		image = new Image(file.toURI().toString(),124,146,false,true);
    		userImg.setImage(image);
    		path = file.getAbsolutePath();
    		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    		SingletonCookie sc = SingletonCookie.getInstace("", -1, "");
    		sc.setImagePath(path);
    		pf.getDB().updateImg(sc.getUserID(),path);
    	}
    }
    
    public ObservableList<Screening> addAllScreensInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<Screening> screenList =  FXCollections.observableArrayList();
    	screenList = pf.getDB().showAllScreens(screenList);
    	return screenList;
    }
    
    public void calculateCost(MouseEvent event)
    {
    	//for cost
    	int totalCost = spin.getValue() * 1000;
    	String temp = "" + totalCost;
    	Cost.setText(temp);    	
    }

    public int adjustSeats() {
    	seatsCounter.setText(""+(Integer.valueOf(seatsCounter.getText()) - spin.getValue()));
		return (Integer.valueOf(seatsCounter.getText()) - spin.getValue());
    }
   
    
    public void selectScreenList() throws SQLException {
    	Screening screen = table.getSelectionModel().getSelectedItem();
    	int count = table.getSelectionModel().getSelectedIndex();
    	
    	if((count-1)<-1)
    		return;
    	screenid = screen.getScreeningID();
    	title.setText(screen.getTitle());
    	date.setText(screen.getDate());
    	time.setText(screen.getTime());
    	screenNo.setText(String.valueOf(screenid));
    	
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	String imgPath = pf.getDB().getImg(screen.getTitle());
    	InputStream stream = null;
		try {
			stream = new FileInputStream(imgPath);
		    Image image = new Image(stream);
		    movieImg.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int totalSeats = pf.getDB().getSelectedScreeningBookedSeats(screenNo.getText());
		seatsCounter.setText(""+totalSeats);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, totalSeats);
    	valueFactory.setValue(0);
    	spin.setValueFactory(valueFactory);  
    	Cost.setText(""+0);    	
    	if(totalSeats == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR Message");
			alert.setHeaderText(null);
			alert.setContentText("Screening Hall is full!");
			alert.showAndWait();	
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
		root = FXMLLoader.load(getClass().getResource("CustomerInterface.fxml"));
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
	
	public void goToBookMovie(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BookMovie.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("./Images/logo.png"));
		stage.setTitle("Book Movie");
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

	public void addBooking(ActionEvent event) throws SQLException, IOException {
		boolean success = Cinema.getInstace(null,null,null,null).makeBooking(title.getText(),spin.getValue(),screenNo.getText());
		if(success == true) {
			root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.getIcons().add(new Image("./Images/logo.png"));
			stage.setTitle("Payment");
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
	
	
}
