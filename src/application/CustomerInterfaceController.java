package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CustomerInterfaceController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;
	
	@FXML
	private Label fullName;
	@FXML
	private ImageView userImg;
	@FXML
	private TextField search;
	@FXML
	private AnchorPane slide1;
	@FXML
	private AnchorPane slide2;
	@FXML
	private AnchorPane slide3;
	@FXML
	private AnchorPane slide4;
	@FXML
	private Label movieName;
    @FXML
    private Pane pane1;
	
	private int show = 0;
	private Image image;
	private String path;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		slideShow(1, slide2, 750);
		slideShow(1, slide3, 750);
		slideShow(1, slide4, 750);
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
		try {
			Movie.setAllMoviesScreensFromDB();
		} catch (SQLException e) {
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
	
	// Event Listener on Button.onAction
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
		SingletonCookie sc = SingletonCookie.getInstace("", -1, "");
		sc.cleanUserSession();
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
	
	public void slideShow(double duration, Node node, double width)
	{
		TranslateTransition transition = new TranslateTransition(Duration.seconds(duration), node);
		transition.setByX(width);
		transition.play();
	}
	
	public void next(ActionEvent event)
	{
		if (show == 0)
		{
			slideShow(1, slide2, -750);
			show++;
			movieName.setText("Spiderman");
		}
		else if (show == 1)
		{
			slideShow(1, slide3, -750);
			show++;
			movieName.setText("Dr. Strange");
		}
		else if (show == 2)
		{
			slideShow(1, slide4, -750);
			show++;
			movieName.setText("The Martian");
		}
	}
	
	public void back(ActionEvent event)
	{
		if (show == 1)
		{
			slideShow(1, slide2, 750);
			show--;
			movieName.setText("Avengers");
		}
		else if (show == 2)
		{
			slideShow(1, slide3, 750);
			show--;
			movieName.setText("Spiderman");
		}
		else if (show == 3)
		{
			slideShow(1, slide4, 750);
			show--;
			movieName.setText("Dr. Strange");
		}
	}
}
