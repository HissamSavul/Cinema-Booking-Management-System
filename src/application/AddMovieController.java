package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddMovieController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	Movie movie;
	
	@FXML
	private Pane pane1;
	
	@FXML
    private ImageView addImage;

    @FXML
    private TextField duration;

    @FXML
    private Label fullName;

    @FXML
    private TextField genre;

    @FXML
    private TableColumn<MovieDescription,String> tableDuration;

    @FXML
    private TableColumn<MovieDescription,String> tableGenre;

    @FXML
    private TableColumn<MovieDescription,String> tableTitle;

    @FXML
    private TableView<MovieDescription> tabledesc;

    @FXML
    private TextField title;

    @FXML
    private ImageView userImg;
    
    private Image image;
    private String path;
    private int movID;

    public ObservableList<MovieDescription> addAllMoviesInitially() throws SQLException{
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
    	ObservableList<MovieDescription> movieList =  FXCollections.observableArrayList();
    	movieList = pf.getDB().showAllMovies(movieList);
    	return movieList;
    }
    
    ObservableList<MovieDescription> movieList;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fullName.setText("Admin");
		try {
			movieList =  addAllMoviesInitially();
			tableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
	    	tableDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
	    	tableGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
	    	tabledesc.setItems(movieList);
	    	Movie.setAllMoviesFromDB();
			
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
    
    public void importImage() {
    	FileChooser open = new FileChooser();
    	open.setTitle("Open Image File");
    	open.getExtensionFilters().add(new ExtensionFilter("Image File","*png","*jpg"));
    	Stage stage = (Stage)pane1.getScene().getWindow();
    	File file = open.showOpenDialog(stage);
    	if(file != null) {
    		image = new Image(file.toURI().toString(),124,146,false,true);
    		addImage.setImage(image);
    		path = file.getAbsolutePath();
    	}
    }
    
    public void selectMovieList() {
    	MovieDescription movDesc = tabledesc.getSelectionModel().getSelectedItem();
    	int count = tabledesc.getSelectionModel().getSelectedIndex();
    	
    	if((count-1)<-1)
    		return;
    	
    	path = movDesc.getImage();
    	movID = movDesc.getMovieID();
    	title.setText(movDesc.getTitle());
    	duration.setText(movDesc.getDuration());
    	genre.setText(movDesc.getGenre());
    	
    	String uri = "file:" + movDesc.getImage();
    	image = new Image(uri,124,146,false,true);
		addImage.setImage(image);

    }
    
    public void addMovie(ActionEvent event) throws SQLException, IOException{
    	boolean success = Cinema.getInstace(null,null,null,null).addMovie(event,title.getText(),duration.getText(),genre.getText(),path);	
		if(success == true) {
	    	Parent root = FXMLLoader.load(getClass().getResource("AddMovie.fxml")); 
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
    }
    
    public void updateMovie(ActionEvent event) throws SQLException, IOException {
    	boolean success = Cinema.getInstace(null,null,null,null).updateMovie(event,movID,title.getText(),duration.getText(),genre.getText(),path);	
		if(success == true) {
	    	Parent root = FXMLLoader.load(getClass().getResource("AddMovie.fxml")); 
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
	
    }
    public void removeMovie(ActionEvent event) throws SQLException, IOException{
    	boolean success = Cinema.getInstace(null,null,null,null).removeMovie(event,movID,title.getText(),duration.getText(),genre.getText(),path);	
		if(success == true) {
	    	Parent root = FXMLLoader.load(getClass().getResource("AddMovie.fxml")); 
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		

    }
}
