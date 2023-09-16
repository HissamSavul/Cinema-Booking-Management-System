package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManagerInterfaceController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double x;
	private double y;

    @FXML
    private Label fullName;	
	
    @FXML
    private Label movieName;

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
    private ImageView userImg;
    
    int show = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		slideShow(1, slide2, 750);
		slideShow(1, slide3, 750);
		slideShow(1, slide4, 750);
		fullName.setText("Admin");	
		try {
			Movie.setAllMoviesScreensFromDB();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public void goToHome(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("ManagerInterface.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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
	
	public void goToLogin(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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

	public void goToAddScreening(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Screening.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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
	
	public void goToCustomerRecord(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("CustomerRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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
	public void goToBooking(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BookingRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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
}
